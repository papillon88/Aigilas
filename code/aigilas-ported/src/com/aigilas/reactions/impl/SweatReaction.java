package com.aigilas.reactions.impl;import com.aigilas.reactions.IReaction;import com.aigilas.statuses.Status;    public class SweatReaction  implements IReaction    {        public void Affect(Creatures.ICreature target)        {            StatusFactory.Apply(target, Status.SlowDown);        }    }