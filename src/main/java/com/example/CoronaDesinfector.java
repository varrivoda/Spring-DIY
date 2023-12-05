package com.example;

public class CoronaDesinfector {
    Announcer announcer = ObjectFactory.getInstance().createObject(Announcer.class);
    Policeman policeman = ObjectFactory.getInstance().createObject(Policeman.class);

    public void start(Room room){
        announcer.announce("�������� �����������, ��� ���!");
        policeman.makePeopleLeaveRoom();
        desinfect(room);
        announcer.announce("�� ������ ����� �������");
    }

    public void desinfect(Room room) {
        System.out.println(" **�������� ������� \"������, �����!\" ***** ����� ���������� � ��**");
    }
}
