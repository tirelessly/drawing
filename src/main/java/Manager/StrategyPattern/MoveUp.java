package Manager.StrategyPattern;

import Bean.Shape;

public class MoveUp implements MoveStrategy {
    @Override
    public void move(Shape shape) {
        for(int i = 0; i < shape.getCoordinates().size(); i++)
            shape.getCoordinates().get(i).setY(shape.getCoordinates().get(i).getY() - 50);
    }
}
