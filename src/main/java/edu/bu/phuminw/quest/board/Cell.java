/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.board;

import edu.bu.phuminw.quest.Quest;

/**
 * A individual cell of a board
 * 
 * @param <T> Object a cell holds
 */

public class Cell<T> {
    private T occupier;
    private Mark mark;
    private String type;

    public Cell(T occ, Mark m) {
        if (occupier == null && m == null) {
            clear();
        } else {
            occupier = occ;
            mark = m;
            type = null;
        }
    }

    public Cell() {
        this(null, null);
    }

    public void clear() {
        occupier = null;
        mark = new Mark("");
        type = null;
    }

    public boolean set(T occ, Mark m) {
        if (occ == null && m == null) {
            clear();
            return false;
        } else {
            occupier = occ;
            mark = m;
            return true;
        }
    }

    public void setType(String t) {
        type = t;
    }

    public T getOccipier() {
        return occupier;
    }

    public Mark getMark() {
        return mark;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        // String boarderMarker = switch (type) {
        // case Quest.NEXUS-> "N";
        // case Quest.FORBIDDEN-> "I";
        // case Quest.BUSH-> "B";
        // default-> "P";
        // };

        return String.format("%1$s - %1$s - %1$s\n| %2$5s |\n%1$s - %1$s - %1$s", switch (type) {
            case Quest.HERO_NEXUS -> "N";
            case Quest.FORBIDDEN -> "I";
            case Quest.BUSH -> "B";
            case Quest.PLAIN -> "P";
            case Quest.CAVE -> "C";
            default -> "P";
        }, mark);
    }

    public boolean isOccupied() {
        return (this.occupier != null) || (this.mark != null);
    }
}