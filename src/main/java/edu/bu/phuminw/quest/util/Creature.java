/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.util;

/**
 * Guideline that Creature object needs to have
 */

public abstract class Creature {
   private String name;
   private int level;
   private double hp;

   public Creature(String name, int level, double hp) {
      this.name = name;
      this.level = level;
      this.hp = hp;
   }

   public String getName() {
      return name;
   }

   public int getLevel() {
      return level;
   }

   public double getHp() {
      return hp;
   }

   public void setHp(double hp) {
      this.hp = hp;
   }

   public void setLevel(int level) {
      this.level = level;
   }

   /** 
    * Take attack from the opponent
    * @param dmg Damage object of the opponent
    * @return boolean indicates win or not
    */
   public abstract boolean attack(Damage dmg);

   /**
    * Check for equality
    * 
    * @param other object to check
    * @return equality result
    */
   @Override

   public abstract boolean equals(Object other);
}