package com.aigilas.creatures;import com.aigilas.entities.Elements;import com.aigilas.skills.SkillId;import com.aigilas.strategies.Strategy;import com.aigilas.strategies.StrategyFactory;public     class DartTrap  extends  Minion    {        public DartTrap()            { super(AigilasActorType.MINION);            _strategy = StrategyFactory.Create(Strategy.MinionFire, this);            Add(SkillId.DART);            _composition.add(Elements.DARK);        }    }