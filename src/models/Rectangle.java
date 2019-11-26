package models;

import sketches.Quadrangle;

public class Rectangle extends Quadrangle {
    public static final String NAME = "прямоугольник";

    public Rectangle(double x, double y, double height, double width) {
        super(x, y, height, width);
        setPointsOfQuadrangle();
    }

    public Rectangle() {
        super();
        setPointsOfQuadrangle();
    }

    @Override
    protected void setPointsOfQuadrangle() {
        setxArray(new double[]{getCoords().getX(), getCoords().getX() + getWidth(), getCoords().getX() + getWidth(),
                getCoords().getX()});
        setyArray(new double[]{getCoords().getY(), getCoords().getY(), getCoords().getY() + getHeight(),
                getCoords().getY() + getHeight()});
    }

    public void changeDimensions(double changedWidth, double changedHeight){
        setHeight(changedHeight);
        setWidth(changedWidth);
    }
}
