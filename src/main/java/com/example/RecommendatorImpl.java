package com.example;

public class RecommendatorImpl implements Recommendator {
    @InjectProperty
    private String alcohol;

    @Override
    public void recommend() {
        System.out.println("*Реклама* Для защиты от COVID-19 пейте " + alcohol);
    }
}
