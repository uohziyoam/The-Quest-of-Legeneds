package edu.bu.phuminw.quest.util;

import edu.bu.phuminw.quest.board.Cell;

/**
 * Specification for Object that has and can change position
 */

public interface Positionable {
    public Cell<?> getPosition();
    public void setPosition(Cell<?> newPosition);
}