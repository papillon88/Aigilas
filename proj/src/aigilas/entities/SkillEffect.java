package aigilas.entities;

import aigilas.creatures.ICreature;
import aigilas.skills.ISkill;
import aigilas.skills.SkillFactory;
import aigilas.skills.animations.SkillAnimation;
import spx.bridge.ActorType;
import spx.bridge.DrawDepth;
import spx.bridge.EntityType;
import spx.core.Point2;
import spx.core.Settings;
import spx.entities.Entity;
import spx.entities.EntityManager;
import spx.entities.IActor;
import spx.entities.IEntity;
import spx.particles.ParticleEngine;
import spx.particles.behaviors.FollowBehavior;

import java.util.List;

public class SkillEffect extends Entity {
    private static final float _strengthDecayAmount = .75f;
    public static final float DefaultStrength = 1;
    private static final float CoolDown = Settings.get().defaultSpeed / 8;

    private Point2 _velocity = new Point2(0, 0);
    private ICreature _source;
    private ISkill _skill;
    private float _currentStrength = 0;
    private float _startingStrength = 0;
    private SkillAnimation _animation;
    private float _coolDown = CoolDown;
    private Point2 _direction = new Point2(0, 0);

    public SkillEffect(Point2 gridLocation, Point2 velocity, ICreature source, ISkill skill) {
        _skill = skill;
        initialize(gridLocation, _skill.getSpriteType(), EntityType.SKILL_EFFECT, DrawDepth.BaseSkillEffect);
        _velocity.copy(velocity);
        _direction.copy(velocity);
        _source = source;
        _startingStrength = _currentStrength = _skill.getStrength();
        _animation = SkillFactory.create(_skill.getAnimationType());
        _graphic.setColor(skill.getElementColor());
        _graphic.setAlpha(0);
        ParticleEngine.emit(FollowBehavior.getInstance(), this, _graphic.getColor());
    }

    private IEntity hitTarget;

    public void cleanup(Entity target) {
        _isActive = false;
        _skill.cleanup(target, this);
    }

    public Point2 getDirection() {
        return _direction;
    }

    @Override
    public void update() {
        for (EntityType targetType : _skill.getTargetTypes()) {
            List<IEntity> targets = EntityManager.getEntities(targetType, this.getLocation());
            if (targets != null && targets.size() > 0) {
                hitTarget = targets.get(0);
                if (null != hitTarget && hitTarget != this && hitTarget != _source) {
                    _skill.affect(hitTarget);
                    cleanup(this);
                }
            }
        }
        for (ActorType targetType : _source.getTargetActorTypes()) {
            List<IActor> targets = EntityManager.getActorsAt(this.getLocation(), targetType);
            if (targets != null && targets.size() > 0) {
                hitTarget = targets.get(0);
                if (null != hitTarget && hitTarget != this && hitTarget != _source) {
                    _skill.affect(hitTarget);
                    cleanup(this);
                }
            }
        }
        if (_currentStrength < .001) {
            cleanup(_source);
        } else {
            _coolDown--;
            if (_coolDown <= 0) {
                if (_startingStrength == 0) {
                    _startingStrength = _currentStrength;
                }
                _currentStrength *= _strengthDecayAmount;
                _velocity.setX(_velocity.X * _currentStrength);
                _velocity.setY(_velocity.Y * _currentStrength);
                _animation.animate(this, _source, _velocity);
                _isActive = _skill.affectTarget(_source, this);
                _coolDown = CoolDown;
            }
        }
    }
}
