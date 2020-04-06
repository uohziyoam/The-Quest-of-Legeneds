/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.monster;

public class Exoskeleton extends Monster {
    public Exoskeleton(String name, int level, double baseDamage, double defense, double dodge) {
        // Exoskeleton has 5% more defense
        super(name, level, baseDamage, defense*1.05, dodge);
    }

    @Override
    public String toString() {
        return String.format("%s (Exoskeleton) Lv %d HP %.2f: damage %.2f defense %.2f dodge %.2f", getName(), getLevel(), getHp(), makeAttack().getDamage().getFirst(), getDefense(), getDodge());
    }
}