package aigilas.reactions.impl;

import aigilas.creatures.ICreature;
import aigilas.reactions.IReaction;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class YinYangReaction implements IReaction {
    @Override
    public void Affect(ICreature target)

    {
        StatusFactory.Apply(target, Status.SelfMutilation);
    }
}