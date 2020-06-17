package org.sydne.leafs.cutthewood.bank;

import org.rspeer.runetek.api.component.tab.Inventory;
import org.sydne.leafs.Leaf;

import java.util.List;

public class BankLeaf implements Leaf {

    @Override
    public boolean validate() {
        return Inventory.isFull();
    }

    @Override
    public void execute() {

    }

    @Override
    public List<Leaf> children() {
        return List.of(new OpenBankLeaf(), new DepositBankLeaf());
    }

}
