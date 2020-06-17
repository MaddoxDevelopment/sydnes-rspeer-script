package org.sydne.leafs.combat.cooking;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.sydne.leafs.Leaf;

import java.util.List;

public class DropUnwanted implements Leaf {

    private List<String> unwantedItems = null;

    public DropUnwanted(List unwantedItems){
        this.unwantedItems = unwantedItems;
    }

    @Override
    public boolean validate() {
        for (String unwantedItem : unwantedItems) {
            if(Inventory.contains(unwantedItem)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {
        for (String unwantedItem : unwantedItems) {
            Item item = Inventory.getFirst(unwantedItem);
            if(item == null){
                return;
            }
            item.interact("Drop");
        }
    }

    @Override
    public List<Leaf> children() {
        return null;
    }
}
