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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Predicate;

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
import edu.bu.phuminw.quest.util.Damage;
import edu.bu.phuminw.quest.util.Team;

public class Quest {
    private int MAXHERO = 3;
    public static final String BUSH = "B";
    public static final String HERO_NEXUS = "N";
    public static final String MONSTER_NEXUS = "N";
    public static final String PLAIN = "P";
    public static final String CAVE = "C";
    public static final String KOULOU = "K";
    public static final String FORBIDDEN = "X";

    private StdinWrapper sinwrap;
    private Random rand;
    private Board<Object> board;
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
        board = new Board<Object>(row, col);
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
        // TODO: INIT THE BOARD
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

        // Assign BUSH
        board.getCell(15).setType(BUSH);
        plainCell.remove(Integer.valueOf(15));
        board.getCell(16).setType(BUSH);
        plainCell.remove(Integer.valueOf(16));
        board.getCell(26).setType(BUSH);
        plainCell.remove(Integer.valueOf(26));
        board.getCell(28).setType(BUSH);
        plainCell.remove(Integer.valueOf(28));
        board.getCell(36).setType(BUSH);
        plainCell.remove(Integer.valueOf(36));
        board.getCell(40).setType(BUSH);
        plainCell.remove(Integer.valueOf(40));

        // Assign CAVE
        board.getCell(12).setType(CAVE);
        plainCell.remove(Integer.valueOf(12));
        board.getCell(25).setType(CAVE);
        plainCell.remove(Integer.valueOf(25));

