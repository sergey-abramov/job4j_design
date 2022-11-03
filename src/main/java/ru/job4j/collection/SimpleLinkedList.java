package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;

    private int modCount;

    private Node<E> first;

    private Node<E> last;

    @Override
    public void add(E value) {
     Node<E> temp = last;
     Node<E> newNode = new Node<>(temp, value, null);
     last = newNode;
     if (temp == null) {
         first = newNode;
     } else {
         temp.next = newNode;
     }
     size++;
     modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> temp;
        if (index < size / 2) {
            temp = first;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = last;
            for (int i = size - 1; i > index; i--) {
                temp = temp.prev;
            }
        }
        return temp.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            final int expectedModCount = modCount;
            Node<E> s = first;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return s != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> temp = s;
                s = s.next;
                return temp.item;
            }
        };
    }
}
