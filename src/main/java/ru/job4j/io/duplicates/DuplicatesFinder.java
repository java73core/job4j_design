package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of("C:\\test"), new DuplicatesVisitor());
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        duplicatesVisitor.getDoubleFile().values().stream().filter(x -> x.size() > 1).forEach(System.out::println);
    }
}