/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.util;

import edu.bu.phuminw.quest.hero.Hero;

/**
 * Damage object that is sent to the opponent representing an attack
 */

public class Damage {
    private double damage;
    private String additionalDamage;
    private double addDmgAmt;

    public Damage(double damage, String additionalDamage, double addDmgAmt) {
        if (damage < 0)
            throw new IllegalArgumentException("Invalid Arguments for Damage");
        this.damage = damage;
        this.additionalDamage = additionalDamage;
        this.addDmgAmt = addDmgAmt;
    }

    public Damage(double damage) {
        this(damage, null, 0);
    }

    public Tuple<Double, Tuple<String, Double>> getDamage() {
        if (additionalDamage == null)
            // This damage does not have additional damage i.e. caused by spell
            return new Tuple<Double, Tuple<String, Double>>(damage, null);

        return new Tuple<Double,Tuple<String,Double>>(damage, new Tuple<String, Double>(additionalDamage.toUpperCase(), addDmgAmt));
    }

    public <T extends Hero> boolean setAdditionalDamage(T hero, String additionalDamage, double addDmgAmt) {
        if (additionalDamage != null || addDmgAmt != 0) 
            return false;

        this.additionalDamage = additionalDamage;
        this.addDmgAmt = addDmgAmt;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Damage %.2f %s %.2f", damage, additionalDamage, addDmgAmt);
    }
}