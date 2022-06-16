package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> doubleFile = new HashMap<>();

    public Map<FileProperty, List<Path>> getDoubleFile() {
        return doubleFile;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            FileProperty fp = new FileProperty(file.toFile().length(), file.toFile().getName());
            List<Path> dub = new ArrayList<>();
            if (doubleFile.containsKey(fp)) {
                    doubleFile.get(fp).add(file);
            } else {
                    dub.add(file);
                   doubleFile.put(fp, dub);
            }
        return super.visitFile(file, attrs);
    }
}