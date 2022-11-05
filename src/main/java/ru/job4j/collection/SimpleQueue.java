package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int sizeIn;
    private int sieOut;

    public T poll() {
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
