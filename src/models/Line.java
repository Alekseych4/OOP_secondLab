package models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Line {
    public static final String NAME = "линию";

    private Point start;
    private Point end;
    private boolean visibility;

    public Line(double x, double y, double x1, double y1) {
        start = new Point(x, y);
        end = new Point(x1, y1);
        visibility = true;
    }

    public Line(){
        start = new Point(Math.random() * 1000, Math.random() * 1000);
        end = new Point(Math.random() * 1000, Math.random() * 1000);
        visibility = true;
    }

    public void show(GraphicsContext gc){
        if (visibility) {
            String color = "#000000";
            gc.setStroke(Paint.valueOf(color));
            gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
        }
    }

    public void move(double biasX, double biasY){
        start.setX(start.getX() + biasX);
        start.setY(start.getY() + biasY);
        end.setX(end.getX() + biasX);
        end.setY(end.getY() + biasY);
    }

    public void changeLength(double changedLength){
        end.setX(end.getX() + changedLength);
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }
}
