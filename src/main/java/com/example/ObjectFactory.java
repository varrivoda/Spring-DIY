package com.example;

import lombok.Setter;
import lombok.SneakyThrows;

import javax.naming.CannotProceedException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.annotation.Annotation;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ObjectFactory {
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    private List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();

    private final ApplicationContext context;

    @SneakyThrows
    public ObjectFactory(ApplicationContext �ontext) {
        this.context = �ontext;
        //config =context.getConfig();// new JavaConfig("com.example", new HashMap<>(Map.of(Policeman.class, AngryPoliceman.class)));

        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }

        for (Class<? extends ProxyConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ProxyConfigurator.class)) {
            proxyConfigurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class <T> implClass){

        T t = create(implClass);

        configure(t);

        invokeInit(implClass, t);

        t = wrapWithProxyIfNeeded(implClass, t);

        return t;
    }

    private <T> T wrapWithProxyIfNeeded(Class<T> implClass, T t) {
        for(ProxyConfigurator proxyConfigurator: proxyConfigurators){
            t = (T) proxyConfigurator.replaceWithProxyIfNeeded(t, implClass);
        }
        return t;
    }

    private <T> void invokeInit(Class<T> implClass, Object t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getMethods()) {
            if(method.isAnnotationPresent(PostConstruct.class)){
                method.invoke(t);
            }
        }
    }

    private void configure(Object t) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
    }

    private <T> T create(Class<T> implClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        T t = implClass.getDeclaredConstructor().newInstance();
        return t;
    }
}
