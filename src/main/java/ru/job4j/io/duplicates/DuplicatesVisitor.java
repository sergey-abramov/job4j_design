package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private List<Path> paths = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(0, "source.txt");
        if (property.getName().equals(file.getFileName().toString()) && property.getSize() == file.toFile().length()) {
            paths.add(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }

    public void filePrint(List<Path> paths) {
        System.out.println(paths);
    }

    public List<Path> getPaths() {
        return paths;
    }
}
