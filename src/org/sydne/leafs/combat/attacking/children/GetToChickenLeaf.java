package org.sydne.leafs.combat.attacking.children;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.sydne.leafs.Leaf;

import java.util.List;

public class GetToChickenLeaf implements Leaf {

    public static Npc chicken;

    @Override
    public boolean validate() {
        chicken = Npcs.getNearest("Chicken");
        return !Movement.isInteractable(chicken.getPosition());
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
