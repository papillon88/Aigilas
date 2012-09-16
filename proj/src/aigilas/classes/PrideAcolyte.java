package aigilas.classes;

import aigilas.creatures.Stats;
import aigilas.skills.SkillId;

public class PrideAcolyte extends CreatureClass {
    public PrideAcolyte() {
        super(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0));
        Add(1, SkillId.STRENGTH_UP);
        Add(2, SkillId.MANA_UP);
        Add(3, SkillId.ELECTRIFY);
        Add(4, SkillId.WALL_PUNCH);
        Add(5, SkillId.MIMIC);
        Add(6, SkillId.VALEDICTORIAN);
    }
}