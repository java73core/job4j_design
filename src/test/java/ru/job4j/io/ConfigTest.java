package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithValue() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password"), is("password"));
    }

    @Test
    public void whenUrlWithAddress() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.url"),
                is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenNotCorrectPair() {
        String path = "./data/notcorrect.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenEmptyConfigFile() {
        String path = "./data/empty.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.url"),
                is(Matchers.nullValue()));
    }

    @Test
    public void whenTwoEqualsConfigFile() {
        String path = "./data/twoequals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("user"),
                is("userName=Petr"));
    }
}