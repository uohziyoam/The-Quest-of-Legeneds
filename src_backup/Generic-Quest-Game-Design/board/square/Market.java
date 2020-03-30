package board.square;

import java.util.*;

import avatar.Hero;
import config.Color;
import equipment.*;

public class Market extends Square {

    public boolean accessible;

    public Market() {
        this.accessible = false;
    }

    public Object sellEquipment(Object item, Hero hero) {
        if (item instanceof Weapons) {
            Weapons weapons = (Weapons) item;
            if (hero.getLevel() < weapons.getMinimumLevel()) {
                throw new NullPointerException("HERO'S LEVEL IS NOT REACHED!");
            }

            if (hero.getMoney() < weapons.getCost()) {
                throw new NullPointerException("HERO DOES NOT HAVE ENOUGH MONEY!");
            }
            hero.reduceMoney(weapons.getCost());
            return item;
        }

        if (item instanceof Armors) {
            Armors armors = (Armors) item;
            if (hero.getLevel() < armors.getMinimumLevel()) {
                throw new NullPointerException("HERO'S LEVEL IS NOT REACHED!");
            }

            if (hero.getMoney() < armors.getCost()) {
                throw new NullPointerException("HERO DOES NOT HAVE ENOUGH MONEY!");
            }
            hero.reduceMoney(armors.getCost());
            return item;
        }

        if (item instanceof Potions) {
            Potions potions = (Potions) item;
            if (hero.getLevel() < potions.getMinimumLevel()) {
                throw new NullPointerException("HERO'S LEVEL IS NOT REACHED!");
            }

            if (hero.getMoney() < potions.getCost()) {
                throw new NullPointerException("HERO DOES NOT HAVE ENOUGH MONEY!");
            }
            hero.reduceMoney(potions.getCost());
            return item;
        }

        if (item instanceof Ice_Spells) {
            Ice_Spells ice_Spells = (Ice_Spells) item;
            if (hero.getLevel() < ice_Spells.getMinimumLevel()) {
                throw new NullPointerException("HERO'S LEVEL IS NOT REACHED!");
            }

            if (hero.getMoney() < ice_Spells.getCost()) {
                throw new NullPointerException("HERO DOES NOT HAVE ENOUGH MONEY!");
            }
            hero.reduceMoney(ice_Spells.getCost());
            return item;
        }

        if (item instanceof Fire_Spells) {
            Fire_Spells fire_Spells = (Fire_Spells) item;
            if (hero.getLevel() < fire_Spells.getMinimumLevel()) {
                throw new NullPointerException("HERO'S LEVEL IS NOT REACHED!");
            }

            if (hero.getMoney() < fire_Spells.getCost()) {
                throw new NullPointerException("HERO DOES NOT HAVE ENOUGH MONEY!");
            }
            hero.reduceMoney(fire_Spells.getCost());
            return item;
        }

        if (item instanceof Lighting_Spells) {
            Lighting_Spells lighting_Spells = (Lighting_Spells) item;
            if (hero.getLevel() < lighting_Spells.getMinimumLevel()) {
                throw new NullPointerException("HERO'S LEVEL IS NOT REACHED!");
            }

            if (hero.getMoney() < lighting_Spells.getCost()) {
                throw new NullPointerException("HERO DOES NOT HAVE ENOUGH MONEY!");
            }
            hero.reduceMoney(lighting_Spells.getCost());
            return item;
        }


        return null;
    }

    public double buyEquipment(Object item) {
        if (item instanceof Weapons) {
            return ((Weapons) item).getCost();
        }

        if (item instanceof Armors) {
            return ((Armors) item).getCost();
        }

        if (item instanceof Potions) {
            return ((Potions) item).getCost();
        }

        if (item instanceof Ice_Spells) {
            return ((Ice_Spells) item).getCost();
        }

        if (item instanceof Fire_Spells) {
            return ((Fire_Spells) item).getCost();
        }

        if (item instanceof Lighting_Spells) {
            return ((Lighting_Spells) item).getCost();
        }

        return -1;
    }

    private void replenishMarket() {

    }

    @Override
    public String toString() {
        return Color.ANSI_RED + "M" + Color.ANSI_RESET;
    }

    public boolean getAccessible() {
        return accessible;
    }

}