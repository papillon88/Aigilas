package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class ForgetSkill extends ISkill

{
    public ForgetSkill()

    {
        super(SkillId.FORGET_SKILL, AnimationType.SELF);

        Add(Elements.MENTAL);
        AddCost(StatType.MANA, 10);

    }

    @Override
    public void Activate(ICreature source) {
        super.Activate(source);
        _source.RemoveLeastUsedSkill();

    }

}
