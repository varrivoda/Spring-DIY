package com.example;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class InjectPropertyAnnotationObjectConfigurator implements ObjectConfigurator {

    Class implClass;
    Map<String, String> propertiesMap = new HashMap<>();

    @SneakyThrows
    public InjectPropertyAnnotationObjectConfigurator() {
        //Convert properties file to protertiesMap
        String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
        Stream<String> lines = new BufferedReader(new FileReader(path)).lines();

        propertiesMap = lines
                .map(line -> line.split("="))
                //stupit splitting, works only without whitespaces
                .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));

    }

    @SneakyThrows
    @Override
    public void configure(Object t) {
        implClass=t.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);
            if(annotation!=null){
                //если ваннотации было значение,берем его. ≈сли не юбыло, берем из файла ѕроперти
                String value = annotation.value().isEmpty() ? propertiesMap.get(field.getName()) : propertiesMap.get(annotation.value());

                // теперь знаение надо засетить в поле
                field.setAccessible(true);
                field.set(t, value);
            }
        }

    }
}
