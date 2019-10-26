package models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Circle {
    public static final String NAME = "круг";

    private Point center;
    private double radius;
    private boolean visibility;

    public Circle(double x, double y, double radius) {
        this.radius = radius;
        center = new Point(x, y);
        visibility = true;
    }

    public Circle(){
        center = new Point(Math.random() * 1000, Math.random() * 1000);
        radius = Math.random() * 100;
        visibility = true;
    }

    public void show(GraphicsContext gc){
        if (visibility) {
            String color = "#00FF00";
            gc.setFill(Paint.valueOf(color));
            gc.fillOval(center.getX(), center.getY(), radius, radius);
        }
    }

    public void move(double biasX, double biasY){
        center.setX(center.getX() + biasX);
        center.setY(center.getY() + biasY);
    }

    public void changeRadius(double changedRadius){
        radius = changedRadius;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean getVisibility() {
        return visibility;
    }
}
