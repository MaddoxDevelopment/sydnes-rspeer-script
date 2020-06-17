package org.sydne.leafs.cutthewood.woodcutting;

import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.sydne.leafs.Leaf;

import java.util.List;

public class WoodCuttingLeaf implements Leaf {

    private Position treePosition = null;

    public WoodCuttingLeaf(Position treePosition){
        this.treePosition = treePosition;
    }

    @Override
    public boolean validate() {
        return !Inventory.isFull() && Players.getLocal().getAnimation() == -1;
    }

    @Override
    public void execute() {

    }

    @Override
    public List<Leaf> children() {
        return List.of(new WalkToTreesLeaf(treePosition), new InteractTreeLeaf());
    }
}
