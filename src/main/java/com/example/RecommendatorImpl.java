package com.example;

public class RecommendatorImpl implements Recommendator {
    @InjectProperty
    private String alcohol;

    @Override
    public void recommend() {
        System.out.println("*�������* ��� ������ �� COVID-19 ����� " + alcohol);
    }
}
