package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        int index = indexFor(hash(key));
        if (key != null && table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        } else if (table[0] == null) {
            table[0] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        if ((double)count/capacity >= LOAD_FACTOR) {
            expand();
        }
        return rsl;
    }

    private int hash(K key) {
        return (key == null) ? 0 : (key.hashCode()) ^ (key.hashCode() >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] temp = new MapEntry[capacity];
        for (MapEntry<K, V> e : table) {
            if (e != null) {
                int i = indexFor(hash(e.key));
                temp[i] = e;
            }
        }
        table = temp;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int i = indexFor(hash(key));
        MapEntry<K, V> e = table[i];
        if (e != null && hash(e.key) == hash(key)
                && Objects.equals(key, e.key)) {
            rsl = e.value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int i = indexFor(hash(key));
        MapEntry<K, V> e = table[i];
        if (e != null && hash(e.key) == hash(key)
                && Objects.equals(key, e.key)) {
            table[i] = null;
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int point = 0;
            final int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < capacity && table[point] == null) {
                    point++;
                }
                return point < capacity && table[point] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
