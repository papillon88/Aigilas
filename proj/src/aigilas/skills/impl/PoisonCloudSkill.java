package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class PoisonCloudSkill extends ISkill {
    public PoisonCloudSkill()

    {
        super(SkillId.POISON_CLOUD, AnimationType.CLOUD);

        Add(Elements.MENTAL);
        AddCost(StatType.MANA, 0);

    }

    @Override
    public void Affect(ICreature target)

    {
        StatusFactory.Apply(target, Status.Poison);

    }

}
