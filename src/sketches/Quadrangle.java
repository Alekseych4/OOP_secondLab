package sketches;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import models.Point;

public class Quadrangle extends TFigure {

    private boolean visibility;
    private double height;
    private double width;
    private double[] xArray = new double[4];
    private double[] yArray = new double[4];

    public Quadrangle(double x, double y, double height, double width) {
        super(new Point(x, y));
        this.height = height;
        this.width = width;
        visibility = true;
    }

    public Quadrangle() {
        super(new Point(Math.random() * 1000, Math.random() * 1000));
        height = Math.random() * 100;
        width = Math.random() * 100;
        visibility = true;
    }

    @Override
    public void show(GraphicsContext gc) {
        if (visibility) {
            String color = "#00FF00";
            gc.setFill(Paint.valueOf(color));
            gc.fillPolygon(getxArray(), getyArray(), 4);
        }
    }

    @Override
    public void move(double biasX, double biasY) {
        getCoords().setX(biasX + getCoords().getX());
        getCoords().setY(biasY + getCoords().getY());
        setPointsOfQuadrangle(getCoords().getX(), getCoords().getY());
    }

    public void changeDimensions(double changedWidth, double changedHeight){
        setHeight(changedHeight);
        setWidth(changedWidth);
    }

    protected void setPointsOfQuadrangle(double x, double y){}

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public double[] getxArray() {
        return xArray;
    }

    public void setxArray(double[] xArray) {
        this.xArray = xArray;
    }

    public double[] getyArray() {
        return yArray;
    }

    public void setyArray(double[] yArray) {
        this.yArray = yArray;
    }
}
