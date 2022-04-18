package ru.job4j.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> listFile = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().forEach(listFile::add);
            listFile.removeIf(str -> Arrays.stream(str.split(" ")).skip(8).limit(1).noneMatch("404"::equals));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listFile;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String str : log) {
                out.printf("%s%n", str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}