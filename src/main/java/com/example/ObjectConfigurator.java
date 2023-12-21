package com.example;

import lombok.SneakyThrows;

public interface ObjectConfigurator {
    @SneakyThrows
    void configure(Object t, ApplicationContext context);
}
