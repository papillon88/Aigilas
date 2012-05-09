﻿using System;
using OGUR.Creatures;
using SPX.Core;

namespace OGUR.Strategies
{
    public class ConfusedStrategy : IStrategy
    {
        public ConfusedStrategy(ICreature parent)
            : base(parent,Strategy.Confused)
        {
        }
        public override void Act()
        {
            _parent.MoveIfPossible(RNG.Rand.Next(3) - 1, RNG.Rand.Next(3) - 1);
        }
    }

    public class ConfusedAndDyingStrategy: ConfusedStrategy
    {
        public ConfusedAndDyingStrategy(ICreature parent)
            : base(parent)
        {
        }
        public override void Act()
        {
            base.Act();
            _parent.ApplyDamage(1.1f,null,false);
        }
    }
}