package board.square;

import config.Color;

public class Obstacle extends Square {
    public boolean accessible;

    public Obstacle() {
        this.accessible = false;
    }

    @Override
    public String toString() {
        return Color.ANSI_GREEN + "%" + Color.ANSI_RESET;
    }

}