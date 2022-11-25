package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        valid(args);
        search(Paths.get(args[0]), p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void valid(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("The root folder is empty or search argument is null. Use ROOT_FOLDER or SEARCH_ARGUMENT.");
        }
        File start = Paths.get(args[0]).toFile();
        if (!start.exists()) {
            throw new IllegalArgumentException(String.format("%s - not exist", args[0]));
        }
        if (!start.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s - not directory", args[0]));
        }
        if (!args[1].startsWith(".") || args[1].length() < 2) {
            throw new IllegalArgumentException("This search argument is not extension.");
        }
    }
}
