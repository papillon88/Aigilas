package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class GushSkill extends ISkill {
    public GushSkill()

    {
        super(SkillId.GUSH, AnimationType.RANGED);

        Add(Elements.WATER);
        AddCost(StatType.MANA, 10);

    }

    @Override
    public void Affect(ICreature target)

    {
        target.ApplyDamage(10, _source, true);

    }

}
