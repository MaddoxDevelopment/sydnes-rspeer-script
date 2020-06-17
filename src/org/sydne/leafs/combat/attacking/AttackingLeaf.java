package org.sydne.leafs.combat.attacking;

import org.sydne.leafs.Leaf;
import org.sydne.leafs.combat.attacking.children.ClickChickenLeaf;
import org.sydne.leafs.combat.attacking.children.GetToChickenLeaf;
import org.sydne.leafs.combat.attacking.children.InCombatLeaf;

import java.util.List;

public class AttackingLeaf implements Leaf {
    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void execute() {

    }

    @Override
    public List<Leaf> children() {
        return List.of(new InCombatLeaf(), new GetToChickenLeaf(), new ClickChickenLeaf());
    }
}
