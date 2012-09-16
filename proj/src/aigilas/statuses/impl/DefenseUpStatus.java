package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.statuses.IStatus;

public class DefenseUpStatus extends IStatus {
    public DefenseUpStatus(ICreature target)

    {
        super(target);

        _buff = new StatBuff(StatType.DEFENSE, 10);
        setup();
    }
}
