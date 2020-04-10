/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.board;

/**
 * A class containing a mark
 */

public class Mark {
    private String symbol;

    public Mark(String symbol) {
        this.symbol = symbol;
    }

    public void set(String m) {
        symbol = m;
    }

    @Override
    public String toString() {
        return symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Mark) {
            Mark other = (Mark) obj;
            return symbol.equals(other.toString());
        }

        return false;
    }
}