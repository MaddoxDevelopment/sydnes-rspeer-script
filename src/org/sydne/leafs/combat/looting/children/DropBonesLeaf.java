package org.sydne.leafs.combat.looting.children;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.sydne.leafs.Leaf;

import java.util.List;

public class DropBonesLeaf implements Leaf {
    @Override
    public boolean validate() {
        return Inventory.contains("Bones");
    }

    @Override
    public void execute() {
        Item bones = Inventory.getFirst("Bones");
        bones.interact("Bury");
    }

    @Override
    public List<Leaf> children() {
        return null;
    }
}
