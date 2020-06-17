package org.sydne.leafs.combat.attacking.children;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.ui.Log;
import org.sydne.leafs.Leaf;

import java.util.List;

public class GetToChickenLeaf implements Leaf {

    public static Npc chicken;
    public static Position chickenPinPosition = new Position(3177,3297,0);

    @Override
    public boolean validate() {
        chicken = Npcs.getNearest("Chicken");
        if(chicken == null){
            Movement.walkTo(chickenPinPosition);
            chicken = Npcs.getNearest("Chicken");
        }
        Log.info(chicken);
        return !Movement.isInteractable(chicken.getPosition(), false);
    }

    @Override
    public void execute() {
        Movement.walkTo(chicken.getPosition());
    }

    @Override
    public List<Leaf> children() {
        return null;
    }
}
