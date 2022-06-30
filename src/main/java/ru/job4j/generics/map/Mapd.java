package ru.job4j.generics.map;

public interface Mapd<K, V> extends Iterable<K> {
    boolean put(K key, V value);

    V get(K key);

    boolean remove(K key);
}