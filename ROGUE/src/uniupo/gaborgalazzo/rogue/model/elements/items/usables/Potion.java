package uniupo.gaborgalazzo.rogue.model.elements.items.usables;

import uniupo.gaborgalazzo.rogue.model.elements.actors.Actor;

public class Potion extends Usable {

    private int healValue;

    public Potion(int healValue) {
        this.healValue = healValue;
    }

    public int getHealValue() {
        return healValue;
    }

    @Override
    public void use(Actor actor) {
        actor.incrementStrenght(healValue);
    }
}
