package aigilas.items;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;

import java.util.HashMap;

public class Equipment {
    private HashMap<ItemSlot, GenericItem> _slots = new HashMap<>();
    private ICreature _parent;

    public Equipment(ICreature owner) {
        _parent = owner;
    }

    public void Unequip(GenericItem item) {
        if (IsRegistered(item)) {
            Unregister(item);
        }
    }

    public void Register(GenericItem item) {
        ItemSlot itemSlot = item.GetItemClass().Slot;
        if (_slots.containsKey(itemSlot)) {
            Unequip(_slots.get(itemSlot));
        }
        _slots.put(itemSlot, item);
    }

    public void Unregister(GenericItem item) {
        ItemSlot itemSlot = item.GetItemClass().Slot;
        if (_slots.containsKey(itemSlot)) {
            _parent.PickupItem(_slots.get(itemSlot));
            _slots.remove(itemSlot);
        }
    }

    public boolean IsRegistered(GenericItem item) {
        ItemSlot itemClass = item.GetItemClass().Slot;
        if (_slots.containsKey(itemClass)) {
            return (item == _slots.get(itemClass));
        }
        return false;
    }

    private float bonusSum;

    public float CalculateBonus(StatType stat) {
        bonusSum = 0;
        for (ItemSlot slot : _slots.keySet()) {
            bonusSum += _slots.get(slot).GetStatBonus(stat);
        }
        return bonusSum;
    }

    public HashMap<ItemSlot, GenericItem> GetItems() {
        return _slots;
    }
}
