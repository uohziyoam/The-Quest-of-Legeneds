package board;

import board.square.Coordinate;
import board.square.Square;

/**
 * Cell
 */
public class Cell {
    private Coordinate coordinate;
    private Square currentSquare;
    private Square previousSquare;

    public Cell(Square cSquare) {
        currentSquare = cSquare;
        previousSquare = null;
    }

    public Cell(int x, int y) {
        currentSquare = null;
        previousSquare = null;
        coordinate = new Coordinate(x, y);
    }

    /**
     * @return the currentSquare
     */
    public Square getCurrentSquare() {
        return currentSquare;
    }

    /**
     * @return the previousSquare
     */
    public Square getPreviousSquare() {
        return previousSquare;
    }

    /**
     * @param currentSquare the currentSquare to set
     */
    public void setCurrentSquare(Square currentSquare) {
        this.currentSquare = currentSquare;
    }

    /**
     * @param previousSquare the previousSquare to set
     */
    public void setPreviousSquare(Square previousSquare) {
        this.previousSquare = previousSquare;
    }

    /**
     * @return the coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }
}