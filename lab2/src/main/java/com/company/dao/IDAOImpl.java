package com.company.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAOImpl<T> {
    T getEntity(Long id) throws SQLException;

    List<T> getEntityList() throws SQLException;

    T updateEntity(T entity) throws SQLException, IllegalAccessException;

    String getEntityErrorMessage();
}
