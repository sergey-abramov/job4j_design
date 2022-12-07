package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        valid(argsName);
        Scanner scanner = new Scanner(new BufferedInputStream(
                new FileInputStream(argsName.get("path")))).useDelimiter(System.lineSeparator());
        List<String> path = new ArrayList<>();
        String[] filter = argsName.get("filter").split(",");
        while (scanner.hasNext()) {
            path.add(scanner.next());
        }
        writer(argsName.get("out"), filter(filter, path, argsName.get("delimiter")));
    }

    public static void writer(String pathOut, List<String> filter) {
        try (PrintStream out = new PrintStream(new FileOutputStream(pathOut))) {
            filter.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> filter(String[] add, List<String> path, String delimiter) {
        List<String> rsl = new ArrayList<>();
        int[] index = new int[add.length];
        String[] e = path.get(0).split(delimiter);
        for (int j = 0; j < e.length; j++) {
            for (int i = 0; i < add.length; i++) {
                if (add[i].equals(e[j])) {
                    index[i] = j;
                }
            }
        }
        for (String s : path) {
            StringJoiner joiner = new StringJoiner(delimiter);
            String[] f = s.split(delimiter);
            for (Integer g : index) {
                joiner.add(f[g]);
            }
            rsl.add(joiner.toString());
        }
        return rsl;
    }

    public static void valid(ArgsName argsName) {
        if (!";".equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException(String.format("%s - invalid delimiter argument", argsName.get("delimiter")));
        }
        if (argsName.get("out").isEmpty()) {
            throw new IllegalArgumentException(String.format("%s - not directory", argsName.get("filter")));
        }
        if (argsName.get("filter").isBlank()) {
            throw new IllegalArgumentException(String.format("%s - argument is empty", argsName.get("filter")));
        }
        if (!Files.exists(Paths.get(argsName.get("path")))) {
            throw new IllegalArgumentException(String.format("%s - not directory", argsName.get("path")));
        }
    }
}
