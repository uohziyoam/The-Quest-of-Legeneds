/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.hero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import edu.bu.phuminw.quest.Quest;
import edu.bu.phuminw.quest.board.Cell;
import edu.bu.phuminw.quest.io.StdinWrapper;
import edu.bu.phuminw.quest.market.Armor;
import edu.bu.phuminw.quest.market.FireSpell;
import edu.bu.phuminw.quest.market.IceSpell;
import edu.bu.phuminw.quest.market.LightningSpell;
import edu.bu.phuminw.quest.market.Potion;
import edu.bu.phuminw.quest.market.Weapon;
import edu.bu.phuminw.quest.util.Creature;
import edu.bu.phuminw.quest.util.Damagable;
import edu.bu.phuminw.quest.util.Damage;
import edu.bu.phuminw.quest.util.Item;
import edu.bu.phuminw.quest.util.ItemComparator;
import edu.bu.phuminw.quest.util.Spell;
import edu.bu.phuminw.quest.util.Tuple;

/**
 * A template/abstract class for Hero containing all necessary
 * information about Hero
 */

public abstract class Hero extends Creature implements Damagable {
    // private String name;
    // private int level;
    // private double hp;
    private double mana;
    private HSkills skills;
    private double money;
    private double exp;
    private Tuple<String, String> favor;
    private ArrayList<Item> inventory;
    private Weapon weapon;
    private Armor armor;
    private FireSpell fireSpell;
    private IceSpell iceSpell;
    private LightningSpell lightningSpell;
    private StdinWrapper sinwrap;
    private Cell<Object> position;

    public Hero(String name, double mana, double str, double dex, double agi, double money, double exp, Tuple<String, String> favor) {
        super(name, 1, 100);
        if (name.length() == 0)
            throw new IllegalArgumentException("Illegal Arguments for Hero");

        // this.name = name;
        // level = 1;
        // hp = 100*level;
        skills = new HSkills(str, dex, agi);
        this.mana = mana;
        this.favor = favor;
        this.money = money;
        this.exp = exp;
        weapon = null;
        armor = null;
        sinwrap = new StdinWrapper("");
        inventory = new ArrayList<Item>();
        fireSpell = null;
        iceSpell = null;
        lightningSpell = null;
        position = null;
    }

    public HSkills getSkills() {
        return skills;
    }

    public double getMoney() {
        return money;
    }

    public double getExp() {
        return exp;
    }

