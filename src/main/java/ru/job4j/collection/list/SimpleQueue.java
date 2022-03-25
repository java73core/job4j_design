package ru.job4j.collection.list;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size1 = 0;
    private int size2 = 0;

    /**
     * метод удаления первого элемента из стека. Первый элемент стека, он же последний добавленный.
     * @return удалённый элемент
     */
    public T poll() {
        while (size1 > 0) {
            out.push(in.pop());
            size1--; size2++;
        }
        T t = out.pop(); size2--;
        while (size2 > 0) {
            in.push(out.pop());
            size1++; size2--;
        }
        return t;
    }

    /**
     * Метод добавления элемента в стек
     * @param value - добавляемый элемент
     */
    public void push(T value) {
        in.push(value);
        size1++;
    }
}