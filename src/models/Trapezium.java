package models;

import javafx.scene.canvas.GraphicsContext;
import sketches.Quadrangle;

public class Trapezium extends Quadrangle {
    public Trapezium(double x, double y, double height, double width) {
        super(x, y, height, width);
        setPointsOfQuadrangle();
    }

    public Trapezium() {
        super();
        setPointsOfQuadrangle();
    }

    @Override
    protected void setPointsOfQuadrangle(){
        setxArray(new double[]{getCoords().getX(), getCoords().getX() + getWidth() - getHeight(),
                getCoords().getX() + getWidth() - getHeight()/2,
                getCoords().getX() - getHeight() / 2});
        setyArray(new double[]{getCoords().getY(), getCoords().getY(), getCoords().getY() + getHeight(),
                getCoords().getY() + getHeight()});
    }
}
