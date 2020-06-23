package org.sydne;

import net.runelite.api.Actor;
import net.runelite.api.NPC;
import net.runelite.api.Point;
import net.runelite.client.Container;
import net.runelite.client.plugins.maddev.Mouse;
import net.runelite.client.scripting.Script;
import net.runelite.client.scripting.ScriptMeta;

import java.awt.*;


@ScriptMeta(developer = "MadDev", desc = "Tut Island", name = "Tut Island")
public class Main extends Script {

    @Override
    public int loop() {
        for (NPC npc : Container.getNpcs()) {
            if(npc == null || npc.getName() == null) {
                continue;
            }
            System.out.println(npc.getName());
            if(npc.getName().contains("Gielinor Guide")) {
                System.out.println("Target: " + Container.getSelectedMenuOptionTarget());
                System.out.println("Interacting: " + npc);
                interact(npc, "Talk-to");
            }
        }
        return 350;
    }

    private void interact(Actor actor, String action) {
        Shape shape = actor.getConvexHull();
        Point point = generatePoint(shape, action, actor.getName());
        Mouse.click(point.getX(), point.getY());
        System.out.println("HAS ACTION");
    }

    Point generatePoint(Shape region, String action, String target){
        Rectangle r = region.getBounds();
        int x = 0, y = 0;
        while (true) {
            if(this.getState() == State.STOPPED) {
                break;
            }
            x = (int) (r.getX() + r.getWidth() * Math.random());
            y = (int) (r.getY() + r.getHeight() * Math.random());
            Mouse.move(x, y);
            String selectedTarget = Container.getSelectedMenuOptionTarget();
            String selectedAction = Container.getSelectedMenuOption();
            if(selectedTarget != null && selectedAction.equals(action) && selectedTarget.contains(target)) {
                break;
            }
        }
        return new Point(x, y);
    }
}
