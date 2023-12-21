package com.example;

import lombok.SneakyThrows;

import javax.naming.CannotProceedException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.annotation.Annotation;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ObjectFactory {
    private static ObjectFactory $$instance = new ObjectFactory();
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    ApplicationContext context;

    @SneakyThrows
    public ObjectFactory(ApplicationContext ñontext) {
        this.context = ñontext;
        config =context.getConfig();// new JavaConfig("com.example", new HashMap<>(Map.of(Policeman.class, AngryPoliceman.class)));

        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    public static ObjectFactory getInstance() {
        return $$instance;
    }

    @SneakyThrows
    public <T> T createObject(Class <T> implClass){

        Object t =implClass.getDeclaredConstructor().newInstance();

        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));

        return (T) t;
    }
}
