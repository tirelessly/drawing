package Manager;

import Bean.Shape;

import java.util.List;

public class ShapeContainerImpl implements ShapeContainer{
    private List<Shape> shapes;

    public ShapeContainerImpl ( List<Shape> shapes){
        this.shapes=shapes;
    }

    @Override
    public IteratorImpl getIterator(int id) {
        return new IteratorImpl(id, this.shapes);
    }
}
