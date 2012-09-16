package aigilas.hud;

import aigilas.creatures.ICreature;
import aigilas.items.Equipment;
import aigilas.items.GenericItem;
import aigilas.items.ItemSlot;
import spx.core.SpxManager;
import spx.util.StringSquisher;
import spx.util.StringStorage;

public class DeltasHud extends IHud {
    private Equipment _equipment;

    public DeltasHud(ICreature owner, Equipment equipment) {
        super(owner, SpxManager.WindowWidth / 2, SpxManager.WindowHeight / 2);
        _equipment = equipment;
    }

    public void draw() {
        if (_isVisible) {
            _textHandler.draw();
        }
    }

    private GenericItem getEquipmentIn(ItemSlot slot) {
        if (_equipment.getItems().containsKey(slot)) {
            return _equipment.getItems().get(slot);
        }
        return null;
    }

    private static final String delim = "|";
    private static final String positive = "+";
    private static final String title = "Deltas";
    private String display = "EMPTY";

    public void update(GenericItem item, boolean refresh) {
        if (_isVisible) {
            _textHandler.update();
            _textHandler.clear();
            if (item != null && refresh) {
                if (getEquipmentIn(item.getItemClass().Slot) != null) {
                    StringSquisher.clear();
                    for (Float stat : getEquipmentIn(item.getItemClass().Slot).Modifers.getDeltas(item.Modifers)) {
                        StringSquisher.squish(((stat > 0) ? positive : ""), StringStorage.get(stat), delim);
                    }
                    display = StringSquisher.flush();
                }
            }
            _textHandler.writeDefault(title, 30, 260, getHudOrigin());
            _textHandler.writeDefault(display, 30, 290, getHudOrigin());
        }
    }
}
