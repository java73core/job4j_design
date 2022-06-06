package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        boolean flag = true;
        String time = null;
        List<String> list = new ArrayList<>();

            try (BufferedReader read = new BufferedReader(new FileReader(source));
                 PrintWriter write = new PrintWriter(new FileOutputStream(target))) {
                String line;
                while ((line = read.readLine())!= null) {
                    String[] log = line.split(" ");
                    if (log[0].equals("200") || log[0].equals("300")) {
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