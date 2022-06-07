package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    SimpleMap<User, String> simpleMap = new SimpleMap();
    User vasya = new User("Vasya", 2, new GregorianCalendar(1999, 1, 1));

    @Test
    public void get() {
        simpleMap.put(vasya, "Value of Vasya");
        var rsl = simpleMap.get(vasya);
        assertThat(rsl, is("Value of Vasya"));
    }

    @Test
    public void remove() {
        simpleMap.put(vasya, "Value of Vasya");
        var rsl = simpleMap.remove(vasya);
        assertTrue(rsl);
    }
}