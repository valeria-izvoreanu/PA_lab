package com.company;

import java.util.Objects;

public class Factory extends SourceAbstract {

    public Factory() {
    }

    public Factory(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Factory)) {
            return false;
        }
        Factory other = (Factory) obj;
        return name.equals(other.getName());
    }
}
