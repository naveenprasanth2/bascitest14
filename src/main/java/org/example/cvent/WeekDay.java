package org.example.cvent;

public enum WeekDay {
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7);

    public int id;

    public String nameInLowerCase(){
        return this.name().toLowerCase();
    }

    WeekDay(int id){
        this.id = id;
    }
}
