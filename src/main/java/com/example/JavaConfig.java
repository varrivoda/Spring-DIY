package com.example;

import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

public class JavaConfig implements Config {
    private final Map<Class, Class> ifcToImplClass;
    private Reflections scanner;

    public JavaConfig(String packageToScan, Map ifcToImplClass) {
        scanner = new Reflections(packageToScan);
        this.ifcToImplClass= ifcToImplClass;
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        return ifcToImplClass.computeIfAbsent(ifc, aClass->{
                Set<Class<?extends T>> classes = scanner.getSubTypesOf(ifc);
                if(classes.size() != 1){
                    throw new RuntimeException(ifc.getSimpleName() + " has NO or more than one impls!");
                }
                return classes.iterator().next();
            });
    }
}
