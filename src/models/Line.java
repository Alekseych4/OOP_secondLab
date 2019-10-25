package models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Line {
    public static final String NAME = "линию";

    private double x;
    private double y;
    private double length;
    private boolean visibility;

    public Line(double x, double y, double length) {
        this.x = x;
        this.y = y;
        this.length = length;
        visibility = true;
    }

    public Line(){
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        length = Math.random() * 100;
        visibility = true;
    }

    public void show(GraphicsContext gc){
        if (visibility) {
            String color = "#000000";
            gc.setStroke(Paint.valueOf(color));
            gc.strokeLine(x, y, x+length, y);
        }
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean getVisibility() {
        return visibility;
    }
}
