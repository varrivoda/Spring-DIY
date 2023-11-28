package com.example;

public class CoronaDesinfector {
    Announcer announcer = new ConsoleAnnouncer();
    Policeman policeman= new PolicemanImpl();

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
