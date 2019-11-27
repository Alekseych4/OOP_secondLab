package sketches;

import javafx.scene.canvas.GraphicsContext;
import models.Point;

public abstract class TFigure {
    private Point coords;

    public TFigure(Point coords) {
        this.coords = coords;
        System.out.println("Coordinates were initialized");
    }

    public void show(GraphicsContext gc){}

    public final void move(double biasX, double biasY, GraphicsContext gc){
        getCoords().setX(biasX + getCoords().getX());
        getCoords().setY(biasY + getCoords().getY());

        show(gc);
    }

    protected Point getCoords() {
        return coords;
    }

    public void setCoords(Point coords) {
        this.coords = coords;
    }
}
