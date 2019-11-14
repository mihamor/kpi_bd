package com.company.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IDAOImpl<T> {
    T getEntity(Long id) throws SQLException;

    List<T> getEntityList() throws SQLException;

    String getEntityErrorMessage();
}
