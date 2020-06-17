package org.sydne.leafs.cutthewood.bank;

import org.rspeer.runetek.api.component.Bank;
import org.sydne.leafs.Leaf;

import java.util.List;

public class DepositBankLeaf implements Leaf {

    @Override
    public boolean validate() {
        return Bank.isOpen();
    }

    @Override
    public void execute() {
        Bank.depositAllExcept(s -> s.getName().contains("axe"));
    }

    @Override
    public List<Leaf> children() {
        return null;
    }
}
