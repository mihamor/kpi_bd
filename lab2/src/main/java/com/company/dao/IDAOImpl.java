package com.company.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDAOImpl<T> {
    T getEntity(Long id);
    List<T> getEntityList();
    T updateEntity(T entity);
    T deleteEntity(Long id);
    T insertEntity(T entity);

    String getEntityErrorMessage();
    public List<T> resultSetToList(ResultSet resultSet);
}
