package com.example;

import lombok.SneakyThrows;

import javax.naming.CannotProceedException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.annotation.Annotation;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

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
        Object t =implClass.getDeclaredConstructor().newInstance();


        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);

            //Convert properties file to protertiesMap
            String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
            Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
            Map<String, String> propertiesMap = lines
                    .map(line -> line.split("="))
                    //stupit splitting, works only without whitespaces
                    .collect(toMap(arr -> arr[0], arr -> arr[1]));

            if(annotation!=null){
                //если ваннотации было значение,берем его. ≈сли не юбыло, берем из файла ѕроперти
                String value = annotation.value().isEmpty() ? propertiesMap.get(field.getName()) : propertiesMap.get(annotation.value());

                // теперь знаение надо засетить в поле
                field.setAccessible(true);
                field.set(t, value);
            }
        }
        return (T) t;
    }
}
