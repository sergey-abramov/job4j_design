package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private Path pathZip;

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void valid(String[] args) {
        ArgsName arguments = ArgsName.of(args);
        File directory = Path.of(arguments.get("d")).toFile();
        if (!directory.exists()) {
            throw new IllegalArgumentException(String.format("%s - not exist", args[0]));
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s - not directory", args[0]));
        }
        if (!arguments.get("e").startsWith(".") || args[1].length() < 2) {
            throw new IllegalArgumentException("This search argument is not extension.");
        }
        if (!arguments.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Argument must be in .zip format");
        }
    }

    public List<Path> pathList(String[] args) throws IOException {
        valid(args);
        ArgsName arguments = ArgsName.of(args);
        Path directory = Path.of(arguments.get("d"));
        this.pathZip = Path.of(arguments.get("o"));
        return Search.search(directory, p -> !p.toString().endsWith(arguments.get("e")));
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        valid(args);
        Zip zip = new Zip();
        zip.packFiles(zip.pathList(args), zip.pathZip.toFile());
    }
}
