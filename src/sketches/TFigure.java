package sketches;

import javafx.scene.canvas.GraphicsContext;
import models.Point;

public abstract class TFigure {
    private Point coords;

    public TFigure(Point coords) {
        this.coords = coords;
        System.out.println("Coordinates were initialized");
    }

    public abstract void show(GraphicsContext gc);

    public abstract void move(double biasX, double biasY);

    public Point getCoords() {
        return coords;
    }

    public void setCoords(Point coords) {
        this.coords = coords;
    }
}
