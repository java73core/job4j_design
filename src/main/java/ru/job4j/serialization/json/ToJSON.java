package ru.job4j.serialization.json;

import org.json.JSONObject;

public class ToJSON {
    public static void main(String[] args)  throws Exception {
        Animal animal = new Animal(true, 5, "tiger", new String[] {"p-p-p-p", "mp-p-p-p"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", animal.isPredator());
        jsonObject.put("age", animal.getAge());
        jsonObject.put("name", animal.getName());
        jsonObject.put("voice", animal.getVoice());
        System.out.println(new JSONObject(animal).toString());
    }
}


