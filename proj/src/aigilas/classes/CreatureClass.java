package aigilas.classes;

import aigilas.creatures.StatType;
import aigilas.creatures.Stats;
import aigilas.skills.SkillId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class CreatureClass {
    private Stats _stats;
    protected HashMap<Integer, SkillId> _skillUnlocks = new HashMap<Integer, SkillId>();
    public static CreatureClass NULL = new NoClass();

    protected CreatureClass() {
    }

    protected CreatureClass(Stats stats) {
        _stats = new Stats(stats);
    }

    public float getBonus(int level, StatType stat) {
        return _stats.getBonus(level, stat);
    }

    public List<SkillId> getLevelSkills(int level) {
        List<SkillId> results = new ArrayList<SkillId>();
        for (Integer key : _skillUnlocks.keySet()) {
            if (key <= level) {
                results.add(_skillUnlocks.get(key));
            }
        }
        return results;
    }

    protected void add(int level, SkillId skillId) {
        _skillUnlocks.put(level, skillId);
    }
}
