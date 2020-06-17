package org.sydne.leafs.combat.looting.children;

import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.scene.Pickables;
import org.sydne.leafs.Leaf;

import java.util.List;

public class LootFeathersLeaf implements Leaf {
    Pickable feather = Pickables.getNearest("Feather");

    @Override
    public boolean validate() {
        return feather != null && feather.distance() < 10;
    }

    @Override
    public void execute() {
        feather.interact("Take");
    }

    @Override
    public List<Leaf> children() {
        return null;
    }
}
