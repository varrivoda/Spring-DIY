package com.example;

import lombok.SneakyThrows;

import java.lang.invoke.SerializedLambda;
import java.util.HashMap;
import java.util.Map;

public class ObjectFactory {
    private static final ObjectFactory $$instance = new ObjectFactory();
    private final Config config = new JavaConfig("com.example", new HashMap<>(Map.of(Policeman.class, AngryPoliceman.class)));

    public static ObjectFactory getInstance() {
        return $$instance;
    }

    @SneakyThrows
    public <T> T createObject(Class <T> type){
        Class <?extends T> implClass = type;
        if(type.isInterface()){
            implClass = config.getImplClass(type);
        }
        return implClass.getDeclaredConstructor().newInstance();
    }
}
