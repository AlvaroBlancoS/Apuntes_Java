package com.programarycafe.catalog;

public enum Items {

    SPECIE("Specie"),
    MEDICINE("Medicine");

    
    private Items(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }

}
