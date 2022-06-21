package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private static Search search = new Search();
    private static ArgsName names = new ArgsName();

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
              for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toAbsolutePath().toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                } catch (Exception e) {
                e.printStackTrace();
                }
              }
        } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void valid(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Количество аргументов не соответствует требованиям задачи!");
        }
        if (!Files.exists(Paths.get(names.get("d")))) {
            throw new IllegalArgumentException("Отсутствуют аргументы: Каталог поиска! ");
        }
        if (!new File(names.get("d")).isDirectory()) {
            throw new IllegalArgumentException("Отсутствует каталог! ");
        }
        if (!names.get("e").contains(".")) {
            throw new IllegalArgumentException("Отсутствуют аргументы: Расширение файла! ");
        }
        if (!names.get("o").contains(".zip")) {
            throw new IllegalArgumentException("Отсутствуют аргументы: output - во что проходит архивация! ");
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zips = new Zip();
        names.parse(args);
        valid(args);
        zips.packFiles(search.search(Path.of(names.get("d")), p -> p.toFile().getName().endsWith(names.get("e"))), Path.of(names.get("o")));
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("pom.xml"),
                new File("pom.zip")
        );
    }
}