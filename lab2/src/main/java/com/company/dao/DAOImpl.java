package com.company.dao;

import com.company.model.TableName;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOImpl<T> implements IDAOImpl<T> {

    private Class<T> clazz;
    private Connection connection;
    private Exception ex;

    public DAOImpl(Class<T> clazz, Connection connection) {
        this.clazz = clazz;
        this.connection = connection;
    }

    private List<T> resultSetToList(ResultSet resultSet) {
        Field[] fields = clazz.getDeclaredFields();
        for(Field field: fields) {
            field.setAccessible(true);
        }

        try {
            List<T> list = new ArrayList<>();
            while (resultSet.next()) {
                T dto = clazz.getConstructor().newInstance();
                for (Field field : fields) {
                    String name = field.getName();
                    try {
                        String value = resultSet.getString(name);
                        field.set(dto, field.getType().getConstructor(String.class).newInstance(value));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                list.add(dto);
            }
            return list;
        } catch (SQLException
                | IllegalAccessException
                | NoSuchMethodException
                | InstantiationException
                | InvocationTargetException
                ex) {
            this.ex = ex;
        }
        return null;
    }

    public List<T> getEntityList() throws SQLException {
        TableName tableAnnotation = clazz.getAnnotation(TableName.class);

        String sql = "SELECT * FROM public.$tableName".replace("$tableName", tableAnnotation.name());
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSetToList(resultSet);
    }

    public String getEntityErrorMessage() {
        return ex.toString();
    }

    public T getEntity(Long id) throws SQLException {
        T entity;
        TableName tableAnnotation = clazz.getAnnotation(TableName.class);

        String sql = "SELECT * FROM public.$tableName WHERE id = ?".replace("$tableName", tableAnnotation.name());
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        try {
            entity = resultSetToList(resultSet).get(0);
        } catch (IndexOutOfBoundsException ex) {
            entity = null;
        }

        return entity;
    }
}
