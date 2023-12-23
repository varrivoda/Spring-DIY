package com.example;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    public ApplicationContext(Config config) {
        this.config = config;
    }
    @Setter
    private ObjectFactory factory;
    @Getter
    private Config config;
    private Map<Class, Object> cache = new ConcurrentHashMap<>();

    public <T> T getObject(Class <T> type){
        if(cache.containsKey(type)){
            return (T) cache.get(type);
        }

        Class <?extends T> implClass = type;
        if(type.isInterface()){
            implClass = config.getImplClass(type);
        }

        T t = factory.createObject(implClass);

        if(implClass.isAnnotationPresent(Singleton.class)){
            cache.put(type, t);
        }

        return t;
    }
}
