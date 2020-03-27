/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.hero;

import edu.bu.phuminw.quest.util.Tuple;

/**
 * Warrior hero
 */

public class Warrior extends Hero {
    public Warrior(String name, double mana, double str, double dex, double agi, double money, double exp) {
        super(name, mana, str, dex, agi, money, exp, new Tuple<String,String>(HSkills.STR, HSkills.AGI));
    }

    @Override
    public String toString() {
        HSkills skills = getSkills();
        return String.format("Warrior %s (Lv %d Exp %.2f STR %.2f DEX %.2f AGI %.2f) HP: %.2f Mana: %.2f", getName(), getLevel(), getExp(), skills.getStr(), skills.getDex(), skills.getAgi(), getHp(), getMana());
    }
}