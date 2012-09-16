package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class FireballSkill extends ISkill {
    public FireballSkill()

    {
        super(SkillId.FIREBALL, AnimationType.RANGED);

        Add(Elements.FIRE);
        AddCost(StatType.MANA, 10);

    }

    @Override
    public void Affect(ICreature target)

    {
        target.ApplyDamage(20, _source);

    }

}
