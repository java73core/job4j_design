package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "animal")
@XmlAccessorType(XmlAccessType.FIELD)
public class Animal {
    @XmlAttribute
    private boolean predator;
    @XmlAttribute
    private int age;
    private String name;
    private String[] voice;

    public Animal() {
    }

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

    public static void main(String[] args) throws JAXBException {
        final Animal animal = new Animal(true, 5, "tiger", new String[] {"p-p-p-p", "mp-p-p-p"});
        JAXBContext context = JAXBContext.newInstance(Animal.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(animal, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
        }
    }
}
