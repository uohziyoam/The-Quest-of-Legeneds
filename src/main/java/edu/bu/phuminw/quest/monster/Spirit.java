/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.monster;

public class Spirit extends Monster {
    public Spirit(String name, int level, double baseDamage, double defense, double dodge) {
        // Spirit has 5% more dodge chance
        super(name, level, baseDamage, defense, dodge*1.05);
    }

    @Override
    public String toString() {
        return String.format("%s (Spirit) Lv %d HP %.2f: damage %.2f defense %.2f dodge %.2f", getName(), getLevel(), getHp(), getDamage().getDamage().getFirst(), getDefense(), getDodge());
    }
}