/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.util;

import java.util.Comparator;

import edu.bu.phuminw.quest.hero.Hero;

/**
 * Sort Hero by mark according to natural ordering
 */

public class HeroSorter implements Comparator<Hero> {
    @Override
    public int compare(Hero h1, Hero h2) {
        return h1.getMark().toString().compareTo(h2.getMark().toString());
    }
}