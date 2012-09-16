package aigilas.statuses.impl;

import aigilas.creatures.CreatureAction;
import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;

public class LockSkillCycleStatus extends IStatus {
    public LockSkillCycleStatus(ICreature target)

    {
        super(target);

        _prevents.add(CreatureAction.SkillCycle);
    }
}
