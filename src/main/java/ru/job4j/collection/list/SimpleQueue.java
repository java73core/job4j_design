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
            if (size2 == 0) {
                while (size1 > 0) {
                    out.push(in.pop());
                    size1--; size2++;
                }
            }
            size2--;
            return out.pop();
    }

    /**
     * Метод добавления элемента в стек
     * @param value
     */
    public void push(T value) {
        in.push(value);
        size1++;
    }
}