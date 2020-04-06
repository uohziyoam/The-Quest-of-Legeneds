/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.market;

import edu.bu.phuminw.quest.hero.Hero;
import edu.bu.phuminw.quest.util.Damage;
import edu.bu.phuminw.quest.util.Spell;

/**
 * Fire Spell Item as a hero equipment
 */

public class FireSpell extends Spell {
    private final String DEFENSE = "DEFENSE";
    private double defenseAmt;

    public FireSpell(Hero owner, String name, double price, int minLevel, double baseDamage, double damageRange, double manaReq, double defenseAmt) {
        super(owner, name, price, minLevel, baseDamage, damageRange, manaReq);
        if (defenseAmt < 0)
            throw new IllegalArgumentException("Illegal Arguments for FireSpell");
        this.defenseAmt = defenseAmt;
    }

    public Damage makeAttack() {
        return new Damage(getFinalBaseDamage(), DEFENSE, defenseAmt);
    }

    public double getDefense() {
        return defenseAmt;
    }

    @Override
    public String toString() {
        return String.format("%s (Fire Spell) mana %.2f base damage %.2f reduce defense %.2f", getName(), getManaReq(), getBaseDamage(), defenseAmt);
    }
}