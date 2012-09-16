package aigilas.strategies.impl;

import aigilas.creatures.ICreature;
import aigilas.strategies.IStrategy;
import aigilas.strategies.Strategy;
import spx.bridge.ActorType;
import spx.core.Settings;

public class StraightLineStrategy extends IStrategy {
    public StraightLineStrategy(ICreature parent, ActorType... targetTypes)

    {
        super(parent, Strategy.StraightLine);

        for (ActorType targetType : targetTypes) {
            _targets.AddTargetTypes(targetType);
        }
    }

    @Override
    public void Act() {
        _parent.MoveIfPossible(0, 1);
        if (_parent.GetLocation().GridY >= Settings.Get().tileMapHeight - 1) {
            _parent.SetInactive();
        }
    }
}