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

    public String get() {
        return symbol;
    }

    public void set(String m) {
        symbol = m;
    }
}