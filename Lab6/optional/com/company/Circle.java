package com.company;

import java.awt.geom.Ellipse2D;

public class Circle extends Ellipse2D.Double {
    public Circle(double x0, double y0, double radius) {
        super(x0 - radius / 2, y0 - radius / 2, radius, radius);

    }
}
