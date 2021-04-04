package com.company;

import java.awt.*;

public class Parallelogram extends Polygon {
    public Parallelogram(int x, int y, int size, int direction) {
        y = y - size / 2;
        if (direction > 0) {
            x = x - size;
        }else if (direction == 0){
            y=y-size/2;
            x=x-size/2;
        }
        this.addPoint(x, y);
        this.addPoint(x + size, y);
        if (direction < 0) {
            this.addPoint(x, y + size);
            this.addPoint(x - size, y + size);
        } else if(direction > 0){
            this.addPoint(x + 2 * size, y + size);
            this.addPoint(x + size, y + size);
        }else{
            this.addPoint(x+ size, y+2*size);
            this.addPoint(x, y+2*size);
        }

    }
}
