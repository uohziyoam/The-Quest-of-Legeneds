/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.monster;

public class Dragon extends Monster {
    public Dragon(String name, int level, double baseDamage, double defense, double dodge) {
        // Dragon has 5% more damage
        super(name, level, baseDamage*1.05, defense, dodge);
    }

    @Override
    public String toString() {
        return String.format("%s (Dragon) Lv %d HP %.2f: damage %.2f defense %.2f dodge %.2f", getName(), getLevel(), getHp(), getDamage().getDamage().getFirst(), getDefense(), getDodge());
    }
}