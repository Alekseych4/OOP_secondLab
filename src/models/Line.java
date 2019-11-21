package models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Line {
    public static final String NAME = "линию";

    private double x;
    private double y;
    private double x1;
    private double y1;
    private boolean visibility;

    public Line(double x, double y, double x1, double y1) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        visibility = true;
    }

    public Line(){
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        y1 = Math.random() * 1000;
        x1 = Math.random() * 1000;
        visibility = true;
    }

    public void show(GraphicsContext gc){
        if (visibility) {
            String color = "#000000";
            gc.setStroke(Paint.valueOf(color));
            gc.strokeLine(x, y, x1, y1);
        }
    }

    public void move(double biasX, double biasY){
        x += biasX;
        y += biasY;
        x1 += biasX;
        y1 += biasY;
    }

    public void changeLength(double changedLength){
        x1 = changedLength;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean getVisibility() {
        return visibility;
    }
}
