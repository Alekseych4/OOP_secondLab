package models;

import sketches.Quadrangle;

public class Rhombus extends Quadrangle {
    public static final String NAME = "ромб";
    public Rhombus(double x, double y, double height, double width) {
        super(x, y, height, width);
    }

    public Rhombus() {
        super();
    }

    @Override
    protected void setPointsOfQuadrangle(double x, double y) {
        setxArray(new double[]{x, x + getWidth() / 2, x, x - getWidth() / 2});
        setyArray(new double[]{y, y + getHeight() / 2, y + getHeight(), y + getHeight() / 2});
    }

}
