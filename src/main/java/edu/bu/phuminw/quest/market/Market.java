/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.market;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import edu.bu.phuminw.quest.Quest;
import edu.bu.phuminw.quest.hero.Hero;
import edu.bu.phuminw.quest.io.StdinWrapper;
import edu.bu.phuminw.quest.player.Player;
import edu.bu.phuminw.quest.util.Item;
import edu.bu.phuminw.quest.util.ItemComparator;
import edu.bu.phuminw.quest.util.Spell;
import edu.bu.phuminw.quest.util.Tuple;

/**
 * Market class that holds all items in the game and handle buying/selling logic
 */

public class Market {
    ArrayList<Item> items;
    StdinWrapper sinwrap;

    public Market() throws IOException {
        items = new ArrayList<Item>();
        sinwrap = new StdinWrapper("");

        final String DBPATH = Quest.findDb(); // db folder path
        ArrayDeque<String> PATH = new ArrayDeque<String>();
        PATH.add(DBPATH + "item/"); // *.csv is located under db/item/

        for (String p : PATH) {
            // Add all csv files under db/item/ to queue
            ArrayDeque<File> queue = new ArrayDeque<File>();
            queue.addAll(Arrays.asList(new File(p).listFiles(new FileFilter() {

                @Override
                public boolean accept(File file) {
                    return (!file.isDirectory()) && file.getName()
                            .substring(file.getName().length() - 4, file.getName().length()).equals(".csv");
                }
            })));

            for (File file: queue) {
                loadItem(file);
            }
        }
    }

    /**
     * Load given File into Market database
     * 
     * @param file File pointing to csv
     * @throws IOException
     */