    public double getMana() {
        return mana;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public FireSpell getFireSpell() {
        return fireSpell;
    }

    public IceSpell getIceSpell() {
        return iceSpell;
    }

    public LightningSpell getLightningSpell() {
        return lightningSpell;
    }

    public Tuple<String, String> getFavor() {
        return favor;
    }

    public Cell<Object> getPosition(){
        return position;
    }
    
    public void setPosition(Cell<Object> newPosition){
        if (newPosition == null)
            throw new IllegalArgumentException("Null position cannot be accepted");

        // Remove bonus of old cell, if any
        switch (position.getType()) {
            case Quest.BUSH: skills.removeBonus(HSkills.DEX, 1.1);break;
            case Quest.CAVE: skills.removeBonus(HSkills.AGI, 1.1);break;
            case Quest.KOULOU: skills.removeBonus(HSkills.STR, 1.1);break;
            default: ;
        }

        position = newPosition;

        // Add bonus of new cell, if any
        switch (position.getType()) {
            case Quest.BUSH: skills.addBonus(HSkills.DEX, 1.1);break;
            case Quest.CAVE: skills.addBonus(HSkills.AGI, 1.1);break;
            case Quest.KOULOU: skills.addBonus(HSkills.STR, 1.1);break;
            default: ;
        }
    }

    /**
     * Decide whether can dodge the incoming attack
     * 
     * @return Dodge result
     */

    private boolean canDodge() {
        Random rand = new Random();
        return (rand.nextDouble()<0.02*skills.getAgi()/100) ? true : false;
    }

    /**
     * Handle necessary modification after level up
     * 
     * @return Boolean indicates whether can level up or not
     */

    public boolean levelUp() {
        if (exp >= 10*getLevel()) {
            // Increase stats
            skills.setStr(skills.getStr()*1.05);
            skills.setDex(skills.getDex()*1.05);
            skills.setAgi(skills.getAgi()*1.05);

            // Increase favored stats
            switch (favor.getFirst()) {
                case HSkills.STR: skills.setStr(skills.getStr()*1.05);break;
                case HSkills.DEX: skills.setDex(skills.getDex()*1.05);break;
                case HSkills.AGI: skills.setAgi(skills.getAgi()*1.05);break;
                default: System.err.println("Unexpected favored stats");
            }

            switch (favor.getSecond()) {
                case HSkills.STR: skills.setStr(skills.getStr()*1.05);break;
                case HSkills.DEX: skills.setDex(skills.getDex()*1.05);break;
                case HSkills.AGI: skills.setAgi(skills.getAgi()*1.05);break;
                default: System.err.println("Unexpected favored stats");
            }

            mana *= 1.1;
            mana = Math.round(mana*100)/100; // Round to 2 decimal points
            setHp(100*getLevel());
            exp = 0;
            setLevel(getLevel()+1);
            return true;
        }
        return false;
    }

    /**
     * Take attack from the opponent
     * 
     * @param dmg Damage object of the opponent
     * @return Boolean indicates win or not
     */

    public boolean attack(Damage dmg) {
        boolean win = false;

        if (canDodge()) {
            System.out.printf("[!] %s dodged ...\n", getName());
            win = true;
        }
        else {
            double defense = 0;
            if (armor != null)
                defense = armor.getDefense();
            
            // Final damage to process
            double damage = (dmg.getDamage().getFirst() - defense > 0) ? dmg.getDamage().getFirst() - defense : 0;
                
            if (damage >= getHp()) {
                win = false; setHp(0);
                System.out.printf("[!] %s was attacked %.2f and died\n", getName(), damage);
            }
            else {
                win = true; setHp(getHp() - damage);
                System.out.printf("[!] %s was attacked %.2f\n", getName(), damage);
            }
        }

        return win;
    }

    /**
     * Process tasks after winning the match
     */

    public void winMatch(int monLv) {
        money += 100*monLv;
        exp += 2;
        setHp(getHp() * 1.05);
        mana += (mana == 0) ? 100 : 0.05*mana; // Increase by 5% or 100 if empty
        mana = Math.round(mana*100)/100; // Round to 2 decimal points

        levelUp(); // Check level up
    }

    /**
     * Process tasks after losing the match
     */

    public void lostMatch() {
        setHp(100*getLevel()/2);
    }

    // /**
    //  * Add an item to the inventory and deduct money if have enough money
    //  * 
    //  * @param <T> Item object
    //  * @param price Item price
    //  * @param item Item
    //  * @return Buying result
    //  * @deprecated
    //  * @see addItem()
    //  */

    // public <T extends Item> boolean buy(double price, T item) {
    //     if (money > price) {
    //         money -= price;
    //         return true;
    //     }
    //     return false;
    // }

    /**
     * Interactive method asking a player whether to change any equipment, including potion.
     * If want to, ask which item to equip and process necessary task.
     */

    public void changeEquipment() {
        printFightingInfo();

        if (inventory.size() == 0) {
            System.out.println("Nothing in the inventory...");
            return;
        }

        Collections.sort(inventory, new ItemComparator()); // Sort for nice display
        boolean done = false;
        
        sinwrap.setMessage("Wanna change some equipments (Y/N)? ");
        while (!done) {
            Character token = sinwrap.nextChar();

            if (token == null) { // Special key
                if (sinwrap.isEnd())
                    done = true;
                else if (sinwrap.isQuit()) {
                    Quest.quit(); return;
                }
                else if (sinwrap.isInfo())
                    printFightingInfo();
            }
            else {
                if (Character.toUpperCase(token) == 'Y')
                    done = true;
                else
                    return;
            }
        }

        sinwrap.setMessage("Enter the index of item to equip (e to cancel, q to quit game): ");
        done = false;
        
        while (!done) {
            printInventory();

            Integer token = sinwrap.nextInt();
            if (token == null) {
                if (sinwrap.isEnd())
                    done = true;
                else if (sinwrap.isQuit()) {
                    Quest.quit(); done = true;
                }
                else if (sinwrap.isInfo())
                    printFightingInfo();
            }
            else {
                if (1 <= token && token <= inventory.size()) {
                    Item toEquip = inventory.get(token-1);

                    if (toEquip.getMinLevel() <= getLevel()) { // Check min level requirement
                        if (toEquip instanceof Armor) {
                            armor = (Armor) toEquip;
                            System.out.printf("Equipped %s\n", armor.getName());
                        }
                        else if (toEquip instanceof Weapon) {
                            weapon = (Weapon) toEquip;
                            System.out.printf("Equipped %s\n", weapon.getName());
                        }
                        else if (toEquip instanceof Potion) {
                            Potion p = (Potion) toEquip;
                            Tuple<String, Double> pinfo = p.getInfo();
                            System.out.printf("Drinking %s\n", p.getName());

                            switch (pinfo.getFirst()) {
                                case HSkills.STR: skills.setStr(skills.getStr() + pinfo.getSecond());break;
                                case HSkills.DEX: skills.setDex(skills.getDex() + pinfo.getSecond());break;
                                case HSkills.AGI: skills.setAgi(skills.getAgi() + pinfo.getSecond());break;
                                case "HP": setHp(getHp() + pinfo.getSecond());break;
                                case "MANA": mana += pinfo.getSecond();break;
                                case "ALL": setHp(getHp() + pinfo.getSecond());mana += pinfo.getSecond();skills.setStr(skills.getStr() + pinfo.getSecond());skills.setDex(skills.getDex() + pinfo.getSecond());skills.setAgi(skills.getAgi() + pinfo.getSecond());break;
                                default: System.out.println("Potion contains invalid skill");
                            }

                            // Potion can be used once
                            p.use();
                            inventory.remove(p);
                        }
                        else if (toEquip instanceof Spell) {
                            switch (toEquip.getClass().getSimpleName()) {
                                case "FireSpell": fireSpell = (FireSpell) toEquip;System.out.printf("Equipped %s\n", fireSpell.getName());break;
                                case "IceSpell": iceSpell = (IceSpell) toEquip;System.out.printf("Equipped %s\n", iceSpell.getName());break;
                                case "LightningSpell": lightningSpell = (LightningSpell) toEquip;System.out.printf("Equipped %s\n", lightningSpell.getName());break;
                                default: System.out.println("Invalid spell type");
                            }
                        }
                        else {
                            // Not expected to have this, but ...
                            System.out.println("Invalid toEquip instance");
                        }

                        printFightingInfo();
                
                    }
                    else {
                        System.out.printf("Current level does not satisfy the minimum level requirement (%d<%d)\n", getLevel(), toEquip.getMinLevel());
                    }
                }
                else {
                    System.out.printf("%d is not a valid index\n\n", token);
                }
            }   
        }
    }

    /**
     * Create Damage object representing an attack to be sent to the opponent
     * 
     * @return Damage object
     */

    public Damage makeAttack() {
        changeEquipment(); // Give player a change to change before attacking
        sinwrap.setMessage("Which attack method (Hand/Weapon/Fire spell/Ice spell/Lightning spell)? ");
        boolean success = false;

        while (!success) {
            String token = sinwrap.next();
            if (token == null) {
                if (sinwrap.isQuit()) {
                    Quest.quit();
                    success = true;
                }
                else if (sinwrap.isEnd()) {
                    return new Damage(0); // Wanna end? no attack is made
                }
                else if (sinwrap.isInfo())
                    printFightingInfo();
            }
            else {
                token = token.toUpperCase();

                switch (token) {
                    case "HAND": return new Damage(skills.getStr()*0.05);
                    case "WEAPON": 
                        if (weapon == null) {System.out.println("[!] No weapon equipped\n");break;}
                        return weapon.makeAttack();
                    case "FIRE SPELL": 
                        if (fireSpell == null) {System.out.println("[!] No fire spell\n");break;}
                        else if (fireSpell.getManaReq() <= mana) {mana -= fireSpell.getManaReq();return fireSpell.makeAttack();}
                        else {System.out.println("[!] Not enough mana for fire spell\n");break;} 
                    case "ICE SPELL": 
                        if (iceSpell == null) {System.out.println("[!] No ice spell\n");break;}
                        else if (iceSpell.getManaReq() <= mana) {mana -= iceSpell.getManaReq();return iceSpell.makeAttack();}
                        else {System.out.println("[!] Not enough mana for ice spell\n");break;} 
                    case "LIGHTNING SPELL": 
                        if (lightningSpell == null) {System.out.println("[!] No lightning spell\n");break;}
                        else if (lightningSpell.getManaReq() <= mana) {mana -= lightningSpell.getManaReq();return lightningSpell.makeAttack();}
                        else {System.out.println("[!] Not enough mana for lightning spell\n");break;} 
                    default: System.out.println("[!] Invalid attack method\n");
                }
            }
        }

        return null;
    }

    /**
     * Add Item and deduct money. Market must check enough money.
     * 
     * @param <T> Item object
     * @param item Item
     */

    public <T extends Item> void addItem(T item) {
        money -= item.getPrice();
        inventory.add(item);
    }

    public ArrayList<? extends Item> getItems() {
        return inventory;
    }

    /**
     * Sell Item back to the market
     * 
     * @param <T> Item object
     * @param item Item
     * @return removal succeed or not
     */

    public <T extends Item> boolean removeItem(T item) {
        boolean success =  inventory.remove(item);

        if (success) {
            System.out.printf("Sold %s for %.2f\n\n", item.getName(), item.getPrice()/2);
            money += item.getPrice()/2;
        }

        return success;
    }

    /**
     * Print in nice format the information about equipments
     */

    public void printFightingInfo() {
        String format = "|%-20s|%-15s|%-7s|%-7s|%-7s|%-7s|%-85s|\n";
        String headFormat = "+%-20s+%-15s+%-7s+%-7s+%-7s+%-7s+%-85s+\n";
        String hline = String.format(headFormat, (new String(new char[20])).replace("\0", "-"), (new String(new char[15])).replace("\0", "-"), (new String(new char[7])).replace("\0", "-"), (new String(new char[7])).replace("\0", "-"), (new String(new char[7])).replace("\0", "-"), (new String(new char[7])).replace("\0", "-"), (new String(new char[85])).replace("\0", "-"));

        System.out.print(hline);
        System.out.format(format, "Name", "Type", "Level", "Exp", "HP", "Mana", "Equipments");
        System.out.print(hline);

        String[] equipInfo = new String[]{(weapon == null) ? "No weapon equipped" : weapon.toString(), (armor == null) ? "No armor equipped" : armor.toString(), (fireSpell == null) ? "No fire spell" : fireSpell.toString(), (iceSpell == null) ? "No ice spell" : iceSpell.toString(), (lightningSpell == null) ? "No lightning spell" : lightningSpell.toString()};

        System.out.format(format, getName(), getClass().getSimpleName(), getLevel(), getExp(), getHp(), getMana(), equipInfo[0]);

        for (int i = 1 ; i < equipInfo.length ; i++) {
            System.out.printf(format, (new String(new char[20])).replace("\0", " "), (new String(new char[15])).replace("\0", " "), (new String(new char[7])).replace("\0", " "), (new String(new char[7])).replace("\0", " "), (new String(new char[7])).replace("\0", " "), (new String(new char[7])).replace("\0", " "), equipInfo[i]);
        }

        System.out.print(hline);
    }

    /**
     * Print all items in the inventory
     */

    public void printInventory() {
        Collections.sort(inventory, new ItemComparator());

        String format = "|%-4s|%-20s|%-15s|%-10s|%-60s|\n";
        String headFormat = "+%-4s+%-20s+%-15s+%-10s+%-60s+\n";
        String hline = String.format(headFormat, (new String(new char[4])).replace("\0", "-"), (new String(new char[20])).replace("\0", "-"), (new String(new char[15])).replace("\0", "-"), (new String(new char[10])).replace("\0", "-"), (new String(new char[60])).replace("\0", "-"));

        System.out.printf("*** Inventory of %s ***\n", getName());
        System.out.print(hline);
        System.out.format(format, "#", "Name", "Type", "MinLevel", "Additional Information");
        System.out.print(hline);

        int i = 1;
        for (Item item: inventory) {
            String name = item.getName();
            String type = item.getClass().getSimpleName();
            String minLv = Integer.toString(item.getMinLevel());
            String addInfo = "";

            if (item instanceof Armor) {
                addInfo += "defense: " + Double.toString(((Armor) item).getDefense());
            }
            else if (item instanceof Potion) {
                Potion p = (Potion) item;
                Tuple<String, Double> pinfo = p.getInfo();
                addInfo += String.format("%s +%.2f", pinfo.getFirst(), pinfo.getSecond());
            }
            else if (item instanceof Weapon) {
                Weapon w = (Weapon) item;
                Tuple<Double, Integer> winfo = w.getInfo();
                addInfo += String.format("damage %.2f require hands %d", winfo.getFirst(), winfo.getSecond());
            }
            else if (item instanceof Spell) {
                Spell s = (Spell) item;
                addInfo += String.format("mana %.2f damage %.2f range %.2f", s.getManaReq(), s.getBaseDamage(), s.getDamageRange());
                switch (item.getClass().getSimpleName()) {
                    case "FireSpell": FireSpell fs = (FireSpell) s; addInfo += String.format(" (reduce %s %.2f)", "defense", fs.getDefense());break;
                    case "IceSpell": IceSpell is = (IceSpell) s; addInfo += String.format(" (reduce %s %.2f)", "range", is.getRange());break;
                    case "LightningSpell": LightningSpell ls = (LightningSpell) s; addInfo += String.format(" (reduce %s %.2f)", "dodge", ls.getDodge());break;
                    default: addInfo += " Undefined spell";
                }
            }
            else {
                System.out.println("Encountered invalid inventory in Hero");
            }

            System.out.format(format, i++, name, type, minLv, addInfo);
        }

        System.out.print(hline);
    }

    /**
     * Check for equality
     * 
     * @param other object to check
     * @return equality result
     */
    @Override

    public boolean equals(Object other) {
        if (!other.getClass().equals(getClass()))
            return false;

        Hero otherH = (Hero) other;
        if (getName() == null || skills == null || favor == null)
            return false;

        return getName().equals(otherH.getName()) && getLevel() == otherH.getLevel() && getHp() == otherH.getHp() && mana == otherH.getMana() && skills.equals(otherH.getSkills()) && money == otherH.getMoney() && exp == otherH.getExp() && favor.equals(otherH.getFavor());
    }
}