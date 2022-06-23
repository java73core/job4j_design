package ru.job4j.io;

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

    public Integer size() {
        return values.size();
    }

    public void parse(String[] args) {
        for (String s : args) {
            validString(s);
            String[] str = s.split("=", 2);
            str[0] = str[0].replace("-", "");
            valid(str);
            values.put(str[0], str[1]);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Нет аргументов!");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void valid(String[] arg) {
        if (Objects.equals(arg[1], "")) {
            throw new IllegalArgumentException("нет такого значения!");
        }
        if (Objects.equals(arg[0], "")) {
            throw new IllegalArgumentException("нет такого ключа!");
        }
    }

    public static void validString(String str) {
        if (!str.contains("=")) {
            throw new IllegalArgumentException("неправильный формат аргумента!");
        }
        if (!str.contains("-")) {
            throw new IllegalArgumentException("Строка должна начинаться с символа \'-\' ");
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}