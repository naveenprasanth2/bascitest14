package org.example.cvent;

public record Student(int id, String name, String department, String hod) {

    @Override
    public String hod() {
        return hod;
    }
}
