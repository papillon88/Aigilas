package com.aigilas.reactions.impl;import com.aigilas.creatures.ICreature;import com.aigilas.creatures.StatType;import com.aigilas.reactions.IReaction;    public class FastForwardReaction  implements IReaction    {        @Override		public void Affect(ICreature target)        {            target.ApplyDamage(-1, null, false, StatType.AGE);        }    }