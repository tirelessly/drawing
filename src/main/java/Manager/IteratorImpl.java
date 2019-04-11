package Manager;

import Bean.Shape;

import java.util.List;

public class IteratorImpl implements ShapeIterator {

    private int id;
    private List<Shape> shapes;
    private int pos = 0;

    public IteratorImpl(int id, List<Shape> shapes){
        this.id = id;
        this.shapes = shapes;
    }

    @Override
    public boolean hasNext() {
        while (pos < shapes.size()) {
            Shape shape = shapes.get(pos);
            if (shape.getId() == id) {
                return true;
            } else
                pos++;
        }
        return false;
    }

    @Override
    public Shape next() {
        Shape shape = shapes.get(pos);
        pos++;
        return shape;
    }
}
