package models;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Circle {
    public static final String NAME = "круг";

    private Point center;
    private double radius;
    private boolean visibility;

    public Circle(double x, double y, double radius, double canvasHeight, double canvasWidth, boolean modifyCoordsFlag) {
        /*if (modifyCoordsFlag){
            center = new Point(modifyCoordToCenterCoord(x, radius), modifyCoordToCenterCoord(y, radius));
        }*/
        center = new Point(x, y);
        checkFittingIn(center.getX(), center.getY(), radius, canvasHeight, canvasWidth);
        this.radius = radius;
        visibility = true;
        System.out.println("Object Circle has been created!");
    }

    public Circle(double canvasHeight, double canvasWidth){
        center = new Point(Math.random() * canvasWidth, Math.random() * canvasHeight);
        radius = Math.random() * 100;
        checkFittingIn(center.getX(), center.getY(), radius, canvasHeight, canvasWidth);
        visibility = true;
        System.out.println("Object Circle has been created!");
    }

    public void show(GraphicsContext gc){
        if (visibility) {
            gc.setFill(Color.RED);
            gc.fillOval(center.getX()-radius, center.getY()-radius, radius*2, radius*2);
        }
    }
    public void show(GraphicsContext gc, Paint p){
        if (visibility) {
            gc.setFill(p);
            gc.fillOval(center.getX()-radius, center.getY()-radius, radius*2, radius*2);
        }
    }

    public void move(double biasX, double biasY){
        center.setX(center.getX() + biasX);
        center.setY(center.getY() + biasY);
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
            center.setX(cWidth - radius);
        }
        if (x < 0 || x - radius < 0){
            //TODO: counting doesn't correct
            center.setX(radius);
        }
        if (y + radius > cHeight){
            center.setY(cHeight - radius);
        }
        if (y < 0 || y - radius < 0){
            center.setY(radius);
        }
    }

//    private double modifyCoordToCenterCoord(double coord, double radius) {
//        return coord - Math.sqrt(radius*radius/2);
//    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }
}
