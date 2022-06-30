package ru.job4j.generics.map;

import ru.job4j.collection.list.Node;
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
        if ((double) (count / capacity) >= LOAD_FACTOR) {
            expand();
            return false;
        } else {
            int index = indexFor(key.hashCode());
            MapEntry<K, V> newNode = new MapEntry<>(key, value);
            if (table[index] == null) {
                table[index] = newNode;
                modCount++;
                return true;
            }
        }
       return false;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        int newSize = table.length * 2;
        MapEntry<K, V>[] newMapEntry = new MapEntry[newSize];
        for (MapEntry<K, V> node : table) {
            if (node != null) {
                newMapEntry[indexFor((Integer) node.key)] = node;
            }
        }
        table = newMapEntry;
    }

    @Override
    public V get(K key) {
        int index = indexFor((Integer) key);
        if (table[index] != null) {
            if (table[index].key.equals(key)) {
                return (V) table[index].value;
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor((Integer) key);
        if (table[index] != null) {
            if (table[index].key.equals(key)) {
                table[index] = null;
                modCount++;
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator() {

            private int exceptedModCount = modCount;
            private int index = 0;
            @Override
            public boolean hasNext() {
                for (int i = index; i < table.length; i++) {
                    if (table[i] != null) {
                        index = i;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (exceptedModCount != modCount) {
                    throw new ConcurrentModificationException();
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
