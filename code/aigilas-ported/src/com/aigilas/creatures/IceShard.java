package com.aigilas.creatures;import com.aigilas.entities.Elements;import com.aigilas.skills.SkillId;import com.aigilas.strategies.Strategy;import com.aigilas.strategies.StrategyFactory;    public class IceShard  extends  Minion    {        public IceShard()            { super(AigilasActorType.MINION);            _strategy = StrategyFactory.Create(Strategy.MinionOneUse, this);            Add(SkillId.ICE_SHARD);            _composition.add(Elements.WATER);        }    }