package ru.job4j.io;

import java.io.*;
import java.util.Arrays;

public class Analysis {

    public void unavailable(String source, String target) {
        boolean log = true;
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            try (BufferedReader in = new BufferedReader(new FileReader(source))) {
                while (in.ready()) {
                    String s = in.readLine();
                    if (log && (s.contains("400") || s.contains("500"))) {
                        log = false;
                        out.print(s.replaceAll("\\d{3}\\s", "") + "; ");
                    } else if (!log && (s.contains("300") || s.contains("200"))) {
                        log = true;
                        out.println(s.replaceAll("\\d{3}\\s", "") + "; ");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        String sourse = "C:\\projects\\job4j_design\\log\\source.txt";
        String target = "C:\\projects\\job4j_design\\log\\target.txt";
        Analysis analysis = new Analysis();
        analysis.unavailable(sourse, target);
        sourse = "C:\\projects\\job4j_design\\log\\source_2.txt";
        target = "C:\\projects\\job4j_design\\log\\target_2.txt";
        analysis.unavailable(sourse, target);
    }
}
