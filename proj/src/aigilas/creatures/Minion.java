package aigilas.creatures;

import aigilas.entities.SkillEffect;
import aigilas.skills.SkillId;
import aigilas.skills.SkillPool;
import spx.bridge.ActorType;
import spx.core.Point2;
import spx.core.Settings;

public class Minion extends ICreature {
    public Minion(ActorType actorType, float coolDown) {
        _actorType = actorType;
        _baseStats = new Stats(80f, 999f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, coolDown);
    }

    public Minion(ActorType actorType) {
        this(actorType, Settings.get().defaultSpeed);
    }

    public Minion() {
        this(ActorType.MINION, Settings.get().defaultSpeed);
    }

    public void init(ICreature source, SkillEffect effectGraphic) {
        _master = source;
        setup(source.getLocation(), _actorType, _baseStats, null, false);
        if (null != effectGraphic) {
            setSkillVector(effectGraphic.getDirection().rotate180());
        } else {
            setSkillVector(new Point2(0, 1));
        }
        _strategy.addTargets(_master);
    }

    protected void add(SkillId skill) {
        if (_skills == null) {
            _skills = new SkillPool(this);
        }
        _skills.add(skill);
    }
}
