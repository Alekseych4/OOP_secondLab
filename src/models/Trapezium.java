package models;

import sketches.Quadrangle;

import java.util.Arrays;

public class Trapezium extends Quadrangle {
    public static final String NAME = "трапецию";
    public Trapezium(double x, double y, double height, double width) {
        super(x, y, height, width);
    }

    public Trapezium() {
        super();
    }

    @Override
    protected void setPointsOfQuadrangle(double x, double y){
        setxArray(new double[]{x, x + getWidth(), x + getWidth() + getHeight()/2, x - getHeight() / 2});
        System.out.println("X Array: " + Arrays.toString(getxArray()));
        setyArray(new double[]{y, y, y + getHeight(), y + getHeight()});
        System.out.println("Y Array: " + Arrays.toString(getyArray()));
    }
}
