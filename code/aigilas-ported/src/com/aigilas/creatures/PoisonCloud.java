package com.aigilas.creatures;import com.aigilas.entities.Elements;import com.aigilas.skills.SkillId;import com.aigilas.strategies.Strategy;import com.aigilas.strategies.StrategyFactory;public     class PoisonCloud  extends  Minion    {        public PoisonCloud()            { super(AigilasActorType.MINION);            _strategy = StrategyFactory.Create(Strategy.MinionOneUse, this);            Add(SkillId.POISON_CLOUD);            _composition.add(Elements.MENTAL);        }    }