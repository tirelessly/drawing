package Bean;

import java.util.ArrayList;
import java.util.List;

public class Circle implements Shape {

    private int id;
    private double radius;
    private List<Coordinates> coordinates = new ArrayList<>();
    private Double scale;
    private String color;

    public Circle(int id, List<Coordinates> coordinates,double radius, String color){
        this.coordinates = coordinates;
        this.radius = radius;
        this.id = id;
        this.color = color;
        this.scale = 1.0;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public List<Coordinates> getCoordinates() {
        return coordinates;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String generateHtml() {
        return "<circle  transform=\"scale(" + getScale() +")\"  cx=\" " + coordinates.get(0).getX() + " \" cy=\" " + coordinates.get(0).getY() + " \" "
                + " r=\" "+ this.radius+ " \" stroke-width=\"4\" fill=\"" + this.getColor() +"\" />";
    }

    @Override
    public void setScale(Double scale) {
        this.scale += scale;
    }

    @Override
    public Double getScale(){
        return this.scale;
    }


    @Override
    public int getId(){
        return id;
    }
}
