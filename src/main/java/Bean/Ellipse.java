package Bean;

import java.util.ArrayList;
import java.util.List;

public class Ellipse implements Shape {

    private int id;
    private double xRadius;
    private double yRadius;
    private List<Coordinates> coordinates = new ArrayList<>();
    private String color;
    private Double scale;


    public Ellipse(int id, List<Coordinates> coordinates, double xRadius, double yRadius, String color){
        this.coordinates = coordinates;
        this.xRadius = xRadius;
        this.yRadius = yRadius;
        this.id = id;
        this.color = color;
        this.scale = 1.0;
    }


    public double getXRadius() {
        return xRadius;
    }
    public double getYRadius() {
        return yRadius;
    }


    public String getColor() {
        return color;
    }


    @Override
    public String generateHtml() {
        return "<ellipse transform=\"scale(" + getScale() +")\" cx=\" " + coordinates.get(0).getX() + " \" cy=\" " + coordinates.get(0).getY()+
                "\" rx=\" "+ this.xRadius+ " \" ry=\" " + this.yRadius +" \" stroke-width=\"4\" fill=\"" + this.getColor() +"\" />";
    }

    @Override
    public void setScale(Double scale) {
        this.scale += scale;
    }

    @Override
    public Double getScale() {
        return this.scale;
    }


    @Override
    public int getId(){
        return id;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public List<Coordinates> getCoordinates() {
        return this.coordinates;
    }
}

