package com.company;

//represents the source which supplies the needed commodity
//recognized through it's name and type
public class Source {
    private SourceType type;
    private String name;

    public Source() {
    }

    public Source(String name, SourceType type) {
        this.type = type;
        this.name = name;
    }

    public SourceType getType() {
        return type;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setType(SourceType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}
