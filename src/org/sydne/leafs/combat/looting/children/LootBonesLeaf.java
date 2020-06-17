package org.sydne.leafs.combat.looting.children;

import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Pickables;
import org.sydne.leafs.Leaf;

import java.util.List;

public class LootBonesLeaf implements Leaf {

    Pickable bones = Pickables.getNearest("Bones");

    @Override
    public boolean validate() {
        return bones != null && bones.distance() < 10;
    }

    @Override
    public void execute() {
        bones.interact("Take");
    }

    @Override
    public List<Leaf> children() {
        return null;
    }
}
