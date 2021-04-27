package com.coolcats.sqlitedatabaseprj.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.coolcats.sqlitedatabaseprj.util.Position;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private int id;
    private Position position;

    public User(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public User(String name, int id, Position position) {
        this.name = name;
        this.id = id;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", position=" + position +
                '}';
    }

}
