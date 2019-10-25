package models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Rectangle {
    public static final String NAME = "прямоугольник";

    private double x;
    private double y;
    private double height;
    private double width;
    private boolean visibility;

    public Rectangle(double x, double y, double height, double width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        visibility = true;
    }

    public Rectangle(){
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        height = Math.random() * 100;
        width = Math.random() * 100;
        visibility = true;
    }

    public void show(GraphicsContext gc){
        if (visibility) {
            String color = "#FF0000";
            gc.setFill(Paint.valueOf(color));
            gc.fillRect(x, y, width, height);
        }
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean getVisibility() {
        return visibility;
    }
}
