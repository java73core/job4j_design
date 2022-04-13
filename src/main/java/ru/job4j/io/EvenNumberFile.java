package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] str = text.toString().split(System.lineSeparator());
            for (String s : str) {
                int value = Integer.parseInt(s);
                if (value % 2 == 0) {
                System.out.println(value + " is even");
                } else {
                System.out.println(value + " is not even");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
