package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {

    private List<String> stringList = new ArrayList<>();
    
    private List<String> stringResult = new ArrayList<>();

    public static void handle(ArgsName argsName) {
        CSVReader csvReader = new CSVReader();
        csvReader.valid(argsName);
        csvReader.readFile(argsName.get("path"));
        Scanner scanner;
        var s = "";
        StringBuilder resultString = new StringBuilder();
        List<String> art = new ArrayList<>();
        List<Integer> arguments = new ArrayList<>();
        List<String> stringArgs = new ArrayList<>();
        stringArgs.addAll(Arrays.asList(argsName.get("filter").split(",")));
           for (String str : csvReader.stringList) {
            scanner = new Scanner(new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8))).useDelimiter(argsName.get("delimiter"));
            while (scanner.hasNext()) {
                s = scanner.next();
                art.add(s);
                for (String ss : stringArgs) {
                    if (ss.equals(s)) {
                        arguments.add(art.indexOf(s));
                    }
                }
            }
            int count = arguments.size();
            for (Integer def : arguments) {
                var points = "";
                if (count > 1) {
                    count--;
                    points = ";";
                }
                csvReader.stringResult.add(art.get(def) + points);
            }
            art.clear();
            csvReader.stringResult.add(System.lineSeparator());
        }
        for (String line : csvReader.stringResult) {
            resultString.append(line);
        }
        csvReader.writeFile(resultString.toString(), argsName.get("out"));
    }

    public void valid(ArgsName args) {
        if (args.size() != 4) {
            throw new IllegalArgumentException("количество аргументов не соответствует требования задачи!");
        }
        if (!Files.exists(Paths.get(args.get("path")))) {
            throw new IllegalArgumentException("Отсутствуют аргументы: Исходный файл! ");
        }
        if (!args.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Неверное расширение файла! ");
        }
        if (!Files.exists(Paths.get(args.get("out")))) {
            if (!args.get("out").equals("stdout")) {
                throw new IllegalArgumentException("Отсутствуют аргументы: аргумент вывода в консоль! ");
            }
            throw new IllegalArgumentException("Отсутствуют аргументы: файл для записи! ");
        }
    }

    private List<String> readFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.lines().forEach(stringList::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringList;
    }

    private void writeFile(String data, String path) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            pw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=c://test//source.csv", "-delimiter=;", "-out=c://test//target.csv", "-filter=name,age"
        });
        CSVReader.handle(argsName);
    }
}