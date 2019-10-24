package models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle {
    public static final String NAME = "прямоугольник";

    private double x;
    private double y;
    private double height;
    private double width;
    private boolean visibility;

    public Rectangle(double x, double y, double height, double width, boolean visibility) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.visibility = visibility;
    }
}
