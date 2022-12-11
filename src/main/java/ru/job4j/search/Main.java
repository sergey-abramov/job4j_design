package ru.job4j.search;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private static List<Path> searchBy(ArgsName argsName) throws IOException {
        List<Path> rsl = new ArrayList<>();
        if ("name".equals(argsName.get("t"))) {
            rsl = Search.search(Paths.get(argsName.get("d")),
                    path -> path.toFile().getName().endsWith(argsName.get("n")));
        }
        if ("regex".equals(argsName.get("t"))) {
            rsl = Search.search(Paths.get(argsName.get("d")),
                    path -> path.toFile().getName().matches(argsName.get("n")));
        }
        if ("mask".equals(argsName.get("t"))) {
            rsl = Search.search(Paths.get(argsName.get("d")),
                    path -> path.toFile().getName().matches(argsName.get("n")));
        }
        return rsl;
    }

    private static void write(String pathOut, List<Path> search) {
        try (PrintStream out = new PrintStream(new FileOutputStream(pathOut))) {
            search.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void valid(ArgsName argsName) {
        File d = Paths.get(argsName.get("d")).toFile();
        if (!d.exists()) {
            throw new IllegalArgumentException(String.format("%s - not exist", argsName.get("d")));
        }
        if (!d.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s - not directory", argsName.get("d")));
        }
        String[] s = argsName.get("o").split("\\.");
        if (s.length != 2 || !argsName.get("o").endsWith(".txt")) {
            throw new IllegalArgumentException(String.format("%s - not file.txt", argsName.get("o")));
        }
        if (argsName.get("n").isEmpty()) {
            throw new IllegalArgumentException(String.format("%s - not file name, mask, regex", argsName.get("n")));
        }
        if (argsName.get("t").isEmpty()) {
            throw new IllegalArgumentException(String.format("%s - search method must be by name, mask or regex", argsName.get("n")));
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Args.length != 4");
        }
        ArgsName argsName = ArgsName.of(args);
        write(argsName.get("o"), searchBy(argsName));
    }
}
