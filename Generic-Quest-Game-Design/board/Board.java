package board;

import java.util.*;

import avatar.Hero;
import board.square.*;

public class Board {
    static int MARKET_NUMBER;
    static int OBSTACLE_NUMBER;
    static int COMMON_NUMBER;
    static int CELL_NUMBER;

    static HashSet<Integer> MARKET_COORDINATES;
    static HashSet<Integer> OBSTACLE_COORDINATES;
    static HashSet<Integer> COMMON_COORDINATES;

    final static double MARKET_PERCENTAGE = 0.3;
    final static double OBSTACLE_PERCENTAGE = 0.2;
    final static double COMMON_PERCENTAGE = 0.5;

    private Cell[][] board;

    private int row;
    private int col;

    public Board(int row, int col) {
        this.row = row;
        this.col = col;
        CELL_NUMBER = row * col;

        MARKET_NUMBER = (int) Math.round(CELL_NUMBER * MARKET_PERCENTAGE);
        OBSTACLE_NUMBER = (int) Math.round(CELL_NUMBER * OBSTACLE_PERCENTAGE);
        COMMON_NUMBER = (int) Math.round(CELL_NUMBER * COMMON_PERCENTAGE);

        MARKET_COORDINATES = new HashSet<>();
        OBSTACLE_COORDINATES = new HashSet<>();
        COMMON_COORDINATES = new HashSet<>();

        for (int i = 0; i < CELL_NUMBER; i++) {
            COMMON_COORDINATES.add(i);
        }

        initBoard();
    }

    private void initBoard() {
        board = new Cell[row][col];
        squareCoordinatesGenerator();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = new Cell(i, j);
            }
        }

        for (int coor : COMMON_COORDINATES) {
            Square cSquare = new Road();
            board[positionToX(coor)][positionToY(coor)].setCurrentSquare(cSquare);
        }

        for (int coor : MARKET_COORDINATES) {
            Square cSquare = new Market();
            board[positionToX(coor)][positionToY(coor)].setCurrentSquare(cSquare);
        }

        for (int coor : OBSTACLE_COORDINATES) {
            Square cSquare = new Obstacle();
            board[positionToX(coor)][positionToY(coor)].setCurrentSquare(cSquare);
        }

        // printBoard();
    }

    private void squareCoordinatesGenerator() {
        for (int i = 0; i < MARKET_NUMBER; i++) {
            int randomPosition = (int) getRandomElement(COMMON_COORDINATES);
            COMMON_COORDINATES.remove(randomPosition);
            MARKET_COORDINATES.add(randomPosition);
        }

        for (int i = 0; i < OBSTACLE_NUMBER; i++) {
            int randomPosition = (int) getRandomElement(COMMON_COORDINATES);
            COMMON_COORDINATES.remove(randomPosition);
            OBSTACLE_COORDINATES.add(randomPosition);
        }
    }

    public Square getPosition(Coordinate coordinate) {
        return board[coordinate.getX()][coordinate.getY()].getCurrentSquare();
    }

    public void setPosition(Hero hero, Coordinate tCoordinate, Coordinate pCoordinate) {
        if (tCoordinate.getX() < 0 || tCoordinate.getY() < 0) {
            throw new NullPointerException("INVALID POSITION (OUT OF MAP)");
        }

        if (tCoordinate.getX() >= row || tCoordinate.getY() >= col) {
            throw new NullPointerException("INVALID POSITION (OUT OF MAP)");
        }

        if (getPosition(tCoordinate) instanceof Obstacle) {
            throw new NullPointerException("INVALID POSITION (OBSTACLE)");
        }

        if (getPosition(tCoordinate) instanceof Market) {
        }

        hero.setCurLocation(tCoordinate);

        Square pSquare = getPosition(tCoordinate);
        board[tCoordinate.getX()][tCoordinate.getY()].setCurrentSquare(hero);
        board[tCoordinate.getX()][tCoordinate.getY()].setPreviousSquare(pSquare);

        if (pCoordinate == null) {
            return;
        }

        Square pSquarePSquare = board[pCoordinate.getX()][pCoordinate.getY()].getPreviousSquare();
        board[pCoordinate.getX()][pCoordinate.getY()].setCurrentSquare(pSquarePSquare);
        board[pCoordinate.getX()][pCoordinate.getY()].setPreviousSquare(null);
    }

    public void printBoard() {
        for (int i = 0; i < row; i++) {
            String seprator = "";
            String boardLine = "";
            for (int j = 0; j < col; j++) {
                seprator += "+-----";
            }

            seprator += "+";
            System.out.println(seprator);

            for (int j = 0; j < col; j++) {
                boardLine += "|  ";
                boardLine += board[i][j].getCurrentSquare();
                boardLine += "  ";
            }
            boardLine += "|";
            System.out.println(boardLine);

            if (i == row - 1) {
                System.out.println(seprator);
            }
        }
        System.out.println("");
    }

    public int positionToX(int position) {
        return position / col;
    }

    public int positionToY(int position) {
        return position % row;
    }

    /**
     * @return the cOMMON_COORDINATES
     */
    public HashSet<Integer> getCOMMON_COORDINATES() {
        return COMMON_COORDINATES;
    }

    private static int getRandomElement(Set<Integer> set) {
        Integer[] arrayNumbers = set.toArray(new Integer[set.size()]);

        Random random = new Random();

        int randomNumber = random.nextInt(set.size());

        return arrayNumbers[randomNumber];
    }

}
