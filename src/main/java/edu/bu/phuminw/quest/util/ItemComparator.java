/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.util;

import java.util.Comparator;

/**
 * Sort Item by classname then by min level
 */

public class ItemComparator implements Comparator<Item> {
    public int compare(Item i1, Item i2) {
        if (i1.getClass().equals(i2.getClass()))
            return i1.getMinLevel() - i2.getMinLevel();
        return i1.getClass().getSimpleName().compareTo(i2.getClass().getSimpleName());
    }
}