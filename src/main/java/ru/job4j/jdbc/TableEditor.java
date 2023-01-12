package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws IOException, SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws IOException, SQLException, ClassNotFoundException {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            config.load(in);
        }
        Class.forName(config.getProperty("driver_class"));
        connection = DriverManager
                .getConnection(config.getProperty("url"),
                        config.getProperty("username"),
                        config.getProperty("password"));
    }

    public void execude(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "create table if not exists " + tableName + "(%s);",
                "id serial primary key"
        );
        execude(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format(
                "drop table %s;", tableName
        );
        execude(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "alter table %s add column %s %s;", tableName, columnName, type
        );
        execude(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "alter table %s drop column %s;", tableName, columnName
        );
        execude(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception  {
        String sql = String.format(
                "alter table %s rename column %s to %s;", tableName, columnName, newColumnName
        );
        execude(sql);
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        TableEditor tableEditor = new TableEditor(new Properties());
        tableEditor.createTable("table_1");
        System.out.println(getTableScheme(connection, "table_1"));
        tableEditor.addColumn("table_1", "name", "text");
        System.out.println(getTableScheme(connection, "table_1"));
        tableEditor.renameColumn("table_1", "name", "name_two");
        System.out.println(getTableScheme(connection, "table_1"));
        tableEditor.dropColumn("table_1", "name_two");
        System.out.println(getTableScheme(connection, "table_1"));
        tableEditor.dropTable("table_1");
    }
}
