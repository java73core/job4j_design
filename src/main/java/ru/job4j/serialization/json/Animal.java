package ru.job4j.serialization.json;

import java.util.Arrays;

public class Animal {
    private final boolean predator;
    private final int age;
    private final String name;
    private final String[] voice;

    public Animal(boolean predator, int age, String name, String[] voice) {
        this.predator = predator;
        this.age = age;
        this.name = name;
        this.voice = voice;
    }

    @Override
    public String toString() {
        return "Animal{"
               + "predator=" + predator
               + ", age=" + age
               + ", name='" + name + '\''
               + ", voice=" + Arrays.toString(voice)
               + '}';
    }
}