        // Assign KOULOU
        board.getCell(29).setType(KOULOU);
        plainCell.remove(Integer.valueOf(29));
        board.getCell(31).setType(KOULOU);
        plainCell.remove(Integer.valueOf(31));
        board.getCell(41).setType(KOULOU);
        plainCell.remove(Integer.valueOf(41));
        board.getCell(42).setType(KOULOU);
        plainCell.remove(Integer.valueOf(42));
        board.getCell(44).setType(KOULOU);
        plainCell.remove(Integer.valueOf(44));

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
                                player.addHero(new Paladin(toAdd.getName(), toAdd.getMana(), toAdd.getSkills().getStr(),
                                        toAdd.getSkills().getDex(), toAdd.getSkills().getAgi(), toAdd.getMoney(),
                                        toAdd.getExp()));
                                break;
                            case "SORCERER":
                                player.addHero(new Sorcerer(toAdd.getName(), toAdd.getMana(),
                                        toAdd.getSkills().getStr(), toAdd.getSkills().getDex(),
                                        toAdd.getSkills().getAgi(), toAdd.getMoney(), toAdd.getExp()));
                                break;
                            case "WARRIOR":
                                player.addHero(new Warrior(toAdd.getName(), toAdd.getMana(), toAdd.getSkills().getStr(),
                                        toAdd.getSkills().getDex(), toAdd.getSkills().getAgi(), toAdd.getMoney(),
                                        toAdd.getExp()));
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
        board = new Board<Object>(board.getSize()[0], board.getSize()[1]);
        assignCell();
        selectHero();
    }

    /**
     * Core method contains game logic
     * 
     * @throws ClassNotFoundException
     * @throws IOException
     */

    public void play() throws ClassNotFoundException, IOException {
        // Create player for Quest
        board.print(false);
        System.exit(0);
        sinwrap.setMessage("Please enter your name: ");
        String token;
        while ((token = sinwrap.next()) == null) {
        }
        player = new Player(rand.nextInt(), token, MAXHERO);
        System.out.printf("Welcome %s!\n\n", player.getName());
        sinwrap.setMessage("Want mark do you wanna use? ");
        // while ((token = sinwrap.next()) == null || token.toUpperCase().equals(MARKET)
        //         || token.toUpperCase().equals(FORBIDDEN) || token.length() != 1) {
        //     System.out.println("Unacceptable mark\n");
        // }
        player.getMark().set(token.toUpperCase() + "^");

        selectHero();

        while (true) {
            market.shop(player);

            // Assign starting point
            int[] size = board.getSize();
            int currentPos = rand.nextInt(size[0] * size[1]) + 1;
            board.getCell(currentPos).set(player, player.getMark());

            boolean end = false;

            while (!end) {
                board.print(false);
                sinwrap.setMessage("Which direction to move (W/A/S/D)? ");

                boolean valid = false;
                int newPos = -1;

                // Ask for move and store new position
                while (!end && !valid) {
                    Character choice = sinwrap.nextChar();
                    if (choice == null) {
                        if (sinwrap.isEnd() || sinwrap.isQuit()) {
                            Quest.quit();
                            return;
                        } else if (sinwrap.isInfo())
                            player.printBasicInfo();
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
                                        && board.isValidADMove(currentPos, currentPos - 1)) {
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
                                        && board.isValidADMove(currentPos, currentPos + 1)) {
                                    valid = true;
                                    newPos = currentPos + 1;
                                }
                                break;
                            default:
                                System.err.println("Unexpected error of choice in moving");
                        }
                    }
                }

                switch (board.getCell(newPos).getMark().toString()) {
                    // case MARKET:
                    //     market.shop(player);
                    //     break;
                    case FORBIDDEN:
                        System.out.println("That cell is not accessible");
                        break;
                    // Fight with monsters!
                    default:
                        board.getCell(newPos).set(player, player.getMark());
                        board.getCell(currentPos).clear();
                        currentPos = newPos;
                        board.print(false);
                        if (!isNormalCell()) {
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
                            int monSize = player.getNumHero();
                            ArrayList<Monster> monTeam = new ArrayList<Monster>();
                            ArrayList<Monster> targetMonster = monster.get(monLv);

                            for (int i = 0; i < monSize; i++) {
                                Collections.shuffle(targetMonster);
                                Monster toAdd = targetMonster.get(0);

                                // Deep copy of an Item
                                switch (toAdd.getClass().getSimpleName().toUpperCase()) {
                                    case "DRAGON":
                                        monTeam.add(new Dragon(toAdd.getName(), toAdd.getLevel(),
                                                toAdd.getBaseDamage() / 1.05, toAdd.getDefense(), toAdd.getDodge()));
                                        break;
                                    case "EXOSKELETON":
                                        monTeam.add(new Exoskeleton(toAdd.getName(), toAdd.getLevel(),
                                                toAdd.getBaseDamage(), toAdd.getDefense() / 1.05, toAdd.getDodge()));
                                        break;
                                    case "SPIRIT":
                                        monTeam.add(new Spirit(toAdd.getName(), toAdd.getLevel(), toAdd.getBaseDamage(),
                                                toAdd.getDefense(), toAdd.getDodge() / 1.05));
                                        break;
                                    default:
                                        System.out.printf("Undefined monster %s\n", toAdd.getClass().getSimpleName());
                                }
                            }

                            Hero hToFight = null;
                            ArrayList<Hero> died = new ArrayList<Hero>();

                            while (monTeam.size() > 0 && died.size() < player.getNumHero()) {
                                Monster mToFight = monTeam.get(0);
                                System.out.printf("\nMonster List\n");

                                do {
                                    for (Monster m : monTeam)
                                        System.out.printf("[!] %-3s %s\n",
                                                (mToFight == m) ? "-->" : (new String(new char[4])).replace("\0", ""),
                                                m);
                                    System.out.println();
                                } while ((hToFight = player.getHero()) == null || hToFight.getHp() <= 0); // Ask for
                                                                                                          // hero to
                                                                                                          // fight

                                if (!died.contains(hToFight)) {
                                    Damage heroDamage = hToFight.makeAttack();
                                    Damage monDamage = mToFight.makeAttack();

                                    if (!mToFight.attack(heroDamage)) {
                                        // remove monster from list
                                        monTeam.removeIf(new Predicate<Monster>() {
                                            public boolean test(Monster m) {
                                                return m == mToFight;
                                            }
                                        });
                                    }
                                    if (!hToFight.attack(monDamage)) {
                                        died.add(hToFight);
                                    }
                                } else {
                                    System.out.println(hToFight.getName() + " died...\n");
                                }
                            }

                            if (monTeam.size() == 0) {
                                System.out.println("\nCongratulation! All monsters cleared!\n");

                                // After match regen
                                for (Hero h : pHeroes) {
                                    if (h.getHp() <= 0)
                                        h.lostMatch();
                                    else
                                        h.winMatch(monLv);
                                }
                            } else {
                                System.out.println("\nAll heros died...\n");
                                end = true;
                            }
                        }
                        valid = true;
                }
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