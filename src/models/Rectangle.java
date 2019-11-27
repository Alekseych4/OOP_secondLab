package models;

import sketches.Quadrangle;

public class Rectangle extends Quadrangle {
    public static final String NAME = "прямоугольник";

    public Rectangle(double x, double y, double height, double width) {
        super(x, y, height, width);
        setPointsOfQuadrangle(getCoords().getX(), getCoords().getY());
    }

    public Rectangle() {
        super();
        setPointsOfQuadrangle(getCoords().getX(), getCoords().getY());
    }

    @Override
    protected void setPointsOfQuadrangle(double x, double y) {
        setxArray(new double[]{x, x + getWidth(), x + getWidth(), x});
        setyArray(new double[]{y, y, y + getHeight(), y + getHeight()});
    }
}
