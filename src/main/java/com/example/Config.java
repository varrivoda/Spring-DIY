package com.example;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> type);
}
