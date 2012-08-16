package com.aigilas.skills.impl;import com.aigilas.creatures.CreatureFactory;import com.aigilas.creatures.StatType;import com.aigilas.entities.Elements;import com.aigilas.skills.AnimationType;import com.aigilas.skills.ISkill;import com.aigilas.skills.SkillId;import com.spx.entities.IEntity;    public class VaporImplantSkill  extends  ISkill    {        public VaporImplantSkill()            { super(SkillId.VAPOR_IMPLANT, AnimationType.RANGED);            AddCost(StatType.MANA, 10);            Add(Elements.PHYSICAL, Elements.AIR);        }@Override        public  void Affect(IEntity target)        {            CreatureFactory.CreateMinion(SkillId.VAPOR_CLOUD, _source, null, target.GetLocation());        }    }