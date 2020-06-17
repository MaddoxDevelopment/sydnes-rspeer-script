package org.sydne.leafs.cutthewood.woodcutting;

import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.sydne.leafs.Leaf;

import java.util.List;

public class InteractTreeLeaf implements Leaf {
    @Override
    public boolean validate() {
        return Players.getLocal().getAnimation() == -1;
    }

    @Override
    public void execute() {
        var tree = SceneObjects.getNearest("Tree");
        tree.interact("Chop down");
    }

    @Override
    public List<Leaf> children() {
        return null;
    }
}
