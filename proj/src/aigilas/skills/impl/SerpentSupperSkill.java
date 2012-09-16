package aigilas.skills.impl;

import aigilas.creatures.CreatureFactory;
import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import spx.bridge.ActorType;
import spx.core.Point2;
import spx.core.Settings;

public class SerpentSupperSkill extends ISkill {
    public SerpentSupperSkill()

    {
        super(SkillId.SERPENT_SUPPER, AnimationType.SELF);

        Add(Elements.MENTAL);
        AddCost(StatType.MANA, 10);

    }

    @Override
    public void Activate(ICreature source) {
        for (int ii = 1; ii < Settings.Get().tileMapWidth - 1; ii++) {
            if (ii != Settings.Get().tileMapHeight / 2) {
                CreatureFactory.Create(ActorType.SERPENT, new Point2(ii, Settings.Get().tileMapHeight / 2));

            }

        }
        for (int ii = 1; ii < Settings.Get().tileMapHeight - 1; ii++) {
            if (ii != Settings.Get().tileMapWidth / 2) {
                CreatureFactory.Create(ActorType.SERPENT, new Point2(Settings.Get().tileMapWidth / 2, ii));

            }

        }

    }

}
