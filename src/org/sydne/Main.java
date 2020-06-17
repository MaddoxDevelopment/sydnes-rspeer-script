package org.sydne;

import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptMeta;
import org.sydne.leafs.Leaf;
import org.sydne.leafs.combat.attacking.AttackingLeaf;
import org.sydne.leafs.combat.cooking.CookingLeaf;
import org.sydne.leafs.combat.cooking.DropUnwanted;
import org.sydne.leafs.combat.looting.LootingLeaf;
import org.sydne.leafs.cutthewood.bank.BankLeaf;
import org.sydne.leafs.cutthewood.woodcutting.WoodCuttingLeaf;

import java.util.List;

@ScriptMeta(developer = "Sydne", desc = "Woodcutter", name = "Woodcutter")
public class Main extends Script implements RenderListener {

    List<Leaf> leaves = List.of(new CookingLeaf(), new DropUnwanted(List.of("Burnt chicken")), new LootingLeaf(), new AttackingLeaf());
    Leaf currentLeaf = null;

    @Override
    public int loop() {

        if(Players.getLocal().isAnimating()) {
            return 300;
        }

        for (Leaf leaf : leaves) {
            if(leaf.validate()){
                currentLeaf = leaf;
                leaf.execute();
                iterateChildren(leaf);
                break;
            }
        }
        return 300;
    }

    private void iterateChildren(Leaf leaf) {
        if(leaf.children() == null){
            return;
        }
        for (Leaf child : leaf.children()) {
            if(child.validate()) {
                currentLeaf = child;
                child.execute();
                iterateChildren(child);
                break;
            }
        }
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        if(currentLeaf != null){
            renderEvent.getSource().drawString(currentLeaf.getClass().getSimpleName(), 250, 25);
        }
    }
}
