package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class ToJSON {
    public static void main(String[] args)  throws Exception {
        Animal animal = new Animal(true, 5, "tiger", new String[] {"p-p-p-p", "mp-p-p-p"});
        JAXBContext context = JAXBContext.newInstance(Animal.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(animal, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Animal result = (Animal) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}

