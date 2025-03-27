package org.example.cvent;

public class EnumEval {
    public static void main(String[] args) {
        WeekDay weekDay = WeekDay.SATURDAY;
        print(weekDay);
    }

    private static void print(WeekDay weekDay){
        System.out.println(weekDay.id);
        System.out.println(weekDay.nameInLowerCase());
    }
}
