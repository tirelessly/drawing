package Manager;

import Bean.Coordinates;
import Bean.Shape;

import java.util.List;

public class FillColorDecorator extends ShapeDecorator {
    protected String color;


    public FillColorDecorator(Shape decoratedShape, String color){
        super(decoratedShape);
        decoratedShape.setColor(color);
        this.color = color;
    }


    @Override
    public String generateHtml() {
        System.out.println("i am here " + color);
        return decoratedShape.generateHtml();
    }

    @Override
    public void setScale(Double scale) {

    }

    @Override
    public Double getScale() {
        return null;
    }

    @Override
    public String getColor() {
        return this.color;
    }


    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setColor(String color) {

    }

    @Override
    public List<Coordinates> getCoordinates() {
        return null;
    }
}
