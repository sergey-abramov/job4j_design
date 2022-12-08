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
        try (Scanner scanner = new Scanner(new BufferedInputStream(
                new FileInputStream(argsName.get("path")))).useDelimiter(System.lineSeparator())) {
            List<String> path = new ArrayList<>();
            String[] filter = argsName.get("filter").split(",");
            while (scanner.hasNext()) {
                path.add(scanner.next());
            }
            writer(argsName.get("out"), filter(filter, path, argsName.get("delimiter")));
        }
    }

    private static void writer(String pathOut, List<String> filter) {
        if ("stdout".equals(pathOut)) {
            for (String s : filter) {
                System.out.println(s);
            }
        } else {
            try (PrintStream out = new PrintStream(new FileOutputStream(pathOut))) {
                filter.forEach(out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<String> filter(String[] add, List<String> path, String delimiter) {
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
        if (argsName.get("out").isEmpty() || !argsName.get("out").endsWith(".csv")) {
            throw new IllegalArgumentException(String.format("%s - not directory", argsName.get("out")));
        }
        if (argsName.get("filter").isBlank()) {
            throw new IllegalArgumentException(String.format("%s - argument is empty", argsName.get("filter")));
        }
        if (!Files.exists(Paths.get(argsName.get("path")))) {
            throw new IllegalArgumentException(String.format("%s - not directory", argsName.get("path")));
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Args.length != 4");
        }
        ArgsName argsName = ArgsName.of(args);
        valid(argsName);
        handle(argsName);
    }
}
