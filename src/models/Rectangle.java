package models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Rectangle {
    public static final String NAME = "прямоугольник";

    private Point center;
    private double height;
    private double width;
    private boolean visibility;

    public Rectangle(double x, double y, double height, double width) {
        center = new Point(x, y);
        this.height = height;
        this.width = width;
        visibility = true;
    }

    public Rectangle(){
        center = new Point(Math.random() * 1000, Math.random() * 1000);
        height = Math.random() * 100;
        width = Math.random() * 100;
        visibility = true;
    }

    public void show(GraphicsContext gc){
        if (visibility) {
            String color = "#FF0000";
            gc.setFill(Paint.valueOf(color));
            gc.fillRect(center.getX(), center.getY(), width, height);
        }
    }

    public void move(double biasX, double biasY){
        center.setX(center.getX() + biasX);
        center.setY(center.getY() + biasY);
    }

    public void changeDimensions(double changedWidth, double changedHeight){
        height = changedHeight;
        width = changedWidth;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean getVisibility() {
        return visibility;
    }
}
