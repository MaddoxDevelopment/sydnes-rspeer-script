package org.sydne.leafs.combat.cooking;

import org.rspeer.runetek.api.component.Production;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.sydne.leafs.Leaf;

import java.util.List;

public class CookChicken implements Leaf {
    @Override
    public boolean validate() {
        return SceneObjects.getNearest("Fire") != null;
    }

    @Override
    public void execute() {
        if(Players.getLocal().getAnimation() != -1){
            return;
        }
        if(Production.isOpen()){
            Production.initiate();
        }
        else{
            Inventory.use(item -> item.getName().contains("Raw chicken"), SceneObjects.getNearest("Fire"));
        }
    }

    @Override
    public List<Leaf> children() {
        return null;
    }
}