    public void loadItem(File file) throws IOException {
        String type = file.getName().substring(0, file.getName().indexOf('.')).toUpperCase();
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine(); // Skip first line
        String line;

        while ((line = br.readLine()) != null) {
            String[] tokens = line.strip().replace("\n", "").split(",");
            switch (type) {
                case "ARMORY": Armor a = new Armor(null, tokens[0].replace("_", " "), Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]));if (!items.contains(a)) {items.add(a);};break;
                case "FIRESPELLS": FireSpell fs = new FireSpell(null, tokens[0].replace("_", " "), Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), 1, Double.parseDouble(tokens[4]), 50);if (!items.contains(fs)) {items.add(fs);};break;
                case "ICESPELLS": IceSpell is = new IceSpell(null, tokens[0].replace("_", " "), Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), 1, Double.parseDouble(tokens[4]), 0.1); if (!items.contains(is)) {items.add(is);};break;
                case "LIGHTNINGSPELLS": LightningSpell ls = new LightningSpell(null, tokens[0].replace("_", " "), Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), 1, Double.parseDouble(tokens[4]), 0.05);if (!items.contains(ls)) {items.add(ls);};break;
                case "POTIONS": Potion p = new Potion(null, tokens[0].replace("_", " "), Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), tokens[3], Double.parseDouble(tokens[4]));if (!items.contains(p)) {items.add(p);}break;
                case "WEAPONRY": Weapon w = new Weapon(null, tokens[0].replace("_", " "), Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4]));if (!items.contains(w)) {items.add(w);};break;
                default: System.out.printf("Encountered undefined item csv %s\n", type);
            }
        }
        
        br.close();
    }

    /**
     * Initiate shopping on a player
     * 
     * @param p Player to shop
     * @throws ClassNotFoundException
     * @throws IOException
     */

    public void shop(Player p) throws ClassNotFoundException, IOException {
        System.out.println("*** Welcome to the market ***\n");

        Hero h;
        
        while ((h = p.getHero()) != null) {
            boolean done = false;
            while (!done) {
                sinwrap.setMessage("Want to buy (b) or sell (s)? ");
                Character token = sinwrap.nextChar();

                if (token == null) {
                    if (sinwrap.isEnd())
                        return;
                    else if (sinwrap.isQuit()) {
                        Quest.quit(); return;
                    }
                    else if (sinwrap.isInfo()) {
                        h.printFightingInfo();
                        h.printInventory();
                        System.out.println();
                    }
                }
                else {
                    if (Character.toUpperCase(token) == 'B')
                    {
                        buy(h);done = true;
                    }
                    else if (Character.toUpperCase(token) == 'S') {
                        sell(h);done = true;
                    }
                }
            }
        }
    }

    /**
     * Initiate shopping on a hero
     * 
     * @param h Hero to shop
     * @throws ClassNotFoundException
     * @throws IOException
     */

    public void shop(Hero h) throws ClassNotFoundException, IOException {
        while (true) {
            System.out.printf("Shopping on %s\n", h.getName());
            sinwrap.setMessage("Want to buy (b)/sell (s)/end (e) ? ");
            Character token = sinwrap.nextChar();

            if (token == null) {
                if (sinwrap.isEnd())
                    return;
                else if (sinwrap.isQuit()) {
                    Quest.quit(); return;
                }
                else if (sinwrap.isInfo()) {
                    h.printFightingInfo();
                    h.printInventory();
                    System.out.println();
                }
                else if (sinwrap.isBackNexus()) // Same as back nexus special key, so ...
                    buy(h);
            }
            else {
                // if (Character.toUpperCase(token) == 'B')
                // {
                //     buy(h);
                // }
                if (Character.toUpperCase(token) == 'S') {
                    sell(h);
                }
            }
        }
    }

    /**
     * Shopping on one hero
     * @param hero Hero to shop
     * @throws ClassNotFoundException
     * @throws IOException
     */

    public void buy(Hero hero) throws ClassNotFoundException, IOException {
        Collections.sort(items, new ItemComparator());
        String format = "|%-4s|%-20s|%-15s|%-8s|%-60s|%-8s|%-10s|\n";
        String headFormat = "+%-4s+%-20s+%-15s+%-8s+%-60s+%-8s+%-10s\n";
        String hline = String.format(headFormat, (new String(new char[4])).replace("\0", "-"), (new String(new char[20])).replace("\0", "-"), (new String(new char[15])).replace("\0", "-"), (new String(new char[8])).replace("\0", "-"), (new String(new char[60])).replace("\0", "-"), (new String(new char[8])).replace("\0", "-"), (new String(new char[10])).replace("\0", "-"));

        boolean done = false;
        while (!done) {
            System.out.print(hline);
            System.out.format(format, "#", "Name", "Type", "MinLevel", "Additional Information", "Price", "Can Buy?");
            System.out.print(hline);

            int i = 1;

            // Show all items available for purchase
            for (Item item: items) {
                String name = item.getName();
                String type = item.getClass().getSimpleName();
                int minLv = item.getMinLevel();
                String addInfo = "";
                double price = item.getPrice();

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
                    System.out.println("Encountered invalid items in Hero");
                }

                System.out.format(format, i, name, type, minLv, addInfo, price, (hero.getLevel() >= minLv && hero.getMoney() >= price) ? "   *" : "");
                // System.out.print(hline);
                i++;
            }

            System.out.print(hline);

            sinwrap.setMessage("Enter the index of item to buy (e to cancel, q to quit game): ");
        
            System.out.printf("Available money: %.2f\n", hero.getMoney());
            Integer token = sinwrap.nextInt();
            if (token == null) {
                if (sinwrap.isEnd())
                    done = true;
                else if (sinwrap.isQuit()) {
                    Quest.quit(); done = true;
                }
                else if (sinwrap.isInfo()) {
                    hero.printFightingInfo();
                    hero.printInventory();
                    System.out.println();
                }
            }
            else {
                if (1 <= token && token <= items.size()) {
                    Item toBuy = items.get(token-1);
                    // Only allow if enough money and level
                    if (toBuy.getMinLevel() <= hero.getLevel() && toBuy.getPrice() <= hero.getMoney()) {
                        switch (toBuy.getClass().getSimpleName().toUpperCase()) {
                            case "ARMOR": Armor a = (Armor) toBuy;hero.addItem(new Armor(hero, a.getName(), a.getPrice(), a.getMinLevel(), a.getDefense()));break;
                            case "FIRESPELL": FireSpell fs = (FireSpell) toBuy;hero.addItem(new FireSpell(hero, fs.getName(), fs.getPrice(), fs.getMinLevel(), fs.getBaseDamage(), fs.getDamageRange(), fs.getManaReq(), fs.getDefense()));break;
                            case "ICESPELL": IceSpell is = (IceSpell) toBuy;hero.addItem(new IceSpell(hero, is.getName(), is.getPrice(), is.getMinLevel(), is.getBaseDamage(), is.getDamageRange(), is.getManaReq(), is.getRange()));break;
                            case "LIGHTNINGSPELL": LightningSpell ls = (LightningSpell) toBuy;hero.addItem(new LightningSpell(hero, ls.getName(), ls.getPrice(), ls.getMinLevel(), ls.getBaseDamage(), ls.getDamageRange(), ls.getManaReq(), ls.getDodge()));break;
                            case "POTION": Potion p = (Potion) toBuy;hero.addItem(new Potion(hero, p.getName(), p.getPrice(), p.getMinLevel(), p.getInfo().getFirst(), p.getInfo().getSecond()));break;
                            case "WEAPON": Weapon w = (Weapon) toBuy;hero.addItem(new Weapon(hero, w.getName(), w.getPrice(), w.getMinLevel(), w.getRawDamage(), w.getReqHands()));break;
                            default: System.out.printf("Undefined market item %s\n", toBuy.getClass().getSimpleName());
                        }
                        System.out.printf("[!] Added %s to %s inventory\n\n", toBuy.getName(), hero.getName());
                    }
                    else {
                        System.out.printf("[!] %s does not have enough money or satisfying level to buy %s\n\n", hero.getName(), toBuy.getName());
                    }
                }
                else {
                    System.out.printf("[!] %d is not a valid index\n\n", token);
                }
            }
        }
    }

    public void sell(Hero hero) {
        ArrayList<? extends Item> inventory = hero.getItems();
        
        if (inventory.size() == 0) {
            System.out.printf("%s has nothing to sell...\n\n", hero.getName());
            return;
        }

        while (true) {
            hero.printInventory();
            sinwrap.setMessage("Enter the index of item to sell (e to cancel, q to quit game): ");

            Integer token = sinwrap.nextInt();
            if (token == null) {
                if (sinwrap.isEnd())
                    return;
                else if (sinwrap.isQuit()) {
                    Quest.quit(); return;
                }
                else if (sinwrap.isInfo()) {}
            }
            else {
                if (1 <= token && token <= inventory.size()) {
                    hero.removeItem(inventory.get(token-1));
                }
            }
        }
    }
}