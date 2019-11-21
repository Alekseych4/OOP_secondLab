package models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ring {
    public static final String NAME = "кольцо";

    private Circle whiteCircle, borderCircle;

    public Ring(double x, double y, double radius, double canvasHeight, double canvasWidth) {
        whiteCircle = new Circle(x, y, radius, canvasHeight, canvasWidth, true);
        System.out.println("X of body: " + whiteCircle.getCenter().getX() + "Y of body: " + whiteCircle.getCenter().getY());
        borderCircle = new Circle(x, y, radius + 10,
                canvasHeight, canvasWidth, false);
        System.out.println("X of border: " + borderCircle.getCenter().getX() + "Y of border: " + borderCircle.getCenter().getY());
        //TODO: when x and y will be coordinates of center change it to radius+2
        System.out.println("Object Ring has been created!");
    }

    public Ring(double canvasHeight, double canvasWidth){
        borderCircle = new Circle(canvasHeight, canvasWidth);
        whiteCircle = new Circle(borderCircle.getCenter().getX(), borderCircle.getCenter().getY(),
                borderCircle.getRadius() - 10, canvasHeight, canvasWidth, false);
        System.out.println("Object Ring has been created!");
    }

    public void setVisibility(boolean visibility){
        borderCircle.setVisibility(visibility);
        whiteCircle.setVisibility(visibility);
    }

    public boolean getVisibility(){
        return whiteCircle.getVisibility() && borderCircle.getVisibility();
    }

    public void changeRadius(double changedRadius){
        borderCircle.changeRadius(changedRadius);
        whiteCircle.changeRadius(changedRadius);
    }

    public void show(GraphicsContext gc){
        if (whiteCircle.getVisibility() && borderCircle.getVisibility()) {
            whiteCircle.getCenter().setX(borderCircle.getCenter().getX());
            whiteCircle.getCenter().setY(borderCircle.getCenter().getY());
            borderCircle.show(gc);
            whiteCircle.show(gc, Color.WHITE);
        }
    }

    public void move(double biasX, double biasY){
        whiteCircle.move(biasX, biasY);
        borderCircle.move(biasX, biasY);
    }
}
