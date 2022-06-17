package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        valid(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void valid(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Нет аргументов!");
        }
        if (args.length != 2) {
            throw new IllegalArgumentException("Количество аргументов не соответствует требованиям задачи!");
        }
        if (!Objects.equals(args[0], ".")) {
            throw new IllegalArgumentException("Отсутствуют аргументы: Каталог поиска! ");
        }
        if (!Objects.equals(args[1], ".class")) {
            throw new IllegalArgumentException("Отсутствуют аргументы: Расширение файла! ");
        }
    }
}