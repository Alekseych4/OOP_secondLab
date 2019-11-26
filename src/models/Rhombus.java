package models;

import javafx.scene.canvas.GraphicsContext;
import sketches.Quadrangle;

public class Rhombus extends Quadrangle {
    public Rhombus(double x, double y, double height, double width) {
        super(x, y, height, width);
        setPointsOfQuadrangle();
    }

    public Rhombus() {
        super();
        setPointsOfQuadrangle();
    }

    @Override
    protected void setPointsOfQuadrangle() {
        setxArray(new double[]{getCoords().getX(), getCoords().getX() + getWidth() / 2, getCoords().getX(),
                getCoords().getX() - getWidth() / 2});
        setyArray(new double[]{getCoords().getY(), getCoords().getY() + getHeight() / 2, getCoords().getY() + getHeight(),
                getCoords().getY() + getHeight() / 2});
    }

}
