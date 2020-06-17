package org.sydne.leafs.combat.looting.children;

import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.scene.Pickables;
import org.sydne.leafs.Leaf;

import java.util.List;

public class LootRawChickenLeaf implements Leaf {
    Pickable rawChicken = Pickables.getNearest("Raw chicken");

    @Override
    public boolean validate() {
        return rawChicken != null && rawChicken.distance() < 10;
    }

    @Override
    public void execute() {
        rawChicken.interact("Take");
    }

    @Override
    public List<Leaf> children() {
        return null;
    }
}
