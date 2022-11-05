package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;

    public T poll() {
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        size++;
        for (int i = size; i > 0; i--) {
            T rsl = in.pop();
            size--;
            out.push(rsl);
        }
    }
}
