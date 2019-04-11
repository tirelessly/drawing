package Bean;

import java.util.ArrayList;
import java.util.List;

public class Line implements Shape {
    private int id;
    private List<Coordinates> coordinates = new ArrayList<>();
    private String color;
    private Double scale;

    public Line(int id, List<Coordinates> coordinates, String color){
        this.coordinates = coordinates;
        this.color = color;
        this.id = id;
        this.color = color;
        this.scale = 1.0;
    }

    public List<Coordinates> getCoordinates() {
        return coordinates;
    }


    public String getColor() {
        return color;
    }

    //  <line x1="0" y1="0" x2="200" y2="200" style="stroke:rgb(255,0,0);stroke-width:2" />


    @Override
    public String generateHtml() {
        return "<line transform=\"scale(" + getScale() +")\" x1=\" " + coordinates.get(0).getX() + " \" y1=\"" + coordinates.get(0).getY() + " \" x2=\"" + coordinates.get(1).getX() + "\" y2=\"" + coordinates.get(1).getY() + "\" style=\"stroke:" + getColor() +"; stroke-width:2 \" />";
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

}
