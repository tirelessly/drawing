package Manager.StrategyPattern;

import Bean.Shape;
import Manager.StrategyPattern.MoveStrategy;

public class MoveContext {
    private MoveStrategy moveStrategy;

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public void moveShape(Shape shape) {
        moveStrategy.move(shape);
    }
}
