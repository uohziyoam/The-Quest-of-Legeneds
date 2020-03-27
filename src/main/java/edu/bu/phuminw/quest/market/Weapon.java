/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.market;

import edu.bu.phuminw.quest.util.Tuple;
import edu.bu.phuminw.quest.util.Damagable;
import edu.bu.phuminw.quest.util.Damage;
import edu.bu.phuminw.quest.util.Item;
import edu.bu.phuminw.quest.hero.Hero;

/**
 * Weapon item as a hero equipment
 */

public class Weapon extends Item implements Damagable {
    private double damage;
    private int reqHands;

    public Weapon(Hero owner, String name, double price, int minLevel, double damage, int reqHands) {
        super(owner, name, price, minLevel);
        if (damage < 0 || reqHands < 1 || reqHands > 2)
            throw new IllegalArgumentException("Invalid Arguments for Weapon");
        this.damage = damage;
        this.reqHands = reqHands;
    }

    public double getRawDamage() {
        return damage;
    }

    public Damage getDamage() {
        return new Damage((getOwner().getSkills().getStr()+damage)*0.05);
    }

    public int getReqHands() {
        return reqHands;
    }
    
    public Tuple<Double, Integer> getInfo() {
        return new Tuple<Double,Integer>(damage, reqHands);
    }

    @Override
    public String toString() {
        return String.format("%s (Weapon) damage %.2f", getName(), damage);
    }
}