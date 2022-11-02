package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size = 0;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void copy() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, container.length + 1);
        }
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public void add(T value) {
        if (container.length <= size) {
            copy();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T rsl = get(index);
        container[index] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        T rsl = get(index);
        System.arraycopy(container, index + 1,
              container, index, container.length - 1 - index);
        container[container.length - 1] = null;
        size--;
        modCount++;
      return rsl;
    }

    @Override
    public T get(int index) {
        index = Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final int expectedModCount = modCount;
            int s = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return s < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[s++];
            }
        };
    }
}
