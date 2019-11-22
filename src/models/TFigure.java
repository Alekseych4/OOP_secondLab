package models;

import javafx.scene.canvas.GraphicsContext;

public abstract class TFigure {
    Point coords;

    public TFigure(Point coords) {
        this.coords = coords;
        System.out.println("Coordinates were initialized");
    }

    abstract void show(GraphicsContext gc);

    abstract void move(double biasX, double biasY);

    public Point getCoords() {
        return coords;
    }

    public void setCoords(Point coords) {
        this.coords = coords;
    }
}
