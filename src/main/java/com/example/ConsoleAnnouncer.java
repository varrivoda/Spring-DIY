package com.example;

public class ConsoleAnnouncer implements Announcer {
    @InjectByType
    Recommendator recommendator;// = ObjectFactory.getInstance().createObject(Recommendator.class);
    @Override
    public void announce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }


}
