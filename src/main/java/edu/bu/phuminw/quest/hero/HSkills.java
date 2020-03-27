/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.hero;

/**
 * A class to serve as a hero skills set
 */

public class HSkills {
    private double strength;
    private double dexterity;
    private double agility;
    public static final String STR = "STR";
    public static final String DEX = "DEX";
    public static final String AGI = "AGI";

    public HSkills(double strength, double dexterity, double agility) {
        if (strength < 0 || dexterity < 0 || agility < 0)
            throw new IllegalArgumentException("Invalid Arguments for HSKills");
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
    }

    public double getStr() {
        return strength;
    }

    public double getDex() {
        return dexterity;
    }

    public double getAgi() {
        return agility;
    }

    public boolean setStr(double newStr) {
        if (newStr < 0)
            return false;

        strength = newStr;
        return true;
    }

    public boolean setDex(double newDex) {
        if (newDex < 0)
            return false;

        dexterity = newDex;
        return true;
    }

    public boolean setAgi(double newAgi) {
        if (newAgi < 0)
            return false;

        agility = newAgi;
        return true;
    }

    /**
     * Check for equality
     * 
     * @param other object to check
     * @return equality result
     */
    @Override
    
    public boolean equals(Object other) {
        if (getClass().equals(other.getClass()))
            return false;
        
        HSkills otherS = (HSkills) other;

        return strength == otherS.getStr() && dexterity == otherS.getDex() && agility == otherS.getAgi();
    }
}