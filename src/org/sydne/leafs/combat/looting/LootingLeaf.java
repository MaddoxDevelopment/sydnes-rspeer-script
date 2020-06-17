package org.sydne.leafs.combat.looting;

import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.sydne.leafs.Leaf;
import org.sydne.leafs.combat.looting.children.DropBonesLeaf;
import org.sydne.leafs.combat.looting.children.LootBonesLeaf;
import org.sydne.leafs.combat.looting.children.LootFeathersLeaf;
import org.sydne.leafs.combat.looting.children.LootRawChickenLeaf;

import java.util.List;

public class LootingLeaf implements Leaf {
    @Override
    public boolean validate() {
        if(Players.getLocal().getTarget() != null || Inventory.getFreeSlots() <= 1){
            return false;
        }
        for (Leaf child : children()) {
            if(child.validate()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {

    }

    @Override
    public List<Leaf> children() {
        return List.of(new DropBonesLeaf(), new LootBonesLeaf(), new LootFeathersLeaf(), new LootRawChickenLeaf());
    }
}
