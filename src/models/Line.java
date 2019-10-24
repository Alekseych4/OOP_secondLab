package models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line {
    public static final String NAME = "линию";

    private double x;
    private double y;
    private double length;
    private boolean visibility;

    public Line(double x, double y, double length, boolean visibility) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.visibility = visibility;
    }
}
