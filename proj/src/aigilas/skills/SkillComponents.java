package aigilas.skills;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import spx.bridge.ActorType;
import spx.bridge.EntityType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkillComponents {
    protected List<Elements> _elements;
    protected StatBuff _buff;
    protected float _effectStrength = 0;
    protected boolean _isPersistent = false;
    protected List<EntityType> _targetTypes = Arrays.asList(EntityType.WALL);
    protected List<ActorType> _targetActorTypes = new ArrayList<>();

    public SkillComponents(float strength, boolean isPersistent) {
        _effectStrength = strength;
        _isPersistent = isPersistent;
        _elements = new ArrayList<>();
    }

    public void addElements(Elements... elements) {
        for (Elements element : elements) {
            _elements.add(element);
        }
    }

    public void buff(ICreature target) {
        target.addBuff(_buff);
    }

    public void setBuff(StatType stat, float amount) {
        _buff = new StatBuff(stat, amount);
    }

    public float getStrength() {
        return _effectStrength;
    }

    public boolean isPersistent() {
        return _isPersistent;
    }

    public List<Elements> getElements() {
        return _elements;
    }

    public List<EntityType> getTargetTypes()

    {
        return _targetTypes;
    }
}
