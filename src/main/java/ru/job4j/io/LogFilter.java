package ru.job4j.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;

public class LogFilter {
    public List<String> filter(String file) {
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
        try (FileOutputStream out = new FileOutputStream(file)) {
            for (String str : log) {
                out.write(str.getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        save(log, "404.txt");
        for (String s : log) {
            System.out.println(s);
        }
    }
}