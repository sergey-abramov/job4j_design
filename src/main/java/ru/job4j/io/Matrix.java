package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Matrix {

    public static void main(String[] args) {
        int size = 10;
        try (FileOutputStream out = new FileOutputStream("multiple.txt")) {
            for (int i = 1; i < size; i++) {
                for (int j = 1; j < size; j++) {
                    String r = Integer.toString(i * j);
                    out.write(r.getBytes());
                    out.write(" ".getBytes());
                    if (j == size - 1) {
                        out.write(System.lineSeparator().getBytes());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
