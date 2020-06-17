package org.sydne.leafs;

import java.util.List;

public interface Leaf {

    boolean validate();

    void execute();

    List<Leaf> children();

}
