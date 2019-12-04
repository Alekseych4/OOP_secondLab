package models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import sketches.TFigure;

public class Circle extends TFigure {
    public static final String NAME = "круг";

    private double radius;

    public Circle(double x, double y, double radius) {
        super(new Point(x, y));
        this.radius = radius;
        System.out.println("Object Circle has been created!");
    }

    public Circle(){
        super(new Point(Math.random() * 1000, Math.random() * 1000));
        radius = Math.random() * 100;
        System.out.println("Object Circle has been created!");
    }

    @Override
    public void show(GraphicsContext gc){
        if (this.getVisibility()) {
            gc.setFill(Color.RED);
            gc.fillOval(getCoords().getX()-radius, getCoords().getY()-radius, radius*2, radius*2);
        }
    }

    public void show(GraphicsContext gc, Paint p){
        if (this.getVisibility()) {
            gc.setFill(p);
            gc.fillOval(getCoords().getX()-radius, getCoords().getY()-radius, radius*2, radius*2);
        }
    }

    public void changeRadius(double changedRadius){
        radius = changedRadius;
    }

    public Point getCenter() {
        return getCoords();
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
