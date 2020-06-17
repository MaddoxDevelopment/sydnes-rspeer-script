package org.sydne.leafs.combat.cooking;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.sydne.leafs.Leaf;

import java.util.List;

public class MakeFireLeaf implements Leaf {
    @Override
    public boolean validate() {
        return Players.getLocal().getAnimation() == -1;
    }

    @Override
    public void execute() {
        Inventory.use(item -> item.getName().contains("Tinderbox"), Inventory.getFirst("Logs"));
    }

    @Override
    public List<Leaf> children() {
        return null;
    }
}
