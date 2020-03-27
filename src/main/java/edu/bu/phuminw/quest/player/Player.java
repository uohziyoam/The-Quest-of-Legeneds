/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.player;

import java.util.ArrayList;
import java.util.Random;

import edu.bu.phuminw.quest.Quest;
import edu.bu.phuminw.quest.board.Mark;
import edu.bu.phuminw.quest.hero.Hero;
import edu.bu.phuminw.quest.io.StdinWrapper;
import edu.bu.phuminw.quest.util.Team;

/**
 * A class representing a game player
 */

public class Player {
    private int id;
    private String name;
    private Mark mark;
    private Team<Player> team; // Player team
    private int win;
    private int tie;
    private int loss;
    private int roundPlayed;
    private Team<Hero> heroes; // Heroes
    private StdinWrapper sinwrap;

    public Player(int id, String name, Team<Player> team, int maxHero) {
        this.id = id;
        this.name = name;
        this.team = team;
        mark = new Mark("");
        win = tie = loss = roundPlayed = 0;
        this.heroes = new Team<Hero>(new Random().nextInt(), maxHero);
        sinwrap = new StdinWrapper("");
    }
    
    public Player(int id, String name, int maxHero) {
        this(id, name, null, maxHero);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Mark getMark() {
        return mark;
    }

    public int getWin() {
        return win;
    }

    public int getTie() {
        return tie;
    }

    public int getLoss() {
        return loss;
    }

    public int getRound() {
        return roundPlayed;
    }

    public Team<Player> getTeam() {
        return team;
    }

    public int getNumHero() {
        return heroes.getSize();
    }

    public Team<Hero> getHeroTeam() {
        return heroes;
    }

    /**
     * Ask for a hero from the player
     * 
     * @return Selected hero
     */

    public Hero getHero() { 
        ArrayList<Hero> heroMembers = heroes.getMember();
        sinwrap.setMessage("Which hero (select by #, e to end, q to quit)? ");

        while (true) {
            printBasicInfo();

            Integer token = sinwrap.nextInt();
            if (token == null) {
                if (sinwrap.isEnd())
                    return null;
                else if (sinwrap.isQuit()) {
                    Quest.quit(); return null;
                }
                else if (sinwrap.isInfo()) {}
            }
            else {
                if (1 <= token && token <= heroMembers.size()) {
                    return heroMembers.get(token-1);
                }
            }
        }
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public void clearMark() {
        mark = null;
    }

    public void addTeam(Team<Player> t) {
        team = t;
    }

    public void clearTeam() {
        team = null;
    }

    public void addHero(Hero h) {
        heroes.add(h);
    }
    
    public void addWin() {
        win++;
        addRound();
    }

    public void addTie() {
        tie++;
        addRound();
    }

    public void addLoss() {
        loss++;
        addRound();
    }

    public void addRound() {
        roundPlayed++;
    }

    @Override
    public boolean equals(Object other) {
        if (!other.getClass().equals(getClass()))
            return false;
        
        Player otherP = (Player) other;
        return (this.id == otherP.getId()) && (this.name.equals(otherP.getName())) && ((this.team == null && this.team == otherP.getTeam()) || (this.team.equals(otherP.getTeam())));
    }

    /**
     * Nicely print information about heroes
     */

    public void printBasicInfo() {
        String format = "|%-4s|%-20s|%-15s|%-7s|%-7s|%-8s|%-7s|%-7s|%-25s|\n";
        String headFormat = "+%-4s+%-20s+%-15s+%-7s+%-7s+%-8s+%-7s+%-7s+%-25s+\n";
        String hline = String.format(headFormat, (new String(new char[4])).replace("\0", "-"), (new String(new char[20])).replace("\0", "-"), (new String(new char[15])).replace("\0", "-"), (new String(new char[7])).replace("\0", "-"), (new String(new char[7])).replace("\0", "-"), (new String(new char[8])).replace("\0", "-"), (new String(new char[7])).replace("\0", "-"), (new String(new char[7])).replace("\0", "-"), (new String(new char[25])).replace("\0", "-"));

        System.out.print(hline);
        System.out.format(format, "#", "Name", "Type", "Level", "Exp", "Money", "HP", "Mana", "S/D/A");
        System.out.print(hline);
        
        int i = 1;
        for (Hero h: heroes.getMember()) {
            System.out.format(format, i, h.getName(), h.getClass().getSimpleName(), h.getLevel(), h.getExp(), h.getMoney(), h.getHp(), h.getMana(), String.format("%.2f/%.2f/%.2f", h.getSkills().getStr(), h.getSkills().getDex(), h.getSkills().getAgi()));
            System.out.print(hline);
            i++;
        }
    }

    /**
     * Nicely print information about heroes stats and equipments
     */
    
    public void printFightingInfo() {
        String format = "|%-4s|%-20s|%-15s|%-7s|%-7s|%-7s|%-7s|%-80s|\n";
        String headFormat = "+%-4s+%-20s+%-15s+%-7s+%-7s+%-7s+%-7s+%-80s+\n";
        String hline = String.format(headFormat, (new String(new char[4])).replace("\0", "-"), (new String(new char[20])).replace("\0", "-"), (new String(new char[15])).replace("\0", "-"), (new String(new char[7])).replace("\0", "-"), (new String(new char[7])).replace("\0", "-"), (new String(new char[7])).replace("\0", "-"), (new String(new char[7])).replace("\0", "-"), (new String(new char[80])).replace("\0", "-"));

        System.out.print(hline);
        System.out.format(format, "#", "Name", "Type", "Level", "Exp", "HP", "Mana", "Equipments");
        System.out.print(hline);
        
        int i = 1;
        for (Hero h: heroes.getMember()) {
            System.out.format(format, i, h.getName(), h.getClass().getSimpleName(), h.getLevel(), h.getExp(), h.getHp(), h.getMana(), String.format("%s | %s | %s | %s | %s", h.getWeapon(), h.getArmor(), h.getFireSpell(), h.getIceSpell(), h.getLightningSpell()));
            System.out.print(hline);
            i++;
        }
    }
}
