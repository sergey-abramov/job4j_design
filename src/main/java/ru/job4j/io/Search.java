package ru.job4j.io;

import java.io.IOException;
import java.io.OptionalDataException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        Path start = Paths.get(args[0]);
        Path end = Paths.get(args[1]);
        if (valid(start, end)) {
            search(start, p -> p.toFile().getName().endsWith(end.toString())).forEach(System.out::println);
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static boolean valid(Path start, Path end) {
        boolean rsl = false;
        if (start == null || end == null) {
            rsl = false;
            throw new IllegalArgumentException("The root folder is empty or search argument is null. Use ROOT_FOLDER or SEARCH_ARGUMENT.");
        }
        if (!start.toFile().exists()) {
            rsl = false;
            throw new IllegalArgumentException(String.format("Not exist %s", start.toFile().getAbsoluteFile()));
        }
        if (!start.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", start.toFile().getAbsoluteFile()));
        }
        if (!end.startsWith(".")) {
            rsl = false;
            throw new IllegalArgumentException("This search argument is not extension.");
        }
        rsl = true;
        return rsl;
    }
}
