/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.hero;

import java.util.ArrayList;
import java.util.function.Predicate;

import edu.bu.phuminw.quest.util.Tuple;

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
    private ArrayList<Tuple<String, Double>> bonus;

    public HSkills(double strength, double dexterity, double agility) {
        if (strength < 0 || dexterity < 0 || agility < 0)
            throw new IllegalArgumentException("Invalid Arguments for HSKills");
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        bonus = new ArrayList<Tuple<String, Double>>();
    }

    public double getStr() {
        int factor = 1;

        // Apply temporary bonus, if any
        for (Tuple<String, Double> b: bonus) {
            if (b.getFirst() == STR)
                factor *= b.getSecond();
        }

        return strength * factor;
    }

    public double getDex() {
        int factor = 1;
        
        // Apply temporary bonus, if any
        for (Tuple<String, Double> b: bonus) {
            if (b.getFirst() == DEX)
                factor *= b.getSecond();
        }

        return dexterity * factor;
    }

    public double getAgi() {
        int factor = 1;

        // Apply temporary bonus, if any
        for (Tuple<String, Double> b: bonus) {
            if (b.getFirst() == AGI)
                factor *= b.getSecond();
        }

        return agility * factor;
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
     * Add temporary bonus to some skills
     * 
     * @param skill Skill having bonus
     * @param factor bonus factor e.g. 1.1 is 10 %
     */
    public void addBonus(String skill, double factor) {
        bonus.add(new Tuple<String,Double>(skill, factor));
    }

    /**
     * Remove all occurrences matching given skill and factor
     * 
     * @param skill skill name
     * @param factor bonus factor
     * @return {@code true} if some elements were removed
     */
    public boolean removeBonus(String skill, double factor) {
        return bonus.removeIf(new Predicate<Tuple<String, Double>>() {
            @Override
            public boolean test(Tuple<String, Double> t) {
                return t.getFirst().equals(skill) && t.getSecond() == factor;
            }
        });
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