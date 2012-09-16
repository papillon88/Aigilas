package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.items.GenericItem;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class ThrowItemSkill extends ISkill {
    private float _itemStrength = 0;

    public ThrowItemSkill()

    {
        super(SkillId.THROW_ITEM, AnimationType.RANGED);

        Add(Elements.AIR);
        AddCost(StatType.MANA, 0);

    }

    @Override
    public void Activate(ICreature source) {
        GenericItem item = source.DestroyRandomItemFromInventory();
        if (item != null) {
            _itemStrength = item.Modifers.GetSum() * 3;
            super.Activate(source);

        }

    }

    @Override
    public void Affect(ICreature target)

    {
        target.ApplyDamage(_itemStrength, _source);

    }

}