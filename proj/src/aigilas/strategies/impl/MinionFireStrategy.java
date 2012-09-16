package aigilas.strategies.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.strategies.IStrategy;
import aigilas.strategies.Strategy;

public class MinionFireStrategy extends IStrategy {
    public MinionFireStrategy(ICreature parent)

    {
        super(parent, Strategy.MinionFire);

        parent.SetSkillVector(parent.GetSkillVector());
    }

    @Override
    public void Act() {
        if (_parent.IsCooledDown()) {
            _parent.UseActiveSkill();
            _parent.ApplyDamage(5, null, false);
            _parent.Set(StatType.MOVE_COOL_DOWN, 0);
        }
    }
}
