package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.IStatus;

public class StrengthUpStatus extends IStatus {
    public StrengthUpStatus(ICreature target)

    {
        super(target);

        _buff = new StatBuff(StatType.STRENGTH, 10f);
        Setup();
    }
}
