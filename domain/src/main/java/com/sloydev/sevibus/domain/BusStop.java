package com.sloydev.sevibus.domain;

public class BusStop {

    private Integer number;
    private String name;
    private Position position;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusStop busStop = (BusStop) o;

        if (number != busStop.number) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return number;
    }
}
