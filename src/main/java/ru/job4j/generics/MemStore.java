package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (storage.containsKey(id)) {
            storage.put(id, model);
        }
        return storage.containsValue(model);
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = storage.containsKey(id);
        storage.remove(id);
        return rsl;
    }

    @Override
    public T findById(String id) {
            return storage.get(id);
    }
}
