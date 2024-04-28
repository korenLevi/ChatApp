package com.hit.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface Dao<T> {
    T get(Long id);

    ArrayList<T> getAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
}
