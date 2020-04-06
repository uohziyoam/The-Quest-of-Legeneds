/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.market;

import edu.bu.phuminw.quest.hero.Hero;
import edu.bu.phuminw.quest.util.Damage;
import edu.bu.phuminw.quest.util.Spell;

/**
 * Lightning Spell item as a hero equipment
 */

public class LightningSpell extends Spell {
    private final String RANGE = "DODGE";
    private double dodgeAmt;

    public LightningSpell(Hero owner, String name, double price, int minLevel, double baseDamage, double damageRange,
            double manaReq, double dodgeAmt) {
        super(owner, name, price, minLevel, baseDamage, damageRange, manaReq);
        if (dodgeAmt < 0)
            throw new IllegalArgumentException("Illegal Arguments for LightningSpell");
        this.dodgeAmt = dodgeAmt;
    }

    public Damage makeAttack() {
        return new Damage(getFinalBaseDamage(), RANGE, dodgeAmt);
    }

    public double getDodge() {
        return dodgeAmt;
    }

    @Override
    public String toString() {
        return String.format("%s (Lightning Spell) mana %.2f base damage %.2f reduce dodge %.2f", getName(), getManaReq(), getBaseDamage(), dodgeAmt);
    }
}