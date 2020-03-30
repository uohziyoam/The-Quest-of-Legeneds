package board.square;

public class Road extends Square {
    public boolean accessible;

    public Road() {
        this.accessible = true;
    }

    @Override
    public String toString() {
        return " ";
    }

    @Override
    public boolean hasMonster() {
        return Math.random() < 0.9;
    }
}