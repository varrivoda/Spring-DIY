package com.example;

import lombok.SneakyThrows;

import java.lang.invoke.SerializedLambda;

public class ObjectFactory {
    private static final ObjectFactory $$instance = new ObjectFactory();
    private final Config config = new JavaConfig("com.example");

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
