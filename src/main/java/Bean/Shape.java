package Bean;

import Manager.ShapeIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface Shape {

    public String generateHtml();
    public void setScale(Double scale);
    public Double getScale();
    public String getColor();
    public int getId();
    public void setColor(String color);
    public List<Coordinates> getCoordinates();

}