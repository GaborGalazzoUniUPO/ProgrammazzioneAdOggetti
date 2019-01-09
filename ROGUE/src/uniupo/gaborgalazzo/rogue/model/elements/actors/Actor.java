package uniupo.gaborgalazzo.rogue.model.elements.actors;

import uniupo.gaborgalazzo.rogue.model.Prison;
import uniupo.gaborgalazzo.rogue.model.elements.Element;
import uniupo.gaborgalazzo.rogue.model.elements.items.usables.Weapon;

public abstract class Actor extends Element {

    private int maxStrength;
    private int actualStrength;
    private Weapon weapon;

    public Actor(int room, int x, int y, int maxStrength, int actualStrength, Weapon weapon)
    {
        super(room, x, y);
        setMaxStrength(maxStrength);
        setActualStrength(actualStrength);
        setWeapon(weapon);
    }

    public int decrementStrenght(int amount){
        actualStrength -= amount;
        if(actualStrength<0)
            actualStrength = 0;
        return actualStrength;
    }


    public void incrementStrenght(int amount){
        actualStrength += amount;
        if(actualStrength>maxStrength)
            actualStrength = maxStrength;
    }

    public boolean isDead(){
        return actualStrength < 1;
    }

    public int getActualStrength(){
        return actualStrength;
    }

    public int getMaxStrength(){
        return maxStrength;
    }

    public void setMaxStrength(int maxStrength)
    {
        this.maxStrength = maxStrength;
    }

    public void setActualStrength(int actualStrength)
    {
        this.actualStrength = actualStrength;
    }

    public Weapon getWeapon()
    {
        return weapon;
    }

    public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }
}
