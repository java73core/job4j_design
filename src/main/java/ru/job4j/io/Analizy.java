package ru.job4j.io;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class Analizy {
    public void unavailable(String source, String target) {
        AtomicBoolean flag = new AtomicBoolean(true);
        var ref = new Object() {
            String time = "";
        };
            try (BufferedReader read = new BufferedReader(new FileReader(source));
                 PrintWriter write = new PrintWriter(new FileOutputStream(target))) {

            read.lines().filter(s -> (!s.isEmpty()))
                        .map(s -> s.split(" ", 2))
                        .forEach(s -> {
                                 if ("200".equals(s[0]) || "300".equals(s[0])) {
                                     if (!flag.get()) {
                                          flag.set(true);
                                          write.print(ref.time + "; " + s[1] + "\n");
                                    }
                                 } else {
                                        if (flag.get()) {
                                            flag.set(false);
                                            ref.time = s[1];
                                         }
                                 }
                        });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
            Analizy analizy = new Analizy();
            analizy.unavailable("unavailable.csv", "result.txt");
    }
}