package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String pathPhrases;
    private final String pathLogs;
    private List<String> botAnswers = new ArrayList<>();

    public ConsoleChat(String pathPhrases, String pathLogs) {
        this.pathPhrases = pathPhrases;
        this.pathLogs = pathLogs;
        readPhrases(pathPhrases);
    }

    public void run() {
        List<String> logs = new ArrayList<>();
        boolean flagWhile = true;
        boolean flagStop = true;
        Scanner scan = new Scanner(System.in);
        String s = "Привет! О чём поговорим?";
        System.out.println(s);
        var ls = System.lineSeparator();
        logs.add(s + ls);
        while (flagWhile) {
            String input = scan.nextLine();
            logs.add(input + ls);
            if (input.equals(OUT)) {
                logs.add(OUT + ls +  "Dialog is over!");
                flagWhile = false;
                flagStop = false;
            }
            if (Objects.equals(input, STOP) && flagStop) {
                flagStop = false;
                logs.add(STOP + ls);
            }
            if (Objects.equals(input, CONTINUE) && !flagStop) {
                flagStop = true;
                logs.add(CONTINUE + ls);
            }
            if (flagStop) {
                String answer = generationBotAnswers();
                System.out.println(answer);
                logs.add(answer + ls + input);
            }
        }
        saveLog(logs);
    }

    private List<String> readPhrases(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.lines().forEach(botAnswers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botAnswers;
    }

    private String generationBotAnswers() {
        Random r = new Random();
       int i = r.nextInt(botAnswers.size());
        return botAnswers.get(i);
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(pathLogs, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C://test//phrases.txt", "C://test//logs.txt");
        cc.run();
    }
}