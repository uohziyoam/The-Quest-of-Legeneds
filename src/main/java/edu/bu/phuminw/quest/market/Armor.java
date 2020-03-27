/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.market;

import edu.bu.phuminw.quest.hero.Hero;
import edu.bu.phuminw.quest.util.Item;

/**
 * Armor item as a hero equipment
 */

public class Armor extends Item {
    private double defense;

    public Armor(Hero owner, String name, double price, int minLevel, double defense) {
        super(owner, name, price, minLevel);
        if (defense < 0)
            throw new IllegalArgumentException("Invalid Arguments for Armor");
        this.defense = defense;
    }

    public double getDefense() {
        return defense;
    }

    @Override
    public String toString() {
        return String.format("%s (Armor) defense %.2f", getName(), defense);
    }
}