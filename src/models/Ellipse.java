package models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ellipse extends Circle{

    private double radius1;
    public static final String NAME = "эллипс";
    public Ellipse(double x, double y, double radius, double radius1) {
        super(x, y, radius);
        this.radius1 = radius1;
    }

    public Ellipse() {
        super();
        radius1 = Math.random() * 100;
    }

    @Override
    public void show(GraphicsContext gc) {
        if (this.getVisibility()) {
            gc.setFill(Color.RED);
            gc.fillOval(getCoords().getX()-this.getRadius(), getCoords().getY()-getRadius(),
                    this.getRadius()*2, radius1*2);
        }
    }

    public void changeRadii(double changedRadius, double changedRadius1){
        this.setRadius(changedRadius);
        radius1 = changedRadius1;
    }

    public void flip(){
        changeRadii(radius1, this.getRadius());
    }
}
