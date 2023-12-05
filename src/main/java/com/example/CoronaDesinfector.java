package com.example;

public class CoronaDesinfector {
    Announcer announcer = ObjectFactory.getInstance().createObject(Announcer.class);
    Policeman policeman = ObjectFactory.getInstance().createObject(Policeman.class);

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
