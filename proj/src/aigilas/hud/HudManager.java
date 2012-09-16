package aigilas.hud;

import aigilas.creatures.ICreature;
import aigilas.items.Equipment;
import aigilas.items.Inventory;

public class HudManager {
    private InventoryHud _inventory;
    private SkillHud _skill;

    public HudManager(ICreature parent, Inventory inventory, Equipment equipment) {
        _inventory = new InventoryHud(parent, inventory, equipment);
        _skill = new SkillHud(parent);
        _skill.Toggle();
    }

    public boolean ToggleInventory() {
        _inventory.Toggle();
        _skill.Toggle();
        return _inventory.IsVisible();
    }

    public void Update() {
        _inventory.Update();
        _skill.Update();
    }

    public void Draw() {
        _inventory.Draw();
        _skill.Draw();
    }
}