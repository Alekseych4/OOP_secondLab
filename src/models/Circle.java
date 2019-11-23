package models;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Circle extends TFigure{
    public static final String NAME = "круг";

    private double radius;
    private boolean visibility;

    public Circle(double x, double y, double radius, double canvasHeight, double canvasWidth, boolean modifyCoordsFlag) {
        /*if (modifyCoordsFlag){
            center = new Point(modifyCoordToCenterCoord(x, radius), modifyCoordToCenterCoord(y, radius));
        }*/
        super(new Point(x, y));
        checkFittingIn(getCoords().getX(), getCoords().getY(), radius, canvasHeight, canvasWidth);
        this.radius = radius;
        visibility = true;
        System.out.println("Object Circle has been created!");
    }

    public Circle(double canvasHeight, double canvasWidth){
        super(new Point(Math.random() * canvasWidth, Math.random() * canvasHeight));
        radius = Math.random() * 100;
        checkFittingIn(getCoords().getX(), getCoords().getY(), radius, canvasHeight, canvasWidth);
        visibility = true;
        System.out.println("Object Circle has been created!");
    }

    @Override
    public void show(GraphicsContext gc){
        if (visibility) {
            gc.setFill(Color.RED);
            gc.fillOval(getCoords().getX()-radius, getCoords().getY()-radius, radius*2, radius*2);
        }
    }

    public void show(GraphicsContext gc, Paint p){
        if (visibility) {
            gc.setFill(p);
            gc.fillOval(getCoords().getX()-radius, getCoords().getY()-radius, radius*2, radius*2);
        }
    }

    @Override
    public void move(double biasX, double biasY){
        getCoords().setX(getCoords().getX() + biasX);
        getCoords().setY(getCoords().getY() + biasY);
    }

    public void changeRadius(double changedRadius){
        radius = changedRadius;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean getVisibility() {
        return visibility;
    }

    private void checkFittingIn(double x, double y, double radius, double cHeight, double cWidth){
        if (x + radius > cWidth){
            getCoords().setX(cWidth - radius);
        }
        if (x < 0 || x - radius < 0){
            //TODO: counting doesn't correct
            getCoords().setX(radius);
        }
        if (y + radius > cHeight){
            getCoords().setY(cHeight - radius);
        }
        if (y < 0 || y - radius < 0){
            getCoords().setY(radius);
        }
    }

//    private double modifyCoordToCenterCoord(double coord, double radius) {
//        return coord - Math.sqrt(radius*radius/2);
//    }

    public Point getCenter() {
        return getCoords();
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
