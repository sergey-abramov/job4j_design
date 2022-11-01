package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size = 0;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length <= size) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        index = Objects.checkIndex(index, container.length);
        T rsl = container[index];
        container[index] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        T rsl = container[index];
        System.arraycopy(container, index + 1,
              container, index, container.length - 1 - index);
        container[container.length - 1] = null;
        size--;
        modCount++;
      return rsl;
    }

    @Override
    public T get(int index) {
        index = Objects.checkIndex(index, container.length);
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
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
                return s < container.length && expectedModCount != 0;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[s++];
            }
        };
    }
}
