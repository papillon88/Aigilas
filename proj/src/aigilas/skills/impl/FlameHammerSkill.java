package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class FlameHammerSkill extends ISkill {
    public FlameHammerSkill()

    {
        super(SkillId.FLAME_HAMMER, AnimationType.ROTATE);

        Add(Elements.FIRE);
        AddCost(StatType.MANA, 10);

    }

    @Override
    public void Affect(ICreature target)

    {
        target.ApplyDamage(3f, _source);

    }

}
