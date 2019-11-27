package com.company.dao;

import com.company.model.*;
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
                Column columnAnnotation = field.getAnnotation(Column.class);
                String name = columnAnnotation != null ? columnAnnotation.name() : field.getName();
                try {
                    String value = resultSet.getString(name);
                    Class type = field.getType();
                    if(value != null) {
                        field.set(entity, type.isEnum()
                                ? type.getDeclaredMethod("fromString", String.class).invoke(null, value)
                                : type.getConstructor(String.class).newInstance(value));
                    }
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
        List<Field> fields = new ArrayList<>();
        ReflectionUtils.getAllFields(fields, clazz);

        for(Field field: fields) {
            field.setAccessible(true);
        }

        List<T> list = new ArrayList<>();
        resultSet.beforeFirst();
        while (resultSet.next()) {
            T entity = createEntity(resultSet, fields);
            list.add(entity);
        }
        return list;
    }

    public List<T> getEntityList() throws SQLException {
        Table tableAnnotation = clazz.getAnnotation(Table.class);

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
        Table tableAnnotation = clazz.getAnnotation(Table.class);

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

    public T updateEntity(T entity) throws SQLException, IllegalAccessException {
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        List<Field> fields = new ArrayList<>();
        ReflectionUtils.getAllFields(fields, clazz);

        Field primaryField = null;
        for(Field field: fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Primary.class)) {
                primaryField = field;
            }
        }

        Column columnAnnotation = primaryField.getAnnotation(Column.class);
        String name = columnAnnotation != null ? columnAnnotation.name() : primaryField.getName();

        String sql = String.format("UPDATE public.%s SET %s WHERE %s = ? RETURNING *;",
                tableAnnotation.name(), getFieldSqlString(entity), name);

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, (Long) primaryField.get(entity));
        ResultSet resultSet = preparedStatement.executeQuery();

        T updatedEntity;
        try {
            updatedEntity = resultSetToList(resultSet).get(0);
        } catch (IndexOutOfBoundsException ex) {
            updatedEntity = null;
        }

        return updatedEntity;
    }

    private String getFieldSqlString(T entity) throws IllegalAccessException {
        List<Field> fields = new ArrayList<>();
        ReflectionUtils.getAllFields(fields, clazz);


        String sql = new String();
        for(int fieldId = 0; fieldId < fields.size(); fieldId++) {
            Field field = fields.get(fieldId);
            field.setAccessible(true);
            sql += String.format("%s = %s", field.getName(), field.get(entity));
            if(fieldId != fields.size() - 1) {
                sql += ", ";
            }
        }

        return sql;
    }

}
