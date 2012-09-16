package aigilas.classes;

import aigilas.creatures.Stats;
import aigilas.skills.SkillId;

public class SlothAcolyte extends CreatureClass {
    public SlothAcolyte() {
        super(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0));
        Add(1, SkillId.ACID_NOZZLE);
        Add(2, SkillId.FLOOR_SPIKES);
        Add(3, SkillId.REMOTE_MINE);
        Add(4, SkillId.VAPOR_IMPLANT);
        Add(5, SkillId.DART_TRAP);
    }
}