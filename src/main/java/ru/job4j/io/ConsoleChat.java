package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        String ln = System.lineSeparator();
        String question = String.format(scanner.nextLine() + ln);
        while (!scanner.hasNext(OUT)) {
            if (scanner.hasNext(STOP)) {
                while (!scanner.hasNext(CONTINUE)) {
                    log.add(question);
                    scanner.nextLine();
                }
            }
            log.add(question);
            String s = readPhrases().get(new Random().nextInt(readPhrases().size()));
            System.out.println(s);
            log.add(s + ln);
            scanner.nextLine();
            }
        log.add(question);
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            while (in.ready()) {
                String s = in.readLine();
                rsl.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path))) {
            for (String s : log) {
                out.write(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\bot_log.txt",
                "C:\\projects\\job4j_design\\string_bot.txt");
        cc.run();
    }
}
