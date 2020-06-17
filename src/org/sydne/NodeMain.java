package org.sydne;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Production;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;
import org.sydne.nodes.ActionResult;
import org.sydne.nodes.Command;

import java.util.List;

@ScriptMeta(developer = "Sydne", desc = "Woodcutter", name = "Woodcutter")
public class NodeMain extends Script {

    @Override
    public int loop() {
        execute(getRawChicken());
        execute(cookChicken());
        return 500;
    }

    private Command getRawChicken() {
        return () -> {
            Log.fine("Getting raw chicken.");
            execute(dropUnwantedItems());

            if(Inventory.contains("Raw chicken") && Inventory.getFreeSlots() <= 1) {
                return ActionResult.Stop;
            }

            // do fighting logic to get raw chicken;
            return ActionResult.Loop;
        };
    }

    private Command dropUnwantedItems() {
        return () -> {
            Log.fine("Drop Unwanted Items.");
            List<String> items = List.of("Burnt chicken");
            boolean hasUnwanted = false;
            for (String name : items) {
                Item item = Inventory.getFirst(name);
                if(item != null) {
                    hasUnwanted = true;
                    item.interact("Drop");
                }
            }
            if(hasUnwanted) {
                return ActionResult.Loop;
            }
            return ActionResult.Stop;
        };
    }

    private Command cookChicken() {
       return () -> {
           Log.fine("Cooking Chicken.");
           // Animating... just do nothing.
           if(Players.getLocal().getAnimation() != -1) {
               return ActionResult.Loop;
           }
           // If we do not have any chicken, break loop.
           if(!Inventory.contains("Raw chicken")) {
               execute(dropUnwantedItems());
               return ActionResult.Stop;
           }

           // Make the fire
           Log.fine("Making fire.");
           execute(makeFire());
           Log.fine("Finished making fire.");
           // Fire exists, we can continue with cooking.

           if(Production.isOpen()) {
               Production.initiate();
           }
           else {
               Inventory.use(item -> item.getName().contains("Raw chicken"), SceneObjects.getNearest("Fire"));
               Time.sleepUntil(() -> Players.getLocal().isAnimating(), 2000);
           }

           return ActionResult.Loop;
       };
    }

    private Command makeFire() {
        return () -> {
            Log.fine("Making fire.");
            if(SceneObjects.getNearest("Fire") != null) {
                return ActionResult.Stop;
            }
            if(Players.getLocal().getAnimation() != -1) {
                return ActionResult.Loop;
            }
            execute(getLogs());
            // make fire
            execute(lightFire());
            return ActionResult.Loop;
        };
    }

    private Command lightFire() {
        return () -> {
            if(SceneObjects.getNearest("Fire") != null) {
                return ActionResult.Stop;
            }
            if(Players.getLocal().isAnimating()) {
                return ActionResult.Loop;
            }
            Inventory.use(item -> item.getName().contains("Tinderbox"), Inventory.getFirst("Logs"));
            Time.sleepUntil(() -> SceneObjects.getNearest("Fire") != null, 3500);
            return ActionResult.Loop;
        };
    }

    private Command getLogs() {
        return () -> {

            Log.fine("Getting logs.");
            if(Inventory.contains("Logs")) {
                return ActionResult.Stop;
            }

            if(Pickables.getNearest("Logs") != null) {
                Pickables.getNearest("Logs").interact("Take");
                return ActionResult.Loop;
            }

            Position treePosition = new Position(3176,3280,0);
            if(treePosition.distance() > 10){
                Movement.walkTo(treePosition);
                return ActionResult.Loop;
            }
            var tree = SceneObjects.getNearest("Tree");
            if(tree == null){
                return ActionResult.Loop;
            }
            tree.interact("Chop down");
            return ActionResult.Loop;
        };
    }

    private void execute(Command command) {
        Log.fine("Command has started.");
        ActionResult result = command.execute();
        while (result == ActionResult.Loop) {
            if(this.isStopping()) {
                break;
            }
            if(this.isPaused()) {
                Time.sleep(100);
                continue;
            }
            result = command.execute();
            Time.sleep(300, 500);
        }
        Log.fine("Command has finished.");
    }
}
