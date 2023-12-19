package com.example;

public class CoronaDesinfector {
    @InjectByType
    Announcer announcer;// = ObjectFactory.getInstance().createObject(Announcer.class);
    @InjectByType
    Policeman policeman;// = ObjectFactory.getInstance().createObject(Policeman.class);

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
