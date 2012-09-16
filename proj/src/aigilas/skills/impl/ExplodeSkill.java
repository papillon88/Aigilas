package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class ExplodeSkill extends ISkill {
    public ExplodeSkill()

    {
        super(SkillId.EXPLODE, AnimationType.CLOUD);

        Add(Elements.FIRE);
        AddCost(StatType.MANA, 0);

    }

    @Override
    public void Affect(ICreature target)

    {
        target.ApplyDamage(10, _source, true);

    }

}
