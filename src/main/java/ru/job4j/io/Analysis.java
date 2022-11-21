package ru.job4j.io;

import java.io.*;
import java.util.Arrays;

public class Analysis {

    public void unavailable(String source, String target) {
        boolean log = true;
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target));
            BufferedReader in = new BufferedReader(new FileReader(source))) {
            while (in.ready()) {
                String[] s = in.readLine().split(" ");
                if (log && (s[0].contains("400") || s[0].contains("500"))) {
                    log = false;
                    out.printf("%s; ", s[1]);
                } else if (!log && (s[0].contains("300") || s[0].contains("200"))) {
                    log = true;
                    out.printf("%s;\n ", s[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
      /*  String sourse = "C:\\projects\\job4j_design\\log\\source_.txt";
        String target = "C:\\projects\\job4j_design\\log\\target_1.txt";
        Analysis analysis = new Analysis();
        analysis.unavailable(sourse, target);
        sourse = "C:\\projects\\job4j_design\\log\\source_2.txt";
        target = "C:\\projects\\job4j_design\\log\\target_2.txt";
        analysis.unavailable(sourse, target);*/
    }
}
