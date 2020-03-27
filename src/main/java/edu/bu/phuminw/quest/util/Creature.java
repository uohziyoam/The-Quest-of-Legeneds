/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.util;

/**
 * Guideline that Creature object needs to have
 */

public interface Creature {
   public String getName();

   public boolean attack(Damage dmg);
}