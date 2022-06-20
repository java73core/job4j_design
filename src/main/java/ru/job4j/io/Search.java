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
        Search search = new Search();
        search.valid(args);
        Path start = Paths.get(args[0]);
        search.search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public void valid(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Количество аргументов не соответствует требованиям задачи!");
        }
        if (!Files.exists(Paths.get(args[0]).toAbsolutePath())) {
            throw new IllegalArgumentException("Отсутствуют аргументы: Каталог поиска! ");
        }
        if (!args[1].contains(".")) {
            throw new IllegalArgumentException("Отсутствуют аргументы: Расширение файла! ");
        }
    }
}