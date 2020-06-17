package org.sydne.leafs.combat.cooking;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.sydne.leafs.Leaf;
import org.sydne.leafs.cutthewood.woodcutting.WoodCuttingLeaf;

import java.util.List;

public class GetLogLeaf implements Leaf {

    Item logs = Inventory.getFirst("Logs");
    Pickable pickable = Pickables.getNearest(item -> (item.getName().equals("Logs") && item.distance() < 15));
    Position treePosition = new Position(3176,3280,0);

    @Override
    public boolean validate() {
        return logs == null;
    }

    @Override
    public void execute() {
        if(Inventory.isFull()){
            Item chicken = Inventory.getFirst("Raw Chicken");
            chicken.interact("Drop");
        }
        if(pickable != null) {
            pickable.interact("Take");
        }
    }

    @Override
    public List<Leaf> children() {
        if(pickable != null) {
            return null;
        }
        return List.of(new WoodCuttingLeaf(treePosition));
    }
}
