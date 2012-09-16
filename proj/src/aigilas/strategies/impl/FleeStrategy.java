package aigilas.strategies.impl;

import aigilas.creatures.ICreature;
import aigilas.strategies.IStrategy;
import aigilas.strategies.Strategy;
import spx.bridge.ActorType;
import spx.core.Point2;

public class FleeStrategy extends IStrategy {
    public FleeStrategy(ICreature parent, ActorType... targetTypes)

    {
        super(parent, Strategy.Flee);

        for (ActorType targetType : targetTypes) {
            _targets.addTargetTypes(targetType);
        }
    }

    private Point2 _transfer = new Point2(0, 0);

    @Override
    public void act() {
        if (AbleToMove()) {
            _transfer = targetPath.getNextMove();
            if (_transfer != null) {
                nextMove.copy(_parent.getLocation().add(_transfer.minus(_parent.getLocation()).rotate180()));
                _parent.moveTo(nextMove);
            }
        }
    }
}
