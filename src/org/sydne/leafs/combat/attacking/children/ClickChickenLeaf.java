package org.sydne.leafs.combat.attacking.children;

import org.sydne.leafs.Leaf;

import java.util.List;

import static org.sydne.leafs.combat.attacking.children.GetToChickenLeaf.chicken;

public class ClickChickenLeaf implements Leaf {
    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void execute() {
        chicken.interact("Attack");
    }

    @Override
    public List<Leaf> children() {
        return null;
    }
}
