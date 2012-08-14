package com.aigilas.entities;import com.xna.wrapper.*;import java.util.*;import com.aigilas.creatures.*;import com.aigilas.skills.*;import com.spx.core.*;import com.spx.entities.*;import com.spx.particles.*;import com.spx.particles.behaviors.FollowBehavior;
    public class SkillEffect extends Entity
    {
        private static final float _strengthDecayAmount = .75f;
        public static final float DefaultStrength = 1;
        
        private Point2 _velocity = new Point2(0,0);
        private ICreature _source;
        private ISkill _skill;
        private float _currentStrength = 0;
        private float _startingStrength = 0;
        private SkillAnimation _animation;
        private float _coolDown = CoolDown;
        private static final float CoolDown = Stats.DefaultCoolDown/8;
        private Point2 _direction = new Point2(0, 0);

        public SkillEffect(Point2 gridLocation,Point2 velocity,ICreature source,ISkill skill)
        {
            _skill = skill;
            Initialize(gridLocation, _skill.GetSpriteType(), com.aigilas.EntityType.SKILL_EFFECT,.7f);
            _velocity.Copy(velocity);
            _direction.Copy(velocity);
            _source = source;
            _startingStrength = _currentStrength = _skill.GetStrength();
            _animation = SkillFactory.Create(_skill.GetAnimationType());
            _graphic.SetColor(skill.GetElementColor());
            _graphic.SetAlpha(0);
            ParticleEngine.Emit(FollowBehavior.GetInstance(), this,_graphic.GetColor());
        }

        private IEntity hitTarget;

        public void Cleanup(Entity target)        {
            _isActive = false;
            _skill.Cleanup(target,this);
        }

        public Point2 GetDirection()
        {
            return _direction;
        }

@Override        public  void Update()
        {
            for (Integer targetType:_skill.GetTargetTypes())
            {
                List<IEntity> targets = EntityManager.GetEntities(targetType, this.GetLocation());
                if (targets != null && targets.size() > 0)
                {
                    hitTarget = targets.get(0);
                    if (null != hitTarget && hitTarget != this && hitTarget != _source)
                    {
                        _skill.Affect(hitTarget);
                        Cleanup(this);
                    }
                }
            }
            for (Integer targetType:_source.GetTargetActorTypes())
            {
                List<IActor> targets = EntityManager.GetActorsAt(this.GetLocation(),targetType);
                if (targets != null && targets.size() > 0)
                {
                    hitTarget = targets.get(0);
                    if (null != hitTarget && hitTarget != this && hitTarget != _source)
                    {
                        _skill.Affect(hitTarget);
                        Cleanup(this);
                    }
                }
            }
            if(_currentStrength < .001)
            {
                Cleanup(_source);
            }
            else
            {
                _coolDown--;
                if (_coolDown <= 0)
                {
                    if (_startingStrength == 0)
                    {
                        _startingStrength = _currentStrength;
                    }
                    _currentStrength *= _strengthDecayAmount;
                    _velocity.SetX(_velocity.X * _currentStrength);
                    _velocity.SetY(_velocity.Y * _currentStrength);
                    _animation.Animate(this, _source, _velocity);
                    _isActive = _skill.AffectTarget(_source, this);
                    _coolDown = CoolDown;
                }
            }
        }
    }
