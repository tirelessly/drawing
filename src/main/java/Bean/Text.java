package Bean;

import java.util.ArrayList;
import java.util.List;

public class Text implements Shape {

    private int id;
    private String text;
    private List<Coordinates> coordinates = new ArrayList<>();
    private String color;
    private Double scale;


    public Text(int id, List<Coordinates> coordinates, String text, String color){
        this.id = id;
        this.coordinates = coordinates;
        this.text = text;
        this.color = color;
        this.scale = 1.0;
    }

    @Override
    public String generateHtml() {
        return "<text transform=\"scale(" + getScale() +")\" x=\"" + coordinates.get(0).getX() + "\" y=\"" + coordinates.get(0).getY()
                + "\" fill=\"" + this.getColor() + "\"> " + this.text + "</text>" ;
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

    public String getColor() {
        return color;
    }

}
