package aigilas.classes;

import aigilas.creatures.Stats;
import aigilas.skills.SkillId;

public class LustAcolyte extends CreatureClass {
    public LustAcolyte() {
        super(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0));
        Add(1, SkillId.REGEN_ALL);
        Add(2, SkillId.SPEED_UP);
        Add(3, SkillId.ENVENOM);
        Add(4, SkillId.COLD_SHOULDER);
        Add(5, SkillId.CAVALRY);
    }
}
