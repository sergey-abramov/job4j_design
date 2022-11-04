package ru.job4j.collection;

public class Node<E> {
    E item;
    Node<E> next;

    Node(E element, Node<E> next) {
        this.item = element;
        this.next = next;
    }
}
