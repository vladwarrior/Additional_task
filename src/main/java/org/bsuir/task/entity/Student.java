package org.bsuir.task.entity;

public class Student {
    private String fullName;
    private String city;
    private boolean isStudying;

    public Student(String fullName, String city) {
        this.fullName = fullName;
        this.city = city;
    }

    public boolean isStudying() {
        return isStudying;
    }

    public String getCity() {
        return city;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStudying(boolean studying) {
        isStudying = studying;
    }
}
