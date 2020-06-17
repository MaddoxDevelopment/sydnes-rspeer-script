package org.sydne.leafs.combat.attacking.children;

import org.rspeer.runetek.api.scene.Players;
import org.sydne.leafs.Leaf;

import java.util.List;

public class InCombatLeaf implements Leaf {
    @Override
    public boolean validate() {
        return Players.getLocal().getTarget() != null;
    }

    @Override
    public void execute() {

    }

    @Override
    public List<Leaf> children() {
        return null;
    }
}
