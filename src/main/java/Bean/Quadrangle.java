package Bean;

import java.util.ArrayList;
import java.util.List;

public class Quadrangle implements Shape {

    private int id;
    private List<Coordinates> coordinates = new ArrayList<>();
    private String color;
    private double width;
    private double height;
    private Double scale;

    public Quadrangle(int id, List<Coordinates> coordinates, double width, double height, String color) {
        this.id = id;
        this.coordinates =coordinates;
        this.color = color;
        this.width = width;
        this.height = height;
        this.scale = 1.0;
    }

    @Override
    public String generateHtml() {
        return "<rect transform=\"scale(" + getScale() +")\" x=\"" + coordinates.get(0).getX() + "\" y=\"" + coordinates.get(0).getY() + "\" width=\"" + this.width +
                " \" height=\"" + this.height + "\" fill=\"" + this.getColor() + " \"/>" ;
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
    public int getId() {
        return this.id;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public List<Coordinates> getCoordinates() {
        return this.coordinates;
    }

    public String getColor() {
        return color;
    }

}

