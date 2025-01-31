/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.board;

import java.util.ArrayList;

import edu.bu.phuminw.quest.Quest;
import edu.bu.phuminw.quest.util.Creature;


/**
 * A board comprising of cells
 * 
 * @param <T> Object each cell holds
 */

public class Board<T extends Creature> {
    ArrayList<ArrayList<Cell<T>>> board;
    int occupied;

    public Board(int row, int col) {
        board = new ArrayList<ArrayList<Cell<T>>>(row);

        for (int i = 0; i < row; i++) {
            board.add(new ArrayList<Cell<T>>(col));
            for (int j = 0; j < col; j++) {
                board.get(i).add(new Cell<T>((col*i)+j+1));
            }
        }

        occupied = 0;
    }

    public Board(int size) {
        this(size, size);
    }

    /**
     * Convert given position to Cartesian coordinate with (0,0) at the top-left
     * 
     * @param pos Numeric position
     * @return Cartesiam coordinate or (-1,-1) if give position is not valid
     */

    private int[] posLookup(int pos) {
        return (1 <= pos && pos <= board.size() * board.get(0).size())
                ? new int[] { (pos - 1) / board.get(0).size(), (pos - 1) % board.get(0).size() }
                : new int[] { -1, -1 };
    }

    /**
     * Check whether give position is in the board
     * 
     * @param pos Numeric position
     * @return boolean represents the result
     */

    public boolean isInBoard(int pos) {
        return posLookup(pos)[0] != -1 && posLookup(pos)[1] != -1;
    }

    /**
     * Check whether in the same row and not moving to forbidden
     * 
     * @param oldPos Numeric old position
     * @param newPos Numeric new position
     * @return boolean represents the result
     */

    public boolean isSameRow(int oldPos, int newPos) {
        int[] boardSize = new int[] {board.size(), board.get(0).size()};
        return newPos <= boardSize[0] * boardSize[1] && newPos >= 1
                && ((newPos + boardSize[1] - 1) / boardSize[1] == (oldPos + boardSize[1] - 1) / boardSize[1] // Same row
                && !getCell(newPos).getType().equals(Quest.FORBIDDEN) // Not moving to forbidden
                );
    }

    /**
     * Move object across cells. Caller must check for validity regarding game rules.
     * 
     * @param from Source position
     * @param to Destination position
     * @return {@code true} if success; otherwise, {@code false}
     */

    public boolean move(int from, int to) {
        Cell<T> dest = getCell(to);

        // If dest is free
        if ( dest.getOccipier() == null && dest.getMark().toString().equals("")) {
            Cell<T> src = getCell(from);

            // Modify board cells
            dest.set(src.getOccipier(), src.getMark()); // Copy into dest
            src.set(null, new Mark("")); // Nulltify src

            // Inform moving creature
            dest.getOccipier().setPosition(dest);
            return true;
        }

        return false;
    }

    public int[] getSize() {
        return new int[] { board.size(), board.get(0).size() };
    }

    public int getOccupied() {
        return occupied;
    }

    /**
     * Getter for cells
     * 
     * @param pos Numeric position
     * @return Requested cell or null if invalid request
     */

    public Cell<T> getCell(int pos) {
        int[] position = posLookup(pos);

        if (position[0] == -1)
            return null;

        return board.get(position[0]).get(position[1]);
    }

    public Cell<T> getCell(int x, int y) {
        return board.get(x).get(y);
    }

    public boolean isFull() {
        return occupied == board.size() * board.get(0).size();
    }

    public void reset() {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                board.get(i).set(j, new Cell<T>((board.get(i).size()*i)+j+1));
            }
        }

        occupied = 0;
    }

    /**
     * Print board in the nice format
     * 
     * @param help Flag to print numeric position instead of mark
     */
    // @SuppressWarnings({ "unused", "resource" })

    public void print(boolean help) {
        System.out.print("\n\n");

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                System.out.format("%1$s - %1$s - %1$s  ", board.get(i).get(j).getType());
            }
            System.out.println();
            for (int j = 0; j < board.get(i).size(); j++) {
                System.out.format("| %1$5s |  ", (help && board.get(i).get(j).getMark().toString().equals("")) ? (i*board.get(i).size()+j+1) : board.get(i).get(j).getMark());
            }
            System.out.println();
            for (int j = 0; j < board.get(i).size(); j++) {
                System.out.format("%1$s - %1$s - %1$s  ", board.get(i).get(j).getType());
            }
            System.out.println("\n");
        }

        System.out.println();
    }

    /**
     * Place an object with a mark into a cell
     * 
     * @param t    Object to place
     * @param mark Mark associated with the object
     * @param pos  Numeric position
     * @return Either place was succeed
     */

    public boolean place(T t, Mark mark, int pos) {
        int[] position = posLookup(pos);

        if (position[0] == -1 || position[1] == -1) {
            System.out.println("Invalid position");
            return false;
        } else if (!board.get(position[0]).get(position[1]).isOccupied()) {
            // This position is free
            board.get(position[0]).get(position[1]).set(t, mark);
            occupied++;
            return true;
        } else {
            // This position is occupied
            System.out.println("This position is not free");
            return false;
        }
    }
}
