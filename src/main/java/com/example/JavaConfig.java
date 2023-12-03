package com.example;

import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.util.Set;

public class JavaConfig implements Config {
    Reflections scanner;

    public JavaConfig(String packageToScan) {
        scanner = new Reflections(packageToScan);
    }

    @SneakyThrows
    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        Set<Class<?extends T>> classes = scanner.getSubTypesOf(ifc);
        if(classes.size() != 1){
            throw new Exception("Zero or more than one implementation of " +ifc.getName());
        }
        return classes.iterator().next();

    }

}
