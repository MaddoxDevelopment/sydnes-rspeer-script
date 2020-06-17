package org.sydne.leafs.cutthewood.woodcutting;

import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.sydne.leafs.Leaf;

import java.util.List;

public class WalkToTreesLeaf implements Leaf {

    private Position treePosition = null;

    public WalkToTreesLeaf(Position treePosition){
        this.treePosition = treePosition;
    }

    @Override
    public boolean validate() {
        return treePosition.distance() > 10;
    }

    @Override
    public void execute() {
        Movement.walkTo(treePosition);
    }

    @Override
    public List<Leaf> children() {
        return null;
    }
}
