/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.util;

import edu.bu.phuminw.quest.hero.Hero;

/**
 * Spell class serving as a template for a subclass spell
 */
public abstract class Spell extends Item implements Damagable {
    private double baseDamage;
    private double damageRange;
    private double manaReq;

    public Spell(Hero owner, String name, double price, int minLevel, double baseDamage, double damageRange, double manaReq) {
        super(owner, name, price, minLevel);
        if (baseDamage < 0 || damageRange < 0 || manaReq < 0) {
            throw new IllegalArgumentException("Invalid Arguments for Spell");
        }
        this.baseDamage = baseDamage;
        this.damageRange = damageRange;
        this.manaReq = manaReq;
    }

    public double getBaseDamage() {
        return baseDamage;
    }

    public double getFinalBaseDamage() {
        return baseDamage + baseDamage*(getOwner().getSkills().getDex()/10000);
    }

    public double getDamageRange() {
        return damageRange;
    }

    public double getManaReq() {
        return manaReq;
    }
}