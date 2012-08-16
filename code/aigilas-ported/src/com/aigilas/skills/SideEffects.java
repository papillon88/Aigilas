package com.aigilas.skills;import java.util.ArrayList;import java.util.List;import com.aigilas.creatures.ICreature;import com.aigilas.entities.SkillEffect;import com.aigilas.management.SpriteType;import com.spx.core.Point2;import com.spx.entities.EntityManager;    public class SideEffects    {        protected ISkill _parent;        protected int _animation;        protected List<SkillEffect> _effectGraphics = new ArrayList<SkillEffect>();        protected int _effectSprite = SpriteType.SKILL_EFFECT;        protected float _effectStrength;        protected boolean _isPersistent = false;        public SideEffects(int effectGraphic,int animation,ISkill parent)        {            _parent = parent;            _effectStrength = parent.GetStrength();            _effectSprite = effectGraphic;            _animation = animation;        }        public void Generate(Point2 gridLocation,Point2 velocity,ICreature source)        {            SkillEffect effect = new SkillEffect(gridLocation, velocity, source, _parent);            _effectGraphics.add(effect);            EntityManager.addObject(effect);        }        public SkillEffect GetFirstGraphic()        {            if(_effectGraphics.size()>0)                return _effectGraphics.get(0);            return null;        }        public int GetSpriteType()        {            return _effectSprite;        }        public int GetAnimationType()        {            return _animation;        }    }    