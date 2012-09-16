package aigilas.skills.impl;

import aigilas.creatures.CreatureFactory;
import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import spx.entities.EntityManager;

public class HypothermiaSkill extends ISkill {
    public HypothermiaSkill()

    {
        super(SkillId.DISMEMBERMENT, AnimationType.SELF);

        add(Elements.WATER);
        addCost(StatType.MANA, 3);

    }

    @Override
    public void activate(ICreature target)

    {
        for (int ii = 0; ii < 4; ii++) {
            CreatureFactory.createMinion(SkillId.ICE_SHARD, target, _behavior.getGraphic(), EntityManager.getEmptyLocation());

        }

    }

}
