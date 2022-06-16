package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("нет такого ключа!");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        valid(args);
        for (String s : args) {
            if (!s.contains("=")) {
                throw new IllegalArgumentException("неправильный формат аргумента!");
            }
            String[] str = s.split("=", 2);
            str[0] = str[0].replace("-", "");
                    if (Objects.equals(str[1], "")) {
                            throw new IllegalArgumentException("нет такого значения!");
                    }
                    if (Objects.equals(str[0], "")) {
                        throw new IllegalArgumentException("нет такого ключа!");
                    }
            values.put(str[0], str[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void valid(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Нет аргументов!");
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}