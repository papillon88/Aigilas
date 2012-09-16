package aigilas.skills.impl;

import aigilas.creatures.CreatureFactory;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.entities.SkillEffect;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import spx.entities.Entity;

public class DartTrapSkill extends ISkill {
    public DartTrapSkill()

    {
        super(SkillId.DART_TRAP, AnimationType.RANGED);

        addCost(StatType.MANA, 10);
        add(Elements.DARK);

    }

    @Override
    public void cleanup(Entity target, SkillEffect source)

    {
        CreatureFactory.createMinion(_implementationId, _source, source, target.getLocation());

    }

}
