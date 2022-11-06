package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int sizeIn;
    private int sieOut;

    public T poll() {
        if (sizeIn == 0 && sieOut == 0) {
            throw new NoSuchElementException();
        }
        if (sieOut == 0) {
            while (sizeIn != 0) {
                out.push(in.pop());
                sizeIn--;
                sieOut++;
            }
        }
        sieOut--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        sizeIn++;
    }
}
