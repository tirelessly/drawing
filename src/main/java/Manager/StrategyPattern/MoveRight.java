package Manager.StrategyPattern;

import Bean.Shape;

public class MoveRight implements MoveStrategy {

    @Override
    public void move(Shape shape)
    {
        for(int i = 0; i < shape.getCoordinates().size(); i++){

            shape.getCoordinates().get(i).setX(shape.getCoordinates().get(i).getX() + 50);}
    }
}
