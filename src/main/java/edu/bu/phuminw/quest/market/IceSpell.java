/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.market;

import edu.bu.phuminw.quest.hero.Hero;
import edu.bu.phuminw.quest.util.Damage;
import edu.bu.phuminw.quest.util.Spell;

/**
 * Ice Spell Item as a hero equipment
 */

public class IceSpell extends Spell {
    private final String RANGE = "RANGE";
    private double rangeAmt;

    public IceSpell(Hero owner, String name, double price, int minLevel, double baseDamage, double damageRange, double manaReq, double rangeAmt) {
        super(owner, name, price, minLevel, baseDamage, damageRange, manaReq);
        if (rangeAmt < 0)
            throw new IllegalArgumentException("Illegal Arguments for IceSpell");
        this.rangeAmt = rangeAmt;
    }

    public Damage makeAttack() {
        return new Damage(getFinalBaseDamage(), RANGE, rangeAmt);
    }

    public double getRange() {
        return rangeAmt;
    }

    @Override
    public String toString() {
        return String.format("%s (Ice Spell) mana %.2f base damage %.2f reduce range %.2f", getName(), getManaReq(), getBaseDamage(), rangeAmt);
    }
}