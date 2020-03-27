/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.market;

import edu.bu.phuminw.quest.util.Tuple;
import edu.bu.phuminw.quest.hero.Hero;
import edu.bu.phuminw.quest.util.Item;

/**
 * A one-time-use potion item as a hero equipment
 */

public class Potion extends Item {
    private String incStat;
    private double incAmt;
    private boolean isUsed;

    public Potion(Hero owner, String name, double price, int minLevel, String incStat, double incAmt) {
        super(owner, name, price, minLevel);
        if (incStat.length() < 0 || incAmt < 0)
            throw new IllegalArgumentException("Invalid Arguments for Potion");
        this.incStat = incStat.toUpperCase();
        this.incAmt = incAmt;
        this.isUsed = false;
    }

    public Tuple<String, Double> getInfo() {
        return new Tuple<String, Double>(incStat, incAmt);
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void use() {
        isUsed = true;
    }
}