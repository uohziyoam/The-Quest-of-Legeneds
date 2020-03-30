package game;

import java.util.*;

import avatar.Hero;
import board.Board;
import board.square.*;
import config.*;
import equipment.*;

/**
 * Game
 */
public class Game {
    private static int MIN_MEMEBERS = 1;
    private static int MAX_MEMEBERS = 3;

    static Scanner in;

    private Board board;

    private ArrayList<Hero> herosTeam;

    private Hero captain;

    private boolean isQuitGame;

    private boolean inMarket;

    public Game() {
        in = new Scanner(System.in);
        herosTeam = new ArrayList<>();
        board = new Board(8, 8);

        initHeros();
        displayStatistics();

        Hero[] arrayNumbers = herosTeam.toArray(new Hero[herosTeam.size()]);
        // Random random = new Random();
        // int randomNumber = random.nextInt(herosTeam.size());
        captain = arrayNumbers[0];
        inMarket = false;

        // init board

        // init locaiton
        board.setPosition(captain, captain.getCurLocation(), null);
        board.printBoard();
    }

    private int herosNumbers() {
        String input = "";
        int number = 0;
        while (number < MIN_MEMEBERS || number > MAX_MEMEBERS) {
            System.out.println("HOW MANY HEROS DO YOU NEED? (1 ~ 3): ");
            input = in.next();
            if (!input.chars().allMatch(Character::isDigit)) {
                continue;
            }
            number = Integer.parseInt(input);
        }
        return number;
    }

    private String pickHeros(int cur) {

        String input = "";
        int index = 0;
        System.out.println(Color.ANSI_CYAN);
        System.out.println("PICK YOUR No. " + cur + " HEROS! (e.g. Flandal_Steelskin): ");

        for (Object hero : VARIABLES.HEROS_TO_ENUM.values()) {
            if (hero == null) {
                continue;
            }
            System.out.println(index + ". " + hero);
            index++;
        }

        System.out.println(index + ". QUIT (Q/q)");
        System.out.println(Color.ANSI_RESET);
        input = in.next();
        // first category
        while (!VARIABLES.HEROS_TO_ENUM.containsKey(input)) {
            index = 0;
            System.out.println("INVALID INPUT!");
            System.out.println(Color.ANSI_CYAN);
            System.out.println("PICK YOUR HEROS! (e.g. Flandal_Steelskin): ");

            for (Object hero : VARIABLES.HEROS_TO_ENUM.values()) {
                if (hero == null) {
                    continue;
                }
                System.out.println(index + ". " + hero);
                index++;
            }

            System.out.println(Color.ANSI_RESET);
            input = in.next();
        }
        return input;

    }

    private void initHeros() {
        int number = herosNumbers();
        // int number = 1;
        // TODO

        ArrayList<String> arr = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            String hero = pickHeros(i);
            // TODO
            // String hero = "Flandal_Steelskin";
            arr.add(hero);
        }

        HashSet<Integer> common = board.getCOMMON_COORDINATES();
        System.out.println(common);
        System.out.println(common.toArray()[0]);

        for (String string : arr) {
            herosTeam.add(new Hero(string, new Coordinate(board.positionToX((Integer) common.toArray()[0]),
                    board.positionToY((Integer) common.toArray()[0]))));
        }

    }

    public void gameProcessing() {

        HashSet<Integer> common = board.getCOMMON_COORDINATES();
        while (!isQuitGame) {
            if (Math.random() < 0.2 && !inMarket
                    && !captain.getCurLocation().equals(new Coordinate(board.positionToX((Integer) common.toArray()[0]),
                            board.positionToY((Integer) common.toArray()[0])))) {
                FightRound fightRound = new FightRound(herosTeam);
            }

            while (inMarket) {
                try {
                    Hero hero = marketHeroSelection();
                    if (hero == null) {
                        break;
                    }
                    String marketCategory = displayMarketSecondInstruction();
                    purchaseFromMarket(marketCategory, new Market(), hero);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            String nextMove = displayMoveInstruction();
            if (nextMove.equals(VARIABLES.INFORMATION) || nextMove.equals("M")) {
                continue;
            }
            move(nextMove);
        }

    }

    public void move(String direction) {
        String formatDirection = direction.toUpperCase();
        Coordinate cCoordinate = captain.getCurLocation();
        try {
            if (formatDirection.equals(VARIABLES.UP)) {
                Coordinate tCoordinate = new Coordinate(cCoordinate.getX() - 1, cCoordinate.getY());
                enterMarket(tCoordinate);
                board.setPosition(captain, tCoordinate, cCoordinate);
            }

            if (formatDirection.equals(VARIABLES.DOWN)) {
                Coordinate tCoordinate = new Coordinate(cCoordinate.getX() + 1, cCoordinate.getY());
                enterMarket(tCoordinate);
                board.setPosition(captain, tCoordinate, cCoordinate);
            }

            if (formatDirection.equals(VARIABLES.LEFT)) {
                Coordinate tCoordinate = new Coordinate(cCoordinate.getX(), cCoordinate.getY() - 1);
                enterMarket(tCoordinate);
                board.setPosition(captain, tCoordinate, cCoordinate);
            }

            if (formatDirection.equals(VARIABLES.RIGHT)) {
                Coordinate tCoordinate = new Coordinate(cCoordinate.getX(), cCoordinate.getY() + 1);
                enterMarket(tCoordinate);
                board.setPosition(captain, tCoordinate, cCoordinate);
            }
            board.printBoard();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void enterMarket(Coordinate coordinate) {
        inMarket = (board.getPosition(coordinate)) instanceof Market;
    }

    public void displayStatistics() {
        for (Hero hero : herosTeam) {
            hero.printStatistics();
            System.out.println("");
        }
    }

    public String displayMoveInstruction() {
        String input = "";
        moveInstruction();
        input = in.next().toUpperCase();

        while (!input.equals(VARIABLES.UP)

                && !input.equals(VARIABLES.DOWN)

                && !input.equals(VARIABLES.LEFT)

                && !input.equals(VARIABLES.RIGHT)

                && !input.equals(VARIABLES.QUIT)

                && !input.equals(VARIABLES.INFORMATION)

                && !input.equals("M")

        ) {
            System.out.println("INVALID INPUT!");
            moveInstruction();
            input = in.next().toUpperCase();
        }

        if (input.equals(VARIABLES.QUIT)) {
            isQuitGame = true;
        }

        if (input.equals(VARIABLES.INFORMATION)) {
            for (Hero hero : herosTeam) {
                hero.printStatistics();
            }
        }

        if (input.equals("M")) {
            board.printBoard();
        }

        return input;
    }

    public String displayMarketFirstInstruction() {
        String input = "";
        marketFirstCategoryInstruction();
        input = in.next().toUpperCase();
        // first category
        while (!VARIABLES.ITEMS_CATEGORY.contains(input)) {
            System.out.println("INVALID INPUT!");
            marketFirstCategoryInstruction();
            input = in.next().toUpperCase();
        }

        if (VARIABLES.QUIT.equals(input)) {
            return VARIABLES.QUIT;
        }

        return input;
    }

    public String displayMarketSecondInstruction() {
        String input = "";
        marketFirstCategoryInstruction();
        input = in.next().toUpperCase();
        // first category
        while (!VARIABLES.ITEMS_CATEGORY.contains(input)) {
            System.out.println("INVALID INPUT!");
            marketFirstCategoryInstruction();
            input = in.next().toUpperCase();
        }

        if (VARIABLES.QUIT.equals(input)) {
            return VARIABLES.QUIT;
        }

        return input;
    }

    public void purchaseFromMarket(String category, Market market, Hero hero) {
        if (category.equals(VARIABLES.QUIT)) {
            return;
        }
        String input = "";

        while (!input.equals(VARIABLES.QUIT)) {
            marketSecondCategoryInstruction(category);
            input = in.next().toUpperCase();
            input = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
            // second category
            while (!VARIABLES.ITEMS_TO_ENUM.containsKey(input)) {
                System.out.println("INVALID INPUT!" + input);
                marketSecondCategoryInstruction(category);
                input = in.next().toUpperCase();
                input = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
            }

            if (input.equals(VARIABLES.QUIT)) {
                return;
            }
            hero.buy(input, market);
        }
    }

    private Hero marketHeroSelection() {
        String input = "0";
        System.out.println(Color.ANSI_CYAN);
        System.out.println("WHICH HERO NEED TO ENTER MARKET? (ENTER INDEX! OR Q/I): ");

        for (int i = 0; i < herosTeam.size(); i++) {
            System.out.println(i + ". " + herosTeam.get(i).getName());
        }
        System.out.println(herosTeam.size() + ". " + "QUIT (Q/q)");
        System.out.println(herosTeam.size() + 1 + ". " + "INFORMATION (I/i)");
        System.out.println(Color.ANSI_RESET);
        input = in.next();

        if (input.toUpperCase().equals(VARIABLES.QUIT)) {
            return null;
        }
        if (input.toUpperCase().equals(VARIABLES.INFORMATION)) {
            for (Hero hero : herosTeam) {
                hero.printStatistics();
            }
        }

        // Hero Selection
        while (Integer.parseInt(input) < 0 || Integer.parseInt(input) >= herosTeam.size()) {
            System.out.println("INVALID INPUT!");
            System.out.println(Color.ANSI_CYAN);
            System.out.println("WHICH HERO NEED TO ENTER MARKET? (ENTER INDEX! OR Q/I): ");

            for (int i = 0; i < herosTeam.size(); i++) {
                System.out.println(i + ". " + herosTeam.get(i).getName());
            }
            System.out.println(herosTeam.size() + ". " + "QUIT (Q/q)");
            System.out.println(herosTeam.size() + 1 + ". " + "INFORMATION (I/i)");

            System.out.println(Color.ANSI_RESET);
            input = in.next();
            if (input.toUpperCase().equals(VARIABLES.QUIT)) {
                return null;
            }

        }
        return herosTeam.get(Integer.parseInt(input));
    }

    private void moveInstruction() {
        System.out.println(Color.ANSI_RED);
        System.out.println("ENTER YOUR NEXT MOVEMENT: ");
        System.out.println("1. MOVE UP (W/w)");
        System.out.println("2. MOVE DOWN (S/s)");
        System.out.println("3. MOVE LEFT (A/a)");
        System.out.println("4. MOVE RIGHT (D/d)");
        System.out.println("5. INFORMATION (I/i)");
        System.out.println("6. MAP (M/m)");
        System.out.println("7. QUIT (Q/q)");
        System.out.println(Color.ANSI_RESET);
    }

    private void marketFirstCategoryInstruction() {
        System.out.println(Color.ANSI_CYAN);
        System.out.println("WHAT CAN I HELP YOU? (ENTER THE FULL STRING! OR Q for quit): ");
        System.out.println("1. WEAPONS");
        System.out.println("2. ARMORS");
        System.out.println("3. SPELLS");
        System.out.println("4. POTIONS");
        System.out.println("5. QUIT (Q/q)");
        System.out.println(Color.ANSI_RESET);
    }

    private void marketSecondCategoryInstruction(String category) {
        int index = 1;
        System.out.println(Color.ANSI_CYAN);

        for (Object equipment : VARIABLES.ITEMS_TO_ENUM.values()) {
            if (category.equals("WEAPONS") && equipment instanceof Weapons) {
                System.out.println(index + ". " + equipment);
                index++;
            }

            if (category.equals("ARMORS") && equipment instanceof Armors) {
                System.out.println(index + ". " + equipment);
                index++;
            }

            if (category.equals("SPELLS") &&

                    (equipment instanceof Lighting_Spells

                            || equipment instanceof Fire_Spells

                            || equipment instanceof Ice_Spells)

            ) {
                System.out.println(index + ". " + equipment);
                index++;
            }

            if (category.equals("POTIONS") && equipment instanceof Potions) {
                System.out.println(index + ". " + equipment);
                index++;
            }
        }
        System.out.println(index + ". QUIT (Q/q)");

        System.out.println(Color.ANSI_RESET);
    }
}