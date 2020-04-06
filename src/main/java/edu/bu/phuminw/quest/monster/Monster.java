/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.monster;

import java.util.Random;

import edu.bu.phuminw.quest.board.Mark;
import edu.bu.phuminw.quest.util.Creature;
import edu.bu.phuminw.quest.util.Damagable;
import edu.bu.phuminw.quest.util.Damage;
import edu.bu.phuminw.quest.util.Tuple;

/**
 * Monster abstract class serves as a template for Monster subclass object
 */

public abstract class Monster extends Creature implements Damagable {
    // private String name;
    private Mark mark;
    // private double hp;
    // private int level;
    private double baseDamage;
    private double defense;
    private double dodge;

<<<<<<< HEAD
    //NEW
    private Coordinate curPosition;
    //NEW



    public Monster(String name, int level, double baseDamage, double defense, double dodge, Coordinate curPosition) {
        if (name.length() == 0 || hp < 0 || level <= 0 || baseDamage < 0 || defense < 0 || dodge < 0 || dodge > 1)
=======
    public Monster(String name, int level, double baseDamage, double defense, double dodge) {
        super(name, level, 100*level);
        if (name.length() == 0 || level <= 0 || baseDamage < 0 || defense < 0 || dodge < 0 || dodge > 1)
>>>>>>> f52424296501809abbe338445ae275eee91785b2
            throw new IllegalArgumentException("Illegal Arguments for Monster");
        // this.name = name;
        // hp = 100*level;
        // this.level = level;
        this.baseDamage = baseDamage;
        this.defense = defense;
        this.dodge = dodge;

        //NEW
        this.curPosition = curPosition;
        //NEW
    }

    public Mark getMark() {
        return mark;
    }

    /**
     * Make damage object without additional damage
     * 
     * @return Damage object representing damage
     */

    public Damage makeAttack() {
        return new Damage(baseDamage);
    }

    public double getBaseDamage() {
        return baseDamage;
    }
    
    public double getDefense() {
        return defense;
    }

    public double getDodge() {
        return dodge;
    }
    

    //NEW

    public Coordinate getCurrentPosition(){
        return curPosition;
    }

    public void moveAhead(){
        //if no hero around, then move ahead;
        Coordinate newPosition = new Coordinate(curPosition.getX()+1,curPosition.getY());
        setCurrentPosition(newPosition);
    }

    public void setCurrentPosition(Coordinate newPosition){
        this.curPosition = newPosition;
    }
    //NEW


    /**
     * Decide whether can dodge the incoming attack
     * 
     * @return Dodge result
     */

    private boolean canDodge() {
        Random rand = new Random();
        return (rand.nextDouble()<dodge) ? true : false;
    }

    /** 
     * Take attack from the opponent
     * @param dmg Damage object of the opponent
     * @return boolean indicates win or not
     */

    public boolean attack(Damage dmg) {
        if (!canDodge()) {
            Tuple<Double, Tuple<String, Double>> damage = dmg.getDamage();

            double finalDamage = (damage.getFirst() - defense > 0) ? damage.getFirst() - defense : 0;
            setHp((finalDamage >= getHp()) ? 0 : getHp() - finalDamage);
            System.out.printf("[!] %s was attacked %.2f%s\n", getName(), finalDamage, (getHp() == 0) ? " and died" : "");

            
            // Check additional damage i.e. from spells
            if (damage.getSecond() != null) {
                // 10% skill reduction
                baseDamage *= 0.9;defense *= 0.9;dodge *= 0.9;
                // Round to 2 decimal point
                baseDamage = Math.round(100*baseDamage)/100;defense = Math.round(100*defense)/100;dodge = Math.round(100*dodge)/100;

                switch(damage.getSecond().getFirst()) {
                    case "DEFENSE": defense = (damage.getSecond().getSecond() >= defense) ? 0 : defense - damage.getSecond().getSecond();break;
                    case "RANGE": baseDamage *= 1-damage.getSecond().getSecond();defense *= 1-damage.getSecond().getSecond();dodge *= 1-damage.getSecond().getSecond();break; // Reduce all skills
                    case "DODGE": dodge = (damage.getSecond().getSecond() >= dodge) ? 0 : dodge - damage.getSecond().getSecond();break;
                    default: System.out.println("Undefined additional damage");
                }
            }
        }
        else {
            System.out.printf("[!] %s dodged...\n", getName()); return true;
        }

        if (getHp() == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public String toString() {
        return String.format("%s Lv %d damage %.2f defense %.2f dodge %.2f", getName(), getLevel(), baseDamage, defense, dodge);
    }

    @Override
    public boolean equals(Object other) {
        if (!other.getClass().equals(getClass()))
            return false;
        Monster m = (Monster) other;
        return (getName() == m.getName()) && (getHp() == m.getHp()) && (getLevel() == m.getLevel()) && (this.defense == m.getDefense()) && (this.dodge == m.getDodge());
    }
}