package aigilas.skills;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.entities.Extensions;
import aigilas.entities.SkillEffect;
import aigilas.management.SpriteType;
import aigilas.skills.behaviors.SkillBehavior;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;
import com.badlogic.gdx.graphics.Color;
import spx.bridge.EntityType;
import spx.entities.Entity;
import spx.entities.EntityManager;
import spx.entities.IActor;
import spx.entities.IEntity;

import java.util.List;

public abstract class ISkill {
    protected ICreature _source;
    protected SkillId _implementationId;
    protected SkillBehavior _behavior;
    protected SkillComponents _components;
    public boolean StartOffCenter = false;

    protected ISkill(SkillId implementationId, AnimationType animation, float strength, boolean isPersistent, SpriteType effectGraphic) {
        _implementationId = implementationId;
        _components = new SkillComponents(strength, isPersistent);
        _behavior = SkillFactory.Create(animation, effectGraphic, this);
    }

    protected ISkill(SkillId implementationId, AnimationType animation, float strength, boolean isPersistent) {
        this(implementationId, animation, strength, isPersistent, SpriteType.SKILL_EFFECT);
    }

    protected ISkill(SkillId implementationId, AnimationType animation, float strength) {
        this(implementationId, animation, strength, false, SpriteType.SKILL_EFFECT);
    }

    protected ISkill(SkillId implementationId, AnimationType animation) {
        this(implementationId, animation, SkillEffect.DefaultStrength, false, SpriteType.SKILL_EFFECT);
    }

    protected void Add(Elements... elements) {
        _components.AddElements(elements);
    }

    protected void AddCost(StatType stat, float cost) {
        _behavior.AddCost(stat, cost);
    }

    public void SetBuff(StatType stat, float amount) {
        _components.SetBuff(stat, amount);
    }

    public void Activate(ICreature source) {
        _source = source;
        _behavior.Activate(source);
    }

    public void Affect(IEntity target) {
        ICreature creature = Extensions.IsCreature(target);
        if (creature != null) {
            Affect(creature);
        }
    }

    public void ApplyToPlayers(Status statusId) {
        for (IActor player : EntityManager.GetPlayers()) {
            StatusFactory.Apply((ICreature) player, statusId);
        }
    }

    public List<Elements> GetElements() {
        return _components.GetElements();
    }

    public void Affect(ICreature target) {

    }

    public SpriteType GetSpriteType() {
        return _behavior.GetSpriteType();
    }

    public AnimationType GetAnimationType() {
        return _behavior.GetAnimationType();
    }

    public float GetStrength() {
        return _components.GetStrength();
    }

    public boolean IsPersistent() {
        return _components.IsPersistent();
    }

    public Color GetElementColor() {
        return _components.GetElements().get(0).Tint;
    }

    public void Cleanup(Entity target, SkillEffect source) {
        _behavior.Cleanup(target, source);
    }

    public boolean AffectTarget(ICreature target, SkillEffect graphic) {
        return _behavior.AffectTarget(target, graphic);
    }

    public boolean IsActive() {
        return _behavior.IsActive();
    }

    public List<EntityType> GetTargetTypes() {
        return _components.GetTargetTypes();
    }

    public SkillId GetSkillId() {
        return _implementationId;
    }

    public float GetCost() {
        return _behavior.GetCost();
    }
}
