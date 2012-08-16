package com.aigilas.skills.behaviors;import com.aigilas.creatures.ICreature;import com.aigilas.creatures.StatBuff;import com.aigilas.creatures.StatType;import com.aigilas.creatures.Stats;import com.aigilas.entities.Extensions;import com.aigilas.entities.SkillEffect;import com.aigilas.skills.ISkill;import com.aigilas.skills.SideEffects;import com.spx.entities.Entity;import com.spx.entities.IEntity;    public class SkillBehavior    {        protected SideEffects _sideEffects;        protected ISkill _parent;        protected boolean _used = false;        protected Stats _cost;        public SkillBehavior(int effectGraphic, int animation,ISkill parentSkill)        {            _parent = parentSkill;            _sideEffects = new SideEffects(effectGraphic, animation,_parent);            _cost = new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);        }        public int GetSpriteType() { return _sideEffects.GetSpriteType(); }        public void Activate(ICreature target) {}        public void Cleanup(Entity target, SkillEffect source) {}        public boolean IsActive()        {            return !_used;        }        public SkillEffect GetGraphic()        {            return _sideEffects.GetFirstGraphic();        }        public void AddCost(String stat, float cost)        {            _cost.AddBuff(new StatBuff(stat, cost));        }        protected boolean SubtractCost(ICreature owner)        {            boolean costPaid = false;            for (String stat:StatType.Values)            {                if (stat != StatType.REGEN)                {                    if (owner.LowerStat(stat, _cost.Get(stat)))                    {                        costPaid = true;                    }                }            }            return costPaid;        }        private IEntity hitTarget;        private ICreature hitCreature;        public boolean AffectTarget(ICreature source,SkillEffect graphic)        {            hitTarget = source.GetTargets().GetCollidedTarget(graphic);            if (null != hitTarget && hitTarget!=source)            {                _parent.Affect(hitTarget);                hitCreature = Extensions.IsCreature(hitTarget);                if (hitCreature != null)                {                    hitCreature.Combo(_parent.GetElements());                    hitCreature.React(_parent.GetSkillId());                }                if (!_parent.IsPersistent())                {                    return false;                }            }                        return true;        }        public int GetAnimationType()        {            return _sideEffects.GetAnimationType();        }        public float GetCost()        {            return _cost.Get(StatType.MANA);        }    }