package models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Circle {
    public static final String NAME = "круг";

    private double x;
    private double y;
    private double radius;
    private boolean visibility;

    public Circle(double x, double y, double radius, boolean visibility) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.visibility = visibility;
    }


}
