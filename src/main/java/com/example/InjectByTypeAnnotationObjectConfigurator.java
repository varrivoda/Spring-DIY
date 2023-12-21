package com.example;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {
    ApplicationContext context;

    @SneakyThrows
    @Override
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class)){
                field.setAccessible(true);
                Object object = context.getObject(field.getType());//ObjectFactory.getInstance().createObject(field.getType());
                field.set(t, object);
            }
        }
    }
}
