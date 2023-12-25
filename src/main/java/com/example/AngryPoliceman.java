package com.example;

public class AngryPoliceman implements Policeman {
    @PostConstruct
    public void init() {
        System.out.println(recommendator.getClass()
                + "@ " + this.getClass().getName()
                + " @init");
    }

    @InjectByType
    Recommendator recommendator;

    @Override
    public void makePeopleLeaveRoom() {
        System.out.println(recommendator.getClass());
        System.out.println("Angry policeman shooting in the air");
    }
}
