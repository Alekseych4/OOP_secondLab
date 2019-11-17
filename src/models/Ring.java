package models;

public class Ring {
    public static final String NAME = "кольцо";

    private Circle whiteCircle, outlinedCircle;

    public Ring(double x, double y, double radius) {
        whiteCircle = new Circle(x, y, radius);
        //TODO: when x and y will be coordinates of center change it to radius+2
        outlinedCircle = new Circle(x+2, y+2, radius);
    }

    public Ring(){
        whiteCircle = new Circle();
        outlinedCircle = new Circle();
    }
}
