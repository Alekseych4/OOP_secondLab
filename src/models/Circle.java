package models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Circle {
    public static final String NAME = "круг";

    private double x;
    private double y;
    private double radius;
    private boolean visibility;

    public Circle(double x, double y, double radius) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        visibility = true;
    }

    public Circle(){
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        radius = Math.random() * 100;
        visibility = true;
    }

    public void show(GraphicsContext gc){
        if (visibility) {
            String color = "#00FF00";
            gc.setFill(Paint.valueOf(color));
            gc.fillOval(x, y, radius, radius);
        }
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean getVisibility() {
        return visibility;
    }
}
