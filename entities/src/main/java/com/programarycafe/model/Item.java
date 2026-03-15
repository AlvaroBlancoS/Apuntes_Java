package com.programarycafe.model;

public class Item {

    private String name;
    private String type;

    public Item (String name, String type){
        this.name = name;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return name+" "+type;
    }

    

}