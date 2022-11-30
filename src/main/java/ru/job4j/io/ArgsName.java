package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key not found");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Argument not found");
        }
        for (String arg : args) {
            if (!arg.contains("=")) {
                throw new IllegalArgumentException("Argument has no character =");
            }
            String[] arg1 = arg.split("=", 2);
            if (arg1[1].length() == 0) {
                throw new IllegalArgumentException("Argument has no value");
            }
            if (!arg1[0].startsWith("-")) {
                throw new IllegalArgumentException("Argument not starts with -");
            }
            if (arg1[0].startsWith("-") && arg1[0].endsWith("-")) {
                throw new IllegalArgumentException("Argument has no key");
            }
            values.put(arg1[0].substring(1), arg1[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }

}
