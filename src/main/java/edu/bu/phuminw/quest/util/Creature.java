/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.util;

import edu.bu.phuminw.quest.board.Mark;

/**
 * Template of Creature subclass object
 */

public abstract class Creature implements Positionable {
   private String name;
   private int level;
   private double hp;
   private Mark mark;

   public Creature(String name, int level, double hp) {
      this.name = name;
      this.level = level;
      this.hp = hp;
      this.mark = null;
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

   public Mark getMark() {
      return mark;
   }

   public void setHp(double hp) {
      this.hp = hp;
   }

   public void setLevel(int level) {
      this.level = level;
   }

   public void setMark(Mark mark) {
      this.mark = mark;
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