package board.square;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object obj) {
        Coordinate coor = (Coordinate) obj;

        return coor.getX() == this.x && coor.getY() == this.y;
    }
}