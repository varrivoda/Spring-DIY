package com.example;

public class CoronaDesinfector {
    Announcer announcer = new ConsoleAnnouncer();
    Policeman policeman= new PolicemanImpl();

    public void start(Room room){
        announcer.announce("Начинаем дезинфекцию, все вон!");
        policeman.makePeopleLeaveRoom();
        desinfect(room);
        announcer.announce("Вы можете зайти обратно");
    }

    public void desinfect(Room room) {
        System.out.println(" **Читается молитва \"Корона, изыди!\" ***** Вирус низвергнут в ад**");
    }
}
