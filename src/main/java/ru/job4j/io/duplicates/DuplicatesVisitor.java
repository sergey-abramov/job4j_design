package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private FileProperty property;

    public DuplicatesVisitor(FileProperty property) {
        this.property = property;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (property.getName().equals(file.getFileName().toString()) && property.getSize() == file.toFile().length()) {
            System.out.println(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }


}
