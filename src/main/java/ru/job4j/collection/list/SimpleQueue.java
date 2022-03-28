package ru.job4j.collection.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int sizeIn = 0;
    private int sizeOut = 0;

    /**
     * метод удаления первого элемента из стека. Первый элемент стека, он же последний добавленный.
     * @return удалённый элемент
     */
    public T poll() {
        if (sizeIn == 0) {
            throw new NoSuchElementException();
        }
            if (sizeOut == 0) {
                while (sizeIn > 0) {
                    out.push(in.pop());
                    sizeIn--;
                    sizeOut++;
                }
            }
            sizeOut--;
            return out.pop();
    }

    /**
     * Метод добавления элемента в стек
     * @param value
     */
    public void push(T value) {
        in.push(value);
        sizeIn++;
    }
}