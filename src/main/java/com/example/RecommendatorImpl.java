package com.example;

@Deprecated
@Singleton
public class RecommendatorImpl implements Recommendator {
    @InjectProperty("whisky")
    private String alcohol;

    public RecommendatorImpl() {
        System.out.println("Recommendator was created");
    }

    @Override
    public void recommend() {
        System.out.println("*Реклама* Для защиты от COVID-19 пейте " + alcohol);
    }
}
