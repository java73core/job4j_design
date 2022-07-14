package ru.job4j.generics.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Mapd<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean flag = false;
        if ((double) (count / capacity) >= LOAD_FACTOR) {
            expand();
        }
            int index = indexFor(key.hashCode());
            MapEntry<K, V> newNode = new MapEntry<>(key, value);
            if (table[index] == null) {
                table[index] = newNode;
                modCount++;
                flag = true;
        }
       return flag;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash(hash) % (capacity - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newMapEntry = new MapEntry[capacity];
        for (MapEntry<K, V> node : table) {
            if (node != null) {
                newMapEntry[indexFor(node.key.hashCode())] = node;
            }
        }
        table = newMapEntry;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        V flag = null;
        if (table[index] != null) {
            if (table[index].key.hashCode() == key.hashCode()) {
                if (table[index].key.equals(key)) {
                    flag = table[index].value;
                }
            }
        }
        return flag;
    }

    @Override
    public boolean remove(K key) {
        boolean flag = false;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            if (table[index].key.hashCode() == key.hashCode()) {
                if (table[index].key.equals(key)) {
                    table[index] = null;
                    modCount++;
                    count--;
                    flag = true;
                }
            }
        }
        return flag;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator() {
            private int exceptedModCount = modCount;
            private int index = 0;
            @Override
            public boolean hasNext() {
                boolean flag = false;
                if (exceptedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = index; i < table.length; i++) {
                    if (table[i] != null) {
                        index = i;
                        flag = true;
                    }
                }
                return flag;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
