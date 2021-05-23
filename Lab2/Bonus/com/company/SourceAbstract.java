package com.company;


import java.util.Objects;

public abstract class SourceAbstract {
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public abstract boolean equals(Object obj);

}
