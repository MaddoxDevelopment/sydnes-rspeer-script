package org.sydne.leafs.combat.cooking;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.component.Production;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.sydne.leafs.Leaf;

import java.util.List;
import java.util.function.Predicate;

public class CookChicken implements Leaf {

    Predicate<SceneObject> filter = sceneObject -> sceneObject.distance() < 7 && sceneObject.getName().equals("Fire");

    @Override
    public boolean validate() {
        return SceneObjects.getNearest(filter) != null;
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
