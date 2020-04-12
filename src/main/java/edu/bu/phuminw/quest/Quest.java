/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import edu.bu.phuminw.quest.board.Board;
import edu.bu.phuminw.quest.board.Mark;
import edu.bu.phuminw.quest.hero.Hero;
import edu.bu.phuminw.quest.hero.Paladin;
import edu.bu.phuminw.quest.hero.Sorcerer;
import edu.bu.phuminw.quest.hero.Warrior;
import edu.bu.phuminw.quest.io.StdinWrapper;
import edu.bu.phuminw.quest.market.Market;
import edu.bu.phuminw.quest.monster.Dragon;
import edu.bu.phuminw.quest.monster.Exoskeleton;
import edu.bu.phuminw.quest.monster.Monster;
import edu.bu.phuminw.quest.monster.Spirit;
import edu.bu.phuminw.quest.player.Player;
import edu.bu.phuminw.quest.util.Creature;
import edu.bu.phuminw.quest.util.Damage;
import edu.bu.phuminw.quest.util.Team;
import edu.bu.phuminw.quest.util.Tuple;

public class Quest {
    private final int MAXHERO = 3;
    public static final String HERO_NEXUS = "N";
    public static final String MONSTER_NEXUS = "N";
    public static final String PLAIN = "P";
    public static final String BUSH = "B";
    public static final String CAVE = "C";
    public static final String KOULOU = "K";
    public static final String FORBIDDEN = "X";
    
    private final int MONSPAWNROUND = 8;

    private StdinWrapper sinwrap;
    private Random rand;
    private Board<Creature> board;
    private Player player;
    private HashMap<String, ArrayList<Hero>> heroes;
    private ArrayList<String> heroTypes;
    private HashMap<Integer, ArrayList<Monster>> monster; // K: level, V: Monster
    private Market market;

    /**
     * Constructor of Quest board game
     * 
     * @param row Number of board rows
     * @param col Number of board columns
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public Quest(int row, int col) throws IOException, ClassNotFoundException {
        rand = new Random();
        board = new Board<Creature>(row, col);
        sinwrap = new StdinWrapper("");
        market = new Market();
        heroes = new HashMap<String, ArrayList<Hero>>();
        heroTypes = new ArrayList<String>();
        monster = new HashMap<Integer, ArrayList<Monster>>();

        // Assign market and non-accessible cell
        assignCell();

        // Load game data
        String DBPATH = findDb();
        loadHero(DBPATH + "creature/hero/");
        loadMonster(DBPATH + "creature/monster/");
    }

    /**
     * Assign nexus and forbidden cell into board
     */

    private void assignCell() {
        int[] boardSize = board.getSize();

        ArrayList<Integer> plainCell = new ArrayList<Integer>();

        for (int i = 1; i <= boardSize[0] * boardSize[1]; i++)
            plainCell.add(i);

        // Assign monster nexus
        for (int j = 1; j <= boardSize[1]; j++) {
            board.getCell((1 - 1) * boardSize[1] + j).setType(MONSTER_NEXUS);
            plainCell.remove(Integer.valueOf((1 - 1) * boardSize[1] + j));
        }

        // Assign hero nexus
        for (int j = 1; j <= boardSize[1]; j++) {
            board.getCell((8 - 1) * boardSize[1] + j).setType(HERO_NEXUS);
            plainCell.remove(Integer.valueOf((8 - 1) * boardSize[1] + j));
        }

        // Assign forbidden (river)
        for (int j = 1; j <= boardSize[1]; j++) {
            board.getCell((j - 1) * boardSize[1] + 3).setType(FORBIDDEN);
            // Special mark for forbidden cells
            board.getCell((j - 1) * boardSize[1] + 3).set(null, new Mark("X X X"));
            plainCell.remove(Integer.valueOf((j - 1) * boardSize[1] + 3));
        }

        for (int j = 1; j <= boardSize[1]; j++) {
            board.getCell((j - 1) * boardSize[1] + 6).setType(FORBIDDEN);
            // Special mark for forbidden cells
            board.getCell((j - 1) * boardSize[1] + 6).set(null, new Mark("X X X"));
            plainCell.remove(Integer.valueOf((j - 1) * boardSize[1] + 6));
        }

        // 5% of each special cell
        int expectedBush = (int) Math.round(boardSize[0]*boardSize[1]*0.05);
        int expectedCave = (int) Math.round(boardSize[0]*boardSize[1]*0.05);
        int expectedKoulou = (int) Math.round(boardSize[0]*boardSize[1]*0.05);

        int potentialPos; // Share temp variable among loops

        while (expectedBush > 0) {
            do {
                potentialPos = rand.nextInt(boardSize[0]*boardSize[1])+1;
            }
            while (board.getCell(potentialPos).getType() != null);

            board.getCell(potentialPos).setType(BUSH);
            plainCell.remove(Integer.valueOf(potentialPos));
            expectedBush--;
        }

        while (expectedCave > 0) {
            do {
                potentialPos = rand.nextInt(boardSize[0]*boardSize[1])+1;
            }
            while (board.getCell(potentialPos).getType() != null);

            board.getCell(potentialPos).setType(CAVE);
            plainCell.remove(Integer.valueOf(potentialPos));
            expectedCave--;
        }

        while (expectedKoulou > 0) {
            do {
                potentialPos = rand.nextInt(boardSize[0]*boardSize[1])+1;
            }
            while (board.getCell(potentialPos).getType() != null);

            board.getCell(potentialPos).setType(KOULOU);
            plainCell.remove(Integer.valueOf(potentialPos));
            expectedKoulou--;
        }

        // Assign the rest as plain cell
        for (Integer pos: plainCell)
            board.getCell(pos).setType(PLAIN);
    }

