package com.aigilas.reactions.impl;import com.aigilas.creatures.ICreature;import com.aigilas.reactions.IReaction;import com.aigilas.statuses.Status;    public class LacticAcidReaction  implements IReaction    {        @Override		public void Affect(ICreature target)        {            StatusFactory.Apply(target, Status.WeakMuscles);        }    }