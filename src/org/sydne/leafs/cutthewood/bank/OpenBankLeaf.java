package org.sydne.leafs.cutthewood.bank;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.component.Bank;
import org.sydne.leafs.Leaf;

import java.util.List;

public class OpenBankLeaf implements Leaf {

    @Override
    public boolean validate() {
        return !Bank.isOpen();
    }

    @Override
    public void execute() {
        Bank.open(BankLocation.DRAYNOR);
    }

    @Override
    public List<Leaf> children() {
        return null;
    }
}
