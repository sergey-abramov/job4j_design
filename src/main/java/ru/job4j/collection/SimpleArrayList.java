package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length > size) {
            container[size] = value;
            size++;
        } else {
            container = Arrays.copyOf(container, container.length * 2);
        }
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        index = Objects.checkIndex(index, container.length);
        container[index] = newValue;
        return container[index];
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
      System.arraycopy(container, index + 1,
              container, index, container.length - 1 - index);
        container[container.length - 1] = null;
      return container[container.length - 1];
    }

    @Override
    public T get(int index) {
        index = Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public int size() {
        this.size = container.length;
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                return iterator().hasNext();
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return iterator().next();
            }
        };
    }
}
