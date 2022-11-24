package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private List<String> paths = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Map<FileProperty, String> duplicate = new HashMap<>();
        FileProperty property = new FileProperty(file.toFile().length(), file.toFile().getName());
        String path = duplicate.put(property, file.toFile().getAbsolutePath());
        if (path != null) {
            paths.add(path);
        }
        return super.visitFile(file, attrs);
    }

    public void filePrint() {
        System.out.println(paths);
    }
}
