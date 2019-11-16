package com.company.dao;

import com.company.model.DiscriminationColumn;
import com.company.model.DiscriminatorValue;
import com.company.model.TableName;
import com.company.utils.ReflectionUtils;

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

    private T createEntity(ResultSet resultSet, List<Field> fields) {
        T entity;
        try {
            entity = clazz.getConstructor().newInstance();
            for (Field field : fields) {
                String name = field.getName();
                try {
                    String value = resultSet.getString(name);
                    Class type = field.getType();
                    field.set(entity, type.isEnum()
                            ? type.getDeclaredMethod("fromString", String.class).invoke(null, value)
                            : type.getConstructor(String.class).newInstance(value));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (InstantiationException
                | InvocationTargetException
                | NoSuchMethodException
                | IllegalAccessException e) {
            e.printStackTrace();
            entity = null;
        }
        return entity;
    }

    private List<T> resultSetToList(ResultSet resultSet) throws SQLException {
        DiscriminationColumn columnAnnotation = clazz.getAnnotation(DiscriminationColumn.class);
        String discriminatorColumn = columnAnnotation != null ? columnAnnotation.name() : null;
        DiscriminatorValue discriminatorAnnotation = clazz.getAnnotation(DiscriminatorValue.class);
        String discriminator = discriminatorAnnotation != null ? discriminatorAnnotation.value() : null;

        List<Field> fields = new ArrayList<>();
        ReflectionUtils.getAllFields(fields, clazz);

        for(Field field: fields) {
            field.setAccessible(true);
        }

        List<T> list = new ArrayList<>();
        resultSet.beforeFirst();
        while (resultSet.next()) {
            if(discriminatorColumn == null || resultSet.getString(discriminatorColumn).equals(discriminator)) {
                T entity = createEntity(resultSet, fields);
                list.add(entity);
            }
        }
        return list;
    }

    public List<T> getEntityList() throws SQLException {
        TableName tableAnnotation = clazz.getAnnotation(TableName.class);

        String sql = String.format("SELECT * FROM public.%s", tableAnnotation.name());
        PreparedStatement preparedStatement = connection.prepareStatement(
                sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSetToList(resultSet);
    }

    public String getEntityErrorMessage() {
        return ex.toString();
    }

    public T getEntity(Long id) throws SQLException {
        T entity;
        TableName tableAnnotation = clazz.getAnnotation(TableName.class);

        String sql = String.format("SELECT * FROM public.%s WHERE id = ?", tableAnnotation.name());
        PreparedStatement preparedStatement = connection.prepareStatement(
                sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY
        );
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
