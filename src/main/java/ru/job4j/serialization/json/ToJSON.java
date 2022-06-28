package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ToJSON {
    public static void main(String[] args) {
        final Animal animal = new Animal(true, 5, "tiger",
                new String[] {"p-p-p-p", "mp-p-p-p"});
        /**
         *  Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(animal));
        /**
         *  Модифицируем json-строку */
        final String animalJson =
                "{"
                        + "\"predator\":true,"
                        + "\"age\":5,"
                        + "\"name\":tiger,"
                        + "\"voice\":"
                        + "[\"p-p-p-p\",\"mp-p-p-p\"]"
                        + "}";
        final Animal animalMod = gson.fromJson(animalJson, Animal.class);
        System.out.println(animalMod);
    }
}
