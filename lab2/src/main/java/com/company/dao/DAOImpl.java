package com.company.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class DAOImpl<T> implements IDAOImpl<T> {

    private Class<T> clazz;

    public DAOImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> getEntityList() {
        return null;
    }

    public T getEntity(Long id) {
        String tableName = clazz.getName();

        return null;
    }
}
