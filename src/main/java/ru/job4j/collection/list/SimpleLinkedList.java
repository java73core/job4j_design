package ru.job4j.collection.list;

import java.util.*;

public class SimpleLinkedList<E> implements ListL<E> {
    private Node<E> node;

    private int size;

    private int modCount;
    private Node<E> last;
    private Node<E> first;

    @Override
    public void add(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        if (!(index >= 0 && index < size)) {
            throw new  IndexOutOfBoundsException();
        }
        return getNode(index).item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor = 0;
            final int expectedModCount = modCount;
            Node<E> element = first;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = element.item;
                element = element.next;
                cursor++;
                return data;
            }
        };
    }

    private Node<E> getNode(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
                return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
               x = x.prev;
            }
            return x;
        }
    }
}
