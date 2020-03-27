/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.board;

/**
 * A individual cell of a board
 * 
 * @param <T> Object a cell holds
 */

public class Cell<T> {
    private T occupier;
    private Mark mark;

    public Cell(T occ, Mark m) {
        if (occupier == null && m == null) {
            clear();
        }
        else {
            occupier = occ;
            mark = m;
        }
    }

    public Cell() {
        this(null, null);
    }

    public void clear() {
        occupier = null;
        mark = new Mark("");
    }

    public boolean set(T occ, Mark m) {
        if (occ == null && m == null) {
            clear();
            return false;
        }
        else {
            occupier = occ;
            mark = m;
            return true;
        }
    }

    public T getOccipier() {
        return occupier;
    }

    public Mark getMark() {
        return mark;
    }

    @Override
    public String toString() {
        if (occupier == null && mark == null)
            return "Empty cell";
        return "Occupier: " + occupier + " with mark " + mark;
    }

    public boolean isOccupied() {
        return (this.occupier != null) || (this.mark != null);
    }
}
