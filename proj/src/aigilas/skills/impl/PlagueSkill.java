package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class PlagueSkill extends ISkill {
    public PlagueSkill()

    {
        super(SkillId.STRENGTH_UP, AnimationType.SELF);

        Add(Elements.MENTAL);
        AddCost(StatType.MANA, 10);

    }

    @Override
    public void Activate(ICreature source) {
        super.Activate(source);
        StatusFactory.Apply(source, Status.Toxic);

    }

}