package Manager;

import Bean.Shape;

import java.util.List;

public abstract class AbstractFactory {

    abstract void changeColor(int id, String color);

    abstract void createShape(Object ... args);

    abstract void moveShape(int id, String direction);

    abstract void scaleShape(int id, String scale);

    abstract void deleteShape(int id);

    abstract List<Shape> getShapes();

}
