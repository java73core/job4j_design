package ru.job4j.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> listFile = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
        List<String> tempList =  in.lines().filter(x -> x.contains("404")).collect(Collectors.toList());
          for (String str : tempList) {
              if (Arrays.stream(str.split(" ")).skip(8).limit(1).anyMatch("404"::equals)) {
                  listFile.add(str);
              }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listFile;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}