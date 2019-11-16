package com.company.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtils {
    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }
}
