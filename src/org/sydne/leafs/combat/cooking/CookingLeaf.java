package org.sydne.leafs.combat.cooking;

import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.sydne.leafs.Leaf;

import java.util.Collections;
import java.util.List;

public class CookingLeaf implements Leaf {
    @Override
    public boolean validate() {
        return Inventory.getFreeSlots() <= 1 && Inventory.contains("Raw chicken");
    }

    @Override
    public void execute() {

    }

    @Override
    public List<Leaf> children() {
        return List.of(new CookChicken(), new GetLogLeaf(), new MakeFireLeaf());
    }
}
