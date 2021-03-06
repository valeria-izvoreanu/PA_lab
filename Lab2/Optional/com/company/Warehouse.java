package com.company;

import java.util.Objects;

public class Warehouse extends SourceAbstract{

    public Warehouse(){}

    public Warehouse(String name) {
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null ||  !(obj instanceof Warehouse)) return false;
        Warehouse that = (Warehouse) obj;
        return Objects.equals(name, that.name);
    }
}
