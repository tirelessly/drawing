package Bean;

import java.util.ArrayList;
import java.util.List;

public class Star implements Shape {
    private int id;
    private List<Coordinates> coordinates = new ArrayList<>();
    private String color;
    private Double scale;

    public Star(int id, List<Coordinates> coordinates, String color){
        this.coordinates = coordinates;
        this.color = color;
        this.id = id;
        this.color = color;
        this.scale = 1.0;

    }

    @Override
    public String generateHtml() {
        return "<polygon transform=\"scale(" + getScale() +")\" points=\"" +    coordinates.get(0).getX() + "," + coordinates.get(0).getY() + " " +
                coordinates.get(1).getX() + "," + coordinates.get(1).getY()+ " " +
                coordinates.get(2).getX() + "," + coordinates.get(2).getY()+ " " +
                coordinates.get(3).getX() + "," + coordinates.get(3).getY()+ " " +
                coordinates.get(4).getX() + "," + coordinates.get(4).getY()+
                "\" style=\"fill:"+ getColor() +";stroke:purple;stroke-width:1 \" />";
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
