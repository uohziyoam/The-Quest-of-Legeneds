/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.util;

import edu.bu.phuminw.quest.hero.Hero;

/**
 * Item class serves as a template for Item subclass object that
 * can be sold in the market
 */

public abstract class Item {
    private Hero owner;
    private String name;
    private double price;
    private int minLevel;

    public Item(Hero owner, String name, double price, int minLevel) {
        if (name.length() == 0 || price < 0 || minLevel <= 0)
            throw new IllegalArgumentException("Invalid Arguments for Item");
        this.owner = owner;
        this.name = name;
        this.price = price;
        this.minLevel = minLevel;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public Hero getOwner() {
        return owner;
    }

    public void setOwner(Hero newOwner) {
        owner = newOwner;
    }

    @Override
    public boolean equals(Object other) {
        if (!other.getClass().equals(getClass())) 
            return false;
        Item i = (Item) other;
        return (name.equals(i.getName())) && (minLevel == i.getMinLevel()) && (price == i.getPrice());
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", name, minLevel, price);
    }
}