package aigilas.creatures;

import aigilas.entities.Elements;
import aigilas.management.SpriteType;
import aigilas.skills.SkillId;
import spx.bridge.ActorType;

public class Lust extends AbstractCreature {
    public Lust() {
        super(ActorType.LUST, SpriteType.LUST);
        Compose(Elements.FIRE);
        Strengths(StatType.STRENGTH, StatType.STRENGTH);
        Add(SkillId.BRIMSTONE);
    }
}