package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import avatar.*;
import avatar.avatarName.Dragons;
import avatar.avatarName.Exoskeletons;
import avatar.avatarName.Spirits;
import board.square.Market;
import config.Color;
import config.DamageType;
import equipment.Armors;
import equipment.Fire_Spells;
import equipment.Ice_Spells;
import equipment.Lighting_Spells;
import equipment.Potions;
import equipment.Weapons;

/**
 * FightRound
 */
public class FightRound {
    static Scanner in;

    private AvatarType currentTurn;

    private ArrayList<Monster> teamOfMonsters;

    private ArrayList<Hero> teamOfHeros;

    private ArrayList<Hero> deadHeros;

    private boolean quit;

    static HashMap<Integer, String> menu;

    static {
        menu = new HashMap<>();
        menu.put(0, "USEPOTION");
        menu.put(1, "REGULARATTACK");
        menu.put(2, "CHANGEARMOR");
        menu.put(3, "CHANGEWEAPON");
        menu.put(4, "CASTSPELL");
        menu.put(5, "DISPLAYSTATS");
        menu.put(6, "QUIT");
    }

    FightRound(ArrayList<Hero> teamOfHeros) {
        this.teamOfHeros = teamOfHeros;
        this.teamOfMonsters = new ArrayList<>();
        this.deadHeros = new ArrayList<>();
        quit = false;
        initFight();

        while (!isEnd()) {
            int i;
            for (i = 0; i < teamOfHeros.size(); i++) {
                startFight(i);
            }

            for (Hero hero : teamOfHeros) {
                hero.recover();
            }

            i = 0;
        }

        if (areMonstersAllDead(teamOfMonsters)) {
            for (Hero hero : teamOfHeros) {
                hero.addExperience(2);
                hero.winMoney(150);
            }
            for (Hero hero : deadHeros) {
                hero.winMoney(150);
            }
        }

        if (areHerosAllDead(teamOfHeros)) {
            for (Hero hero : deadHeros) {
                hero.loseMoney();
            }
        }

        for (Hero hero : deadHeros) {
            hero.revive();
        }

    }

    private void startFight(int currentHero) {
        System.out.println(Color.ANSI_CYAN);
        System.out.println("Hero: " + teamOfHeros.get(currentHero).getName() + " (ENTER FULL STRING e.g. USEPOTION)");
        System.out.println("0. " + menu.get(0));
        System.out.println("1. " + menu.get(1));
        System.out.println("2. " + menu.get(2));
        System.out.println("3. " + menu.get(3));
        System.out.println("4. " + menu.get(4));
        System.out.println("5. " + menu.get(5));
        System.out.println("6. " + menu.get(6));
        System.out.println(Color.ANSI_RESET);

        String input = "";
        int action = 0;

        while (!menu.containsValue(input)) {
            in = new Scanner(System.in);
            input = in.next().toUpperCase();
            for (int i : menu.keySet()) {
                if (menu.get(i).equals(input)) {
                    action = i;
                    actionProcessing(i, currentHero);
                    break;
                }
            }
        }

        currentHero = currentHero >= teamOfMonsters.size() ? teamOfMonsters.size() - 1 : currentHero;

        monsterAttack(currentHero);

        if (action != 5) {
            teamOfHeros.get(currentHero).printStatistics();
            teamOfMonsters.get(currentHero).printStatistics();
        }

        if (teamOfHeros.get(currentHero).isDead()) {
            deadHeros.add(teamOfHeros.get(currentHero));
            teamOfHeros.remove(currentHero);
        }

        if (teamOfMonsters.get(currentHero).isDead()) {
            teamOfMonsters.remove(currentHero);
        }
    }

    private void monsterAttack(int currentHero) {

        Hero hero = teamOfHeros.get(currentHero);
        Monster monster = teamOfMonsters.get(currentHero);

        hero.receiveDamage(DamageType.PHYSICAL_ATTACK, monster.getDamage());
    }

    private void actionProcessing(int action, int currentHero) {
        currentHero = currentHero >= teamOfMonsters.size() ? teamOfMonsters.size() - 1 : currentHero;
        Hero hero = teamOfHeros.get(currentHero);
        Monster monster = teamOfMonsters.get(currentHero);

        switch (action) {
            case 0:
                usePotion(hero);
                break;
            case 1:
                hero.regularAttack(monster);
                break;
            case 2:
                changeArmorInstruction(hero);
                break;
            case 3:
                changeWeaponInstruction(hero);
                break;
            case 4:
                castSpellInstruction(hero, monster);
                break;
            case 5:
                for (Hero hero2 : teamOfHeros) {
                    hero2.printStatistics();
                }
                break;
            case 6:
                quit = true;
                break;
            default:
                break;
        }
    }

    public void usePotion(Hero hero) {
        ArrayList<String> potions = new ArrayList<>();

        for (Object equipment : hero.getBag().keySet()) {
            if (equipment instanceof Potions) {
                potions.add(((Potions) equipment).toString().substring(0, 1).toUpperCase()
                        + ((Potions) equipment).toString().substring(1).toLowerCase());
            }
        }
        potions.add("Quit");

        String input = "";
        while (!potions.contains(input)) {
            System.out.println(Color.ANSI_CYAN);
            System.out.println("Potions: ");

            int i;

            for (i = 0; i < potions.size(); i++) {
                System.out.println(i + ". " + potions.get(i).toString());
            }

            System.out.println(Color.ANSI_RESET);

            in = new Scanner(System.in);
            input = in.next();
            if (input.length() < 2) {
                continue;
            }
            input = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        }

        if (input.equals("Quit")) {
            return;
        }

        hero.usePotion(input);
    }

    public void castSpellInstruction(Hero hero, Monster monster) {
        ArrayList<String> spells = new ArrayList<>();

        for (Object equipment : hero.getBag().keySet()) {
            if (equipment instanceof Lighting_Spells) {
                spells.add(((Lighting_Spells) equipment).toString().substring(0, 1).toUpperCase()
                        + ((Lighting_Spells) equipment).toString().substring(1).toLowerCase());
            }

            if (equipment instanceof Fire_Spells) {
                spells.add(((Fire_Spells) equipment).toString().substring(0, 1).toUpperCase()
                        + ((Fire_Spells) equipment).toString().substring(1).toLowerCase());
            }

            if (equipment instanceof Ice_Spells) {
                spells.add(((Ice_Spells) equipment).toString().substring(0, 1).toUpperCase()
                        + ((Ice_Spells) equipment).toString().substring(1).toLowerCase());
            }
        }
        spells.add("Quit");

        String input = "";
        while (!spells.contains(input)) {
            System.out.println(Color.ANSI_CYAN);
            System.out.println("SPELLS: ");

            int i;

            for (i = 0; i < spells.size(); i++) {
                System.out.println(i + ". " + spells.get(i).toString());
            }

            System.out.println(Color.ANSI_RESET);

            in = new Scanner(System.in);
            input = in.next();
            if (input.length() < 2) {
                continue;
            }
            input = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        }

        if (input.equals("Quit")) {
            return;
        }

        try {
            hero.castSpell(input, monster);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void changeArmorInstruction(Hero hero) {
        ArrayList<String> armors = new ArrayList<>();

        for (Object equipment : hero.getBag().keySet()) {
            if (equipment instanceof Armors) {
                armors.add(((Armors) equipment).toString().substring(0, 1).toUpperCase()
                        + ((Armors) equipment).toString().substring(1).toLowerCase());
            }
        }
        armors.add("Quit");

        String input = "";
        while (!armors.contains(input)) {
            System.out.println(Color.ANSI_CYAN);
            System.out.println("ARMORS: ");

            int i;

            for (i = 0; i < armors.size(); i++) {
                System.out.println(i + ". " + armors.get(i).toString());
            }

            System.out.println(Color.ANSI_RESET);

            in = new Scanner(System.in);
            input = in.next();
            if (input.length() < 2) {
                continue;
            }
            input = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        }

        if (input.equals("Quit")) {
            return;
        }

        hero.changeArmor(input);
    }

    public void changeWeaponInstruction(Hero hero) {
        ArrayList<String> weapons = new ArrayList<>();

        for (Object equipment : hero.getBag().keySet()) {
            if (equipment instanceof Weapons) {
                weapons.add(((Weapons) equipment).toString().substring(0, 1).toUpperCase()
                        + ((Weapons) equipment).toString().substring(1).toLowerCase());
            }
        }
        weapons.add("Quit");

        String input = "";
        while (!weapons.contains(input)) {
            System.out.println(Color.ANSI_CYAN);
            System.out.println("WEAPONS: ");

            int i;

            for (i = 0; i < weapons.size(); i++) {
                System.out.println(i + ". " + weapons.get(i).toString());
            }

            System.out.println(Color.ANSI_RESET);

            in = new Scanner(System.in);
            input = in.next();
            if (input.length() < 2) {
                continue;
            }
            input = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        }

        if (input.equals("Quit")) {
            return;
        }

        hero.changeWeapon(input);
    }

    private void initFight() {
        initMonsters();
        currentTurn = AvatarType.Hero;
    }

    public void initMonsters() {
        Dragons[] dragons = Dragons.values();
        Spirits[] spirits = Spirits.values();
        Exoskeletons[] exoskeletons = Exoskeletons.values();

        ArrayList<String> arrOfMonsters = new ArrayList<>();

        for (Dragons dragon : dragons) {
            if (dragon.getLevel() == teamOfHeros.get(0).getLevel()) {
                arrOfMonsters.add(dragon.toString());
            }
        }

        for (Spirits spirit : spirits) {
            if (spirit.getLevel() == teamOfHeros.get(0).getLevel()) {
                arrOfMonsters.add(spirit.toString());
            }
        }

        for (Exoskeletons exoskeleton : exoskeletons) {
            if (exoskeleton.getLevel() == teamOfHeros.get(0).getLevel()) {
                arrOfMonsters.add(exoskeleton.toString());
            }
        }

        for (int i = 0; i < teamOfHeros.size(); i++) {
            teamOfMonsters.add(new Monster(arrOfMonsters.get(i)));
        }

        for (Monster m : teamOfMonsters) {
            m.printStatistics();
        }
    }

    public int numberOfAliveAvatar(AvatarType team) {
        if (team == AvatarType.Hero) {
            return teamOfHeros.size();
        }

        if (team == AvatarType.Monster) {
            return teamOfMonsters.size();
        }

        return -1;
    }

    private void changeTurn() {
        if (currentTurn == AvatarType.Hero) {
            currentTurn = AvatarType.Monster;
        }

        if (currentTurn == AvatarType.Monster) {
            currentTurn = AvatarType.Hero;
        }
    }

    private boolean areHerosAllDead(ArrayList<Hero> team) {
        int deadAvatar = 0;
        for (Avatar avatar : team) {
            if (avatar.isDead()) {
                deadAvatar++;
            }
        }
        return deadAvatar == team.size();
    }

    private boolean areMonstersAllDead(ArrayList<Monster> team) {
        int deadAvatar = 0;
        for (Avatar avatar : team) {
            if (avatar.isDead()) {
                deadAvatar++;
            }
        }
        return deadAvatar == team.size();
    }

    private boolean isEnd() {
        if (areHerosAllDead(teamOfHeros)) {
            System.out.println("MONSTERS WIN!");
        }

        if (areMonstersAllDead(teamOfMonsters)) {
            System.out.println("HEROS WIN!");
        }

        return areHerosAllDead(teamOfHeros) || areMonstersAllDead(teamOfMonsters) || quit;
    }
}