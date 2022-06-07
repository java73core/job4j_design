package ru.job4j.io;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Analizy {
    public void unavailable(String source, String target) {
        AtomicBoolean flag = new AtomicBoolean(false);
            try (BufferedReader read = new BufferedReader(new FileReader(source));
                 PrintWriter write = new PrintWriter(new FileOutputStream(target))) {

read.lines().filter(s -> (!s.isEmpty()))
            .map(s -> s.split(" ", 2))
            .filter(s -> "400".equals(s[0]) || "500".equals(s[0]) || flag.get())
            .forEach(s -> {
                write.print(s[1] + "; ");
                flag.set(true);
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