package models;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Bicycle {
    public static final String NAME = "велосипед";

    private Point center;
    private boolean visibility;
    private Line steering, skeleton, seat, pole, chain;
    private Ring rearWheel, frontWheel;

    public Bicycle(double x, double y, double cHeight, double cWidth) {
        center = new Point(x, y);
        initVariables(center, cHeight, cWidth);
        visibility = true;
    }

    public Bicycle(double cHeight, double cWidth){
        center = new Point(Math.random() * 1000, Math.random() * 1000);
        initVariables(center, cHeight, cWidth);
        visibility = true;
    }

    private void initVariables(Point c, double cHeight, double cWidth){
        seat = new Line(c.getX() - 10, c.getY(), c.getX() + 10, c.getY());
        pole = new Line(c.getX(), c.getY(), c.getX(), c.getY() + 20);
        skeleton = new Line(pole.getX1() - 30, pole.getY1(), pole.getX() + 30, pole.getY());
        steering = new Line(skeleton.getX1(), skeleton.getY1() - 20, skeleton.getX1(), skeleton.getY1() + 20);
        chain = new Line(skeleton.getX(), skeleton.getY(), pole.getX1(), pole.getY1());
        rearWheel = new Ring(chain.getX(), chain.getY(), 10, cHeight, cWidth);
        frontWheel = new Ring(steering.getX1(), steering.getY1() + 10, 10, cHeight, cWidth);
    }

    public void show(GraphicsContext gc){
        if (visibility) {
            seat.show(gc);
            pole.show(gc);
            skeleton.show(gc);
            steering.show(gc);
            chain.show(gc);
            rearWheel.show(gc);
            frontWheel.show(gc);
        }
    }

    public void move(double biasX, double biasY){
        seat.move(biasX, biasY);
        pole.move(biasX, biasY);
        skeleton.move(biasX, biasY);
        steering.move(biasX, biasY);
        chain.move(biasX, biasY);
        rearWheel.move(biasX, biasY);
        frontWheel.move(biasX, biasY);
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean getVisibility() {
        return visibility;
    }
}