    /**
     * Find db folder path that contains game data
     * 
     * @return Path to db folder
     * @throws IOException
     */

    public static String findDb() throws IOException {
        ArrayDeque<File> queue = new ArrayDeque<File>();
        queue.add(new File("src/")); // Search only under src folder
        while (!queue.isEmpty()) {
            File nextFile = queue.remove();
            if (nextFile.isDirectory()) {
                if (nextFile.getName().equals("db"))
                    return nextFile.getCanonicalPath() + "/";

                File[] subdirs = nextFile.listFiles();
                for (File f : subdirs)
                    queue.add(f);
            }
        }
        return null;
    }

    /**
     * Load heroes into Quest game
     * 
     * @param heroDbPath Path to folder contains heroes data in csv format
     * @throws IOException
     */

    private void loadHero(String heroDbPath) throws IOException {
        File[] heroCsv = new File(heroDbPath).listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return pathname.getName().substring(pathname.getName().length() - 4, pathname.getName().length())
                        .equals(".csv");
            }
        });

        for (File f : heroCsv) {
            BufferedReader br = new BufferedReader(new FileReader(f));
            br.readLine(); // skip header
            String type = f.getName().substring(0, f.getName().indexOf('.')).toUpperCase();

            String line = "";

            while ((line = br.readLine()) != null) {
                // Name,mana,strength,agility,dexterity,starting money,starting experience
                String[] tokens = line.replace("\n", "").strip().split(",");

                // Expect 7 columns, otherwise skip
                if (tokens.length == 7) {
                    ArrayList<Hero> value;
                    switch (type) {
                        case "PALADINS":
                            Paladin p = new Paladin(tokens[0].replace("-", " ").replace("_", " "),
                                    Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]),
                                    Double.parseDouble(tokens[4]), Double.parseDouble(tokens[3]),
                                    Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]));
                            if ((value = heroes.get(p.getClass().getSimpleName().toUpperCase())) == null) {
                                value = new ArrayList<Hero>();
                                value.add(p);
                                heroes.put(p.getClass().getSimpleName().toUpperCase(), value);
                            } else
                                heroes.get(p.getClass().getSimpleName().toUpperCase()).add(p);
                            if (!heroTypes.contains(p.getClass().getSimpleName()))
                                heroTypes.add(p.getClass().getSimpleName());
                            break;
                        case "SORCERERS":
                            Sorcerer s = new Sorcerer(tokens[0].replace("-", " ").replace("_", " "),
                                    Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]),
                                    Double.parseDouble(tokens[4]), Double.parseDouble(tokens[3]),
                                    Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]));
                            if ((value = heroes.get(s.getClass().getSimpleName().toUpperCase())) == null) {
                                value = new ArrayList<Hero>();
                                value.add(s);
                                heroes.put(s.getClass().getSimpleName().toUpperCase(), value);
                            } else
                                heroes.get(s.getClass().getSimpleName().toUpperCase()).add(s);
                            if (!heroTypes.contains(s.getClass().getSimpleName()))
                                heroTypes.add(s.getClass().getSimpleName());
                            break;
                        case "WARRIORS":
                            Warrior w = new Warrior(tokens[0].replace("-", " ").replace("_", " "),
                                    Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]),
                                    Double.parseDouble(tokens[4]), Double.parseDouble(tokens[3]),
                                    Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]));
                            if ((value = heroes.get(w.getClass().getSimpleName().toUpperCase())) == null) {
                                value = new ArrayList<Hero>();
                                value.add(w);
                                heroes.put(w.getClass().getSimpleName().toUpperCase(), value);
                            } else
                                heroes.get(w.getClass().getSimpleName().toUpperCase()).add(w);
                            if (!heroTypes.contains(w.getClass().getSimpleName()))
                                heroTypes.add(w.getClass().getSimpleName());
                            break;
                        default:
                            System.err.println("Encountered undefined monster type");
                    }
                } else {
                    System.out.printf("Len is %d\n", tokens.length);
                }
            }

            br.close();
        }
    }

    /**
     * Load monsters into Quest game
     * 
     * @param monDbPath Path to folder contains monsters data in csv format
     * @throws IOException
     */

    private void loadMonster(String monDbPath) throws IOException {
        File[] monCsv = new File(monDbPath).listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return pathname.getName().substring(pathname.getName().length() - 4, pathname.getName().length())
                        .equals(".csv");
            }
        });

        for (File f : monCsv) {
            BufferedReader br = new BufferedReader(new FileReader(f));
            br.readLine(); // skip header
            String type = f.getName().substring(0, f.getName().indexOf('.')).toUpperCase();

            String line = "";

            while ((line = br.readLine()) != null) {
                // Name,level,damage,defense,dodge chance
                String[] tokens = line.replace("\n", "").strip().split(",");

                // Expect 5 columns, otherwise skip
                if (tokens.length == 5) {
                    ArrayList<Monster> value;
                    switch (type) {
                        case "DRAGONS":
                            Dragon d = new Dragon(tokens[0].replace("-", " ").replace("_", " "),
                                    Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2]),
                                    Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]) / 100);
                            if ((value = monster.get(d.getLevel())) == null) {
                                value = new ArrayList<Monster>();
                                value.add(d);
                                monster.put(d.getLevel(), value);
                            } else {
                                if (!monster.get(d.getLevel()).contains(d))
                                    monster.get(d.getLevel()).add(d);
                            }
                            break;
                        case "EXOSKELETONS":
                            Exoskeleton e = new Exoskeleton(tokens[0].replace("-", " ").replace("_", " "),
                                    Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2]),
                                    Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]) / 100);
                            if ((value = monster.get(e.getLevel())) == null) {
                                value = new ArrayList<Monster>();
                                value.add(e);
                                monster.put(e.getLevel(), value);
                            } else {
                                if (!monster.get(e.getLevel()).contains(e))
                                    monster.get(e.getLevel()).add(e);
                            }
                            break;
                        case "SPIRITS":
                            Spirit s = new Spirit(tokens[0].replace("-", " ").replace("_", " "),
                                    Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2]),
                                    Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]) / 100);
                            if ((value = monster.get(s.getLevel())) == null) {
                                value = new ArrayList<Monster>();
                                value.add(s);
                                monster.put(s.getLevel(), value);
                            } else {
                                if (!monster.get(s.getLevel()).contains(s))
                                    monster.get(s.getLevel()).add(s);
                            }
                            break;
                        default:
                            System.err.println("Encountered undefined monster type");
                    }
                }
            }

            br.close();
        }
    }

    /**
     * Ask player to select hero before starting the game. Ensure at least one and
     * at most MAXHERO
     * 
     * @throws IOException
     * @throws ClassNotFoundException
     */

    private void selectHero() throws IOException, ClassNotFoundException {
        String tFormat = "|%-4s|%-10s|\n";
        String tHeadFormat = "+%-4s+%-10s+\n";
        String thline = String.format(tHeadFormat, (new String(new char[4])).replace("\0", "-"),
                (new String(new char[10])).replace("\0", "-"));
        String hFormat = "|%-4s|%-20s|%-15s|%-7s|%-20s|%-8s|%-8s|\n";
        String hHeadFormat = "+%-4s+%-20s+%-15s+%-7s+%-20s+%-8s+%-8s+\n";
        String hhline = String.format(hHeadFormat, (new String(new char[4])).replace("\0", "-"),
                (new String(new char[20])).replace("\0", "-"), (new String(new char[15])).replace("\0", "-"),
                (new String(new char[7])).replace("\0", "-"), (new String(new char[20])).replace("\0", "-"),
                (new String(new char[8])).replace("\0", "-"), (new String(new char[8])).replace("\0", "-"));
        boolean finish = false;
        boolean success = false;

        while ((player.getNumHero() < 1 || !finish) && player.getNumHero() < MAXHERO) {
            System.out.printf("\n\n%d hero(es) selected.\n\n", player.getNumHero());
            finish = success = false;
            sinwrap.setMessage("Which hero type? ");
            int selectedType = -1;

            while (!finish && !success) {
                System.out.println("Hero types");
                System.out.print(thline);
                System.out.format(tFormat, "#", "Type");
                System.out.print(thline);

                int i = 1;
                for (String hType : heroTypes)
                    System.out.format(tFormat, i++, hType);

                System.out.print(thline);

                Integer choice = sinwrap.nextInt();

                if (choice == null) {
                    if (sinwrap.isEnd() || sinwrap.isQuit()) {
                        Quest.quit();
                        return;
                    } else if (sinwrap.isInfo())
                        System.out.println("No information to display now...\n");
                } else {
                    if (choice >= 1 && choice <= heroTypes.size()) {
                        if (heroes.get(heroTypes.get(choice - 1).toUpperCase()).size() > 0) {
                            selectedType = choice;
                            success = true;
                        } else {
                            System.out.printf("We don't have any %s\n", heroTypes.get(choice - 1));
                        }
                    }
                }
            }

            success = false;
            sinwrap.setMessage("Select hero: ");

            while (!finish && !success) {
                String type = heroTypes.get(selectedType - 1);
                System.out.println(type);
                System.out.print(hhline);
                System.out.format(hFormat, "#", "Name", "Type", "Mana", "S/D/A", "Money", "Exp");
                System.out.print(hhline);

                ArrayList<Hero> hList = heroes.get(type.toUpperCase());

                int i = 1;
                for (Hero h : hList)
                    System.out.format(
                            hFormat, i++, h.getName(), type, h.getMana(), String.format("%.1f/%.1f/%.1f",
                                    h.getSkills().getStr(), h.getSkills().getDex(), h.getSkills().getAgi()),
                            h.getMoney(), h.getExp());

                System.out.print(hhline);

                Integer choice = sinwrap.nextInt();

                if (choice == null) {
                    if (sinwrap.isEnd()) {
                        finish = true;
                        break;
                    } else if (sinwrap.isQuit()) {
                        Quest.quit();
                        return;
                    } else if (sinwrap.isInfo())
                        System.out.println("\nNo information to display now...");
                } else {
                    if (1 <= choice && choice <= hList.size()) {
                        Hero toAdd = hList.get(choice - 1);
                        // Deep copy of a Hero
                        switch (toAdd.getClass().getSimpleName().toUpperCase()) {
                            case "PALADIN":
                                Paladin pal = new Paladin(toAdd.getName(), toAdd.getMana(), toAdd.getSkills().getStr(),
                                        toAdd.getSkills().getDex(), toAdd.getSkills().getAgi(), toAdd.getMoney(),
                                        toAdd.getExp());
                                pal.setMark(new Mark("H" + (player.getNumHero()+1)));
                                player.addHero(pal);
                                break;
                            case "SORCERER":
                                Sorcerer sor = new Sorcerer(toAdd.getName(), toAdd.getMana(),
                                        toAdd.getSkills().getStr(), toAdd.getSkills().getDex(),
                                        toAdd.getSkills().getAgi(), toAdd.getMoney(), toAdd.getExp());
                                sor.setMark(new Mark("H" + (player.getNumHero()+1)));
                                player.addHero(sor);
                                break;
                            case "WARRIOR":
                                Warrior war = new Warrior(toAdd.getName(), toAdd.getMana(), toAdd.getSkills().getStr(),
                                        toAdd.getSkills().getDex(), toAdd.getSkills().getAgi(), toAdd.getMoney(),
                                        toAdd.getExp());
                                war.setMark(new Mark("H" + (player.getNumHero()+1)));
                                player.addHero(war);
                                break;
                            default:
                                System.out.printf("Undefined hero type %s\n", toAdd.getClass().getSimpleName());
                        }
                        success = true;
                    }
                }
            }
        }
    }

    /**
     * Determine whether this common cell has monster
     * 
     * @return Either this cell is normal or has monsters
     */
    @SuppressWarnings({"unused"})

    private boolean isNormalCell() {
        return (rand.nextDouble() > 0.75); // Probability of being normal cell is 25%
    }

    /**
     * Clear game / Start new game
     * 
     * @throws ClassNotFoundException
     * @throws IOException
     */

    private void clear() throws ClassNotFoundException, IOException {
        player = new Player(rand.nextInt(), "DEBUG 1", MAXHERO);
        player.getMark().set("*");
        sinwrap = new StdinWrapper("");
        board = new Board<Creature>(board.getSize()[0], board.getSize()[1]);
        assignCell();
        selectHero();
    }

    /**
     * Compute lane map given row [start, end)
     * 
     * @param row row number
     * @return A list of lane map consisting of tuples
     */

    private ArrayList<Tuple<Integer, Integer>> getLaneMap(int row) {
        int[] boardSize = board.getSize();

        // Map of each lane
        ArrayList<Tuple<Integer, Integer>> laneMap = new ArrayList<Tuple<Integer, Integer>>();
        for (int i = ((row-1) * boardSize[1]) + 1 ; i <= boardSize[1]*row ;) {
            int forbidInd = i+1;
            
            while (forbidInd <= boardSize[1]*row && !board.getCell(forbidInd).getType().equals(FORBIDDEN)) {
                forbidInd++;
            }

            laneMap.add(new Tuple<Integer,Integer>(i, forbidInd));
            i = forbidInd+1;
        }

        return laneMap;
    }

    /**
     * Randomly assign Creatures to given row. One per lane.
     * 
     * @param <T> Creature object
     * @param row Row to assign Creatures
     * @param toPlace Creatures to place
     */

    private <T extends Creature> void randomAssignPerLane(int row, List<T> toPlace) {
        // Map of each lane
        ArrayList<Tuple<Integer, Integer>> rowMap = getLaneMap(row);

        ListIterator<Tuple<Integer, Integer>> iter = rowMap.listIterator();

        // Place one by one from toPlace
        for (T t: toPlace) {
            boolean assigned = false;

            while (!assigned) {
                Tuple<Integer, Integer> laneBound = iter.next();
                ArrayList<Integer> lane = new ArrayList<Integer>();

                // Space in the lane
                for (Integer i = laneBound.getFirst() ; i < laneBound.getSecond() ; i++)
                    lane.add(i);

                // Shuffle for randomness
                Collections.shuffle(lane);

                for (Integer position: lane) {
                    if (board.getCell(position).getType().equals(HERO_NEXUS) && board.getCell(position).getOccipier() == null) {
                        board.getCell(position).set(t, t.getMark());
                        assigned = true;
                        t.setPosition(board.getCell(position));
                        break;
                    }
                }

                // No lane left but have unassigned Creature(s)
                if (!assigned && !iter.hasNext()) {
                    System.err.println("No place to assign");
                    System.exit(-1);
                }
            }
        }
    }
    
    /**
     * Create monsters of the requested size and based on max hero level
     * 
     * @param monSize Number of Monster to create
     * @return A list of Monsters
     */

    private ArrayList<? extends Monster> spawnMonster(int monSize) {
        Team<Hero> pHeroes = player.getHeroTeam();
        ArrayList<Integer> hLv = new ArrayList<Integer>();
        for (Hero h : pHeroes)
            hLv.add(h.getLevel());

        // Choose monster level
        int maxLv = Collections.max(hLv); // Max of heroes level
        int maxMonLv = Collections.max(monster.keySet()); // Max of mon level
        int monLv = -1;
        if (maxLv > maxMonLv)
            monLv = maxMonLv;
        else {
            monLv = maxLv;

            // In case no monster with same level as heroes max level
            while (!monster.containsKey(monLv) && monLv > 1) {
                monLv--;
            }
        }

        // Prepare monsters for fight
        ArrayList<Monster> monTeam = new ArrayList<Monster>();
        ArrayList<Monster> targetMonster = monster.get(monLv);

        for (int i = 0; i < monSize; i++) {
            Collections.shuffle(targetMonster);
            Monster toAdd = targetMonster.get(0);

            // Deep copy of an Item
            switch (toAdd.getClass().getSimpleName().toUpperCase()) {
                case "DRAGON":
                    Dragon d = new Dragon(toAdd.getName(), toAdd.getLevel(),
                            toAdd.getBaseDamage() / 1.05, toAdd.getDefense(), toAdd.getDodge());
                    d.setMark(new Mark(d.getName().substring(0, 2).toUpperCase()));
                    monTeam.add(d);
                    break;
                case "EXOSKELETON":
                    Exoskeleton e = new Exoskeleton(toAdd.getName(), toAdd.getLevel(),
                            toAdd.getBaseDamage(), toAdd.getDefense() / 1.05, toAdd.getDodge());
                    e.setMark(new Mark(e.getName().substring(0, 2).toUpperCase()));
                    monTeam.add(e);
                    break;
                case "SPIRIT":
                    Spirit s = new Spirit(toAdd.getName(), toAdd.getLevel(), toAdd.getBaseDamage(),
                            toAdd.getDefense(), toAdd.getDodge() / 1.05);
                    s.setMark(new Mark(s.getName().substring(0, 2).toUpperCase()));
                    monTeam.add(s);
                    break;
                default:
                    System.out.printf("Undefined monster %s\n", toAdd.getClass().getSimpleName());
            }
        }

        return monTeam;
    }

    /**
     * Teleport given hero to other lane or do nothing if the player chooses to do so
     * 
     * @param h
     */

    private int teleport(Hero h) {
        sinwrap.setMessage("Which position to transport to? ");
        Integer dest;

        while (true) {
            board.print(true);
            dest = sinwrap.nextInt();

            if (dest == null) {
                if (sinwrap.isQuit()) {
                    Quest.quit();
                } 
                else if (sinwrap.isInfo())
                    board.print(true);
            }
            else {
                if (board.isInBoard(dest) && !board.getCell(dest).getType().equals(FORBIDDEN)) {
                    ArrayList<Tuple<Integer, Integer>> laneMap = getLaneMap(((h.getPosition().getPosition()-1)/8)+1);

                    // Tuple<Integer, Integer> oldLane = null;
                    int oldLane = -1;
                    int newLane = -1;
                    Tuple<Integer, Integer> newLaneTup = null;

                    for (int i = 0 ; i < laneMap.size() ; i++) {
                        if (laneMap.get(i).getFirst() <= h.getPosition().getPosition() && h.getPosition().getPosition() < laneMap.get(i).getSecond()) {
                            oldLane = i;
                            break;
                        }
                    }

                    laneMap = getLaneMap(((dest-1)/8)+1);

                    for (int i = 0 ; i < laneMap.size() ; i++) {
                        if (laneMap.get(i).getFirst() <= dest && dest < laneMap.get(i).getSecond()) {
                            newLane = i;
                            newLaneTup = laneMap.get(i);
                            break;
                        }
                    }

                    // Allow only moving to different lane
                    if (newLane != oldLane) {
                        int[] boardSize = board.getSize();

                        // Check if any enemy behind or in the row of the new destination
                        boolean existMon = false;

                        for (int rowOffset = 0 ; rowOffset < boardSize[0]-(dest-1)/8 ; rowOffset++) {
                            for (int pos = newLaneTup.getFirst()+(rowOffset*boardSize[1]) ; pos < newLaneTup.getSecond()+(rowOffset*boardSize[1]) ; pos++) {
                                if (board.getCell(pos).getOccipier() instanceof Monster) {
                                    existMon = true;
                                    break;
                                }
                            }
                            if (existMon)
                                break;
                        }

                        // Clear to place at this position
                        if (!existMon) {
                            return dest;
                        }
                    }
                }
            }
        }
    }

    /**
     * Determine if either heros or monsters have won
     * 
     * @param playerHeroes Active heroes
     * @param Monsters Active monsters
     * @return boolean value of whether either heros or monsters has won
     */

    public boolean checkIfEnd(List<Hero> playerHeroes, ArrayList<Monster> monsters){
        // if Heros win:
        for (Hero h: playerHeroes) {
            if (h.getPosition().getType().equals(MONSTER_NEXUS) && (h.getPosition().getPosition()-1)/8 == 0) { // Check cell type and row
                // This hero reached nexus
                System.out.printf("%s has reached Monster's nexus. Game ended.\n", h.getName());
                return true;
            }
        }

        // if Monsters win:
        for (Monster m: monsters) {
            if (m.getPosition().getType().equals(HERO_NEXUS) && (m.getPosition().getPosition()-1)/8 == board.getSize()[0]-1) { // Check cell type and row
                // This monster reached nexus
                System.out.printf("%s has reached Hero's nexus. Game ended.\n", m.getName());
                return true;
            }
        }
        return false;
    }

    /**
     * Core method contains game logic
     * 
     * @throws ClassNotFoundException
     * @throws IOException
     */

    public void play() throws ClassNotFoundException, IOException {
        // Create player for Quest
        sinwrap.setMessage("Please enter your name: ");
        String token;
        while ((token = sinwrap.next()) == null) {
        }
        player = new Player(rand.nextInt(), token, MAXHERO);
        System.out.printf("Welcome %s!\n\n", player.getName());

        selectHero();

        // Loop game play until decided to end
        while (true) {
            // Assign starting point
            int[] size = board.getSize();
            int round = 0; // Round count
            List<Hero> playerHeroes = player.getHeroTeam().getRandomMembers();
            ArrayList<Monster> monsters = new ArrayList<Monster>(); // Maintain active monsters

            // Assign heroes to the last row
            randomAssignPerLane(size[0], playerHeroes);

            board.print(false);

            boolean end = false;

            // Per round loop: Move monsters, move heroes (move/fight/teleport/etc)
            while (!end) {
                board.print(false);
                /* 0) Enter market for heroes in nexus */
                for (Hero h: playerHeroes) {
                    if (h.getPosition().getType().equals(HERO_NEXUS))
                        market.shop(h);
                }
                
                /* 1) Spawn new monsters if in right timing */
                if (round % MONSPAWNROUND == 0) {
                    ArrayList<? extends Monster> spawnedMon = spawnMonster(playerHeroes.size());
                    monsters.addAll(spawnedMon);
                    // Assign monsters to the first row
                    randomAssignPerLane(1,spawnedMon);
                }

                
                /* 2) Move monsters forward */
                for (Monster m: monsters)
                    board.move(m.getPosition().getPosition(), m.getPosition().getPosition()+size[1]);

                /* 3) Move all heroes */
                HashSet<Hero> movedHeroes = new HashSet<Hero>();
                
                // All three heroes must move, once each
                while (movedHeroes.size() < 3) {
                    board.print(false);
                    Hero toMove;
                    
                    // Each hero can move once per round
                    do {
                        toMove = player.getHero();
                    } while (toMove == null || movedHeroes.contains(toMove));
                
                    boolean valid = false;
                    sinwrap.setMessage("Which direction to move (W/A/S/D/T/B)? ");

                    valid = false;
                    int currentPos = toMove.getPosition().getPosition();
                    int newPos = -1;

                    // Ask for move and store new position
                    while (!end && !valid) {
                        Character choice = sinwrap.nextChar();
                        if (choice == null) {
                            if (sinwrap.isEnd() || sinwrap.isQuit()) {
                                Quest.quit();
                                return;
                            } 
                            else if (sinwrap.isInfo())
                                board.print(false);
                            else if (sinwrap.isTeleport()) {
                                newPos = teleport(toMove);
                                valid = true;
                            }
                            else if (sinwrap.isBackNexus()) {
                                // Move down until reach nexus
                                newPos = currentPos;
                                while (newPos < (size[0]-1)*size[1])
                                    newPos += size[1];

                                // Find alternative nexus if this one is occupied by some hero
                                if (board.getCell(newPos).getOccipier() != null) {
                                    ArrayList<Tuple<Integer, Integer>> laneMap = getLaneMap(((newPos-1)/8)+1);

                                    Tuple<Integer, Integer> lane = null;

                                    for (Tuple<Integer, Integer> l: laneMap) {
                                        if (l.getFirst() <= newPos && newPos < l.getSecond()) {
                                            lane = l;
                                            break;
                                        }
                                    }

                                    int i;
                                    for (i = lane.getFirst() ; i < lane.getSecond() && board.getCell(i).getOccipier() != null ; i++) {}

                                    newPos = i;
                                }

                                valid = true;
                            }
                        } else {
                            choice = Character.toUpperCase(choice);
                            switch (choice) {
                                case 'W':
                                    if (board.isInBoard(currentPos - size[1])) {
                                        valid = true;
                                        newPos = currentPos - size[1];
                                    }
                                    break;
                                case 'A':
                                    if (board.isInBoard(currentPos - 1)
                                            && board.isSameRow(currentPos, currentPos - 1)) {
                                        valid = true;
                                        newPos = currentPos - 1;
                                    }
                                    break;
                                case 'S':
                                    if (board.isInBoard(currentPos + size[1] + 1)) {
                                        valid = true;
                                        newPos += currentPos + size[1] + 1;
                                    }
                                    break;
                                case 'D':
                                    if (board.isInBoard(currentPos + 1)
                                            && board.isSameRow(currentPos, currentPos + 1)) {
                                        valid = true;
                                        newPos = currentPos + 1;
                                    }
                                    break;
                                default:
                                    System.err.println("Unexpected error of choice in moving");
                            }
                        }
                    }

                    board.move(currentPos, newPos);
                    movedHeroes.add(toMove);
                }

                /* 4) Check whether reached nexus, engage in fight if not */

                if (checkIfEnd(playerHeroes, monsters)) {
                    end = true;
                    break;
                }

                /* 5) Engage in fight: choose hero for fight */
                // , fight (choose mon if > 1), check whether mon is cleared on row ahead all heroes
                boolean monAheadClear = false; // Flag indicating monsters right ahead heroes all clear
                while (!end && !monAheadClear) {
                    // Clear monster one by one if that has some hero right ahead
                    int monMustFightNum = 0;
                    ArrayList<Monster> deadMon = new ArrayList<Monster>();

                    for (Monster mToFight: monsters) {
                        // Lane map of row right ahead this monster
                        ArrayList<Tuple<Integer, Integer>> laneMapAheadMon = getLaneMap(((mToFight.getPosition().getPosition()-1)/8)+1);

                        // Will be used to check any hero ahead
                        Tuple<Integer, Integer> rowMon = null;
                        int laneNum = -1;
                        int i = 1;

                        for (Tuple<Integer, Integer> l: laneMapAheadMon) {
                            if (l.getFirst() <= mToFight.getPosition().getPosition() && mToFight.getPosition().getPosition() < l.getSecond()) {
                                rowMon = l;
                                laneNum = i;
                                break;
                            }
                            i++;
                        }

                        ArrayList<Hero> heroAheadMon = new ArrayList<Hero>();

                        // Figure out any hero waiting ahead?
                        for (i = rowMon.getFirst()+size[1] ; i < rowMon.getSecond()+size[1] ; i++) {
                            if (board.getCell(i).getOccipier() instanceof Hero)
                                heroAheadMon.add((Hero) board.getCell(i).getOccipier());
                        }

                        // Engage in fight when exists hero waiting ahead
                        if (heroAheadMon.size() > 0 && !end) {
                            monMustFightNum++;
                            board.print(false);
                            System.out.printf("\nFighting with %s in lane %d\n", mToFight.getName(), laneNum);

                            while (!end && mToFight.getHp() > 0 && heroAheadMon.size() > 0) {
                                // Ask for hero to fight
                                Hero hToFight;
                                do {
                                    board.print(false);
                                    System.out.printf("Choosing hero to fight (only adjacent with %s (lane %d) and alive one will be accepted)\n", mToFight.getName(), laneNum);
                                    hToFight = player.getHero();
                                } while (!heroAheadMon.contains(hToFight) || hToFight.getHp() <= 0);

                                Damage heroDamage = hToFight.makeAttack();
                                Damage monDamage = mToFight.makeAttack();

                                if (heroDamage != null) {
                                    if (!mToFight.attack(heroDamage)) {
                                        // This monster dead
                                        monMustFightNum--;
                                        // monsters.remove(mToFight);
                                        deadMon.add(mToFight);
                                        mToFight.getPosition().set(null, new Mark(""));
                                    }
                                    if (!hToFight.attack(monDamage)) {
                                        // This hero lose
                                        heroAheadMon.remove(hToFight);

                                        //Move dead hero to nexus
                                        int newPos = hToFight.getPosition().getPosition();
                                        while (newPos < (size[0]-1)*size[1])
                                            newPos += size[1];

                                        // Find alternative nexus if this one is occupied by some hero
                                        if (board.getCell(newPos).getOccipier() != null) {
                                            ArrayList<Tuple<Integer, Integer>> laneMap = getLaneMap(((newPos-1)/8)+1);

                                            Tuple<Integer, Integer> lane = null;

                                            for (Tuple<Integer, Integer> l: laneMap) {
                                                if (l.getFirst() <= newPos && newPos < l.getSecond()) {
                                                    lane = l;
                                                    break;
                                                }
                                            }

                                            for (i = lane.getFirst() ; i < lane.getSecond() && board.getCell(i).getOccipier() != null ; i++) {}

                                            newPos = i;
                                        }

                                        board.move(hToFight.getPosition().getPosition(), newPos);
                                    }
                                }
                                else {
                                    // Player wanna end since heroDamage is null
                                    end = true;
                                }
                            }
                        }

                        if (end)
                            break;
                    }

                    monsters.removeAll(deadMon);

                    if (monMustFightNum == 0) {
                        System.out.println("[!] All monsters head heroes are cleared");
                        monAheadClear = true;
                    }
                }

                round++;
            }

            sinwrap.setMessage("Wanna play again (Y/N)? ");
            end = false;

            while (!end) {
                Character choice = sinwrap.nextChar();
                if (choice != null) {
                    if (Character.toUpperCase(choice) == 'Y') {
                        clear();
                        end = true;
                    } else if (Character.toUpperCase(choice) == 'N') {
                        quit();
                        return;
                    }
                }
            }
        }
    }

    public void load() {
        // TODO: load game from save
    }

    // TODO: gracefully quit game
    public static void quit() {
        // TODO: ask to save data
        System.out.println("Bye...\n");
        System.exit(0);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Quest q = new Quest(8, 8);
        q.play();
    }
}