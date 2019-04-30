package com.test;

import java.io.Serializable;


import javax.persistence.*;


@Entity
@Access(AccessType.FIELD)
@Table(name = "Test")
public class Test implements Serializable {

    @Id
    @Column(length = 12)
    private String number;
    @Column(length = 3)
    private String departure;
    @Column(length = 3)
    private String arrival;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    @Override
    public String toString() {
        return String.format("[flight %s from %s to %s]", number, departure, arrival);
    }

}
