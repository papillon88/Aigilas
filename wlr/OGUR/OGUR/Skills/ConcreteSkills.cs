﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.GameObjects;
using OGUR.Skills;
using OGUR.Sprites;

namespace OGUR.Skills
{
    public class NoSkill:ISkill
    {
        public NoSkill():base(SkillId.NO_SKILL,Skill.Animation.NONE){}
        public override void Activate(ICreature source){}
        public override void Affect(GameplayObject target){}
        public override void Affect(ICreature target){}
    }
    public class FireballSkill:ISkill
    {
        public FireballSkill() : base(SkillId.FIREBALL,Skill.Animation.RANGED)
        { Add(Elements.FIRE);AddCost(StatType.MANA,10);}

        public override void Affect(ICreature target)
        {
            target.ApplyDamage(20,target);
        }
    }
    public class ThrashSkill : ISkill
    {
        public ThrashSkill() : base(SkillId.THRASH, Skill.Animation.CLOUD){ AddCost(StatType.MANA, 40); }
        public override void Affect(ICreature target)
        {
            target.ApplyDamage(30,target);
        }
    }
    public class BulkSkill : ISkill
    {
        public BulkSkill(): base(SkillId.BULK, Skill.Animation.SELF)
        {
            AddCost(StatType.MANA, 10);
            m_buff = new StatBuff(StatType.STRENGTH, 20);
        }
        public override void Affect(ICreature target)
        {
            Buff(target);
        }
    }
    public class FloorSpikesSkill : ISkill
    {
        public FloorSpikesSkill(): base(SkillId.FLOOR_SPIKES, Skill.Animation.STATIONARY){AddCost(StatType.MANA, 10);}
        public override void Affect(ICreature target)
        {
            Buff(target);
        }
    }
    public class DartSkill : ISkill
    {
        public DartSkill(): base(SkillId.DART, Skill.Animation.RANGED){AddCost(StatType.MANA, 10);}
        public override void Affect(ICreature target)
        {
            Buff(target);
        }
    }
    public class AcidNozzleSkill : ISkill
    {
        public AcidNozzleSkill(): base(SkillId.ACID_NOZZLE, Skill.Animation.STATIONARY){AddCost(StatType.MANA, 10);}
        public override void Affect(ICreature target)
        {
            Buff(target);
        }
    }
    public class RemoteMineSkill : ISkill
    {
        public RemoteMineSkill(): base(SkillId.REMOTE_MINE, Skill.Animation.STATIONARY){AddCost(StatType.MANA, 10);}
        public override void Affect(ICreature target)
        {
            Buff(target);
        }
    }
    public class VaporImplantSkill : ISkill
    {
        public VaporImplantSkill(): base(SkillId.VAPOR_IMPLANT, Skill.Animation.RANGED){AddCost(StatType.MANA, 10);}
        public override void Affect(ICreature target)
        {
            Buff(target);
        }
    }
}