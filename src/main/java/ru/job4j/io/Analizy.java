package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        boolean flag = true;
        String time = null;
        List<String> list = new ArrayList<>();
        List<String> tmpList = new ArrayList<>();
            try (BufferedReader read = new BufferedReader(new FileReader(source));
                 PrintWriter write = new PrintWriter(new FileOutputStream(target))) {
                 read.lines().forEach(tmpList::add);
                 for (String str: tmpList) {
                    String[] log = str.split(" ");
                    if ("200".equals(log[0]) || "300".equals(log[0])) {
                        if (!flag) {
                            flag = true;
                            list.add(time + ";" + log[1]);
                        }
                    } else {
                        if (flag) {
                            flag = false;
                            time = log[1];
                        }
                    }
                }
                    write.print(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
            Analizy analizy = new Analizy();
            analizy.unavailable("unavailable.csv", "result.txt");
    }
}