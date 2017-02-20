package se.snrn.combatcreatures.entities;

public enum DirectionDiagonal {
    NORTH(0, 1), NORTHEAST(1,1), EAST(1, 0), SOUTHEAST(1,-1), SOUTH(0, -1),SOUTHWEST(-1,-1), WEST(-1, 0), NORTHWEST(-1,1);

    private int x;
    private int y;

    DirectionDiagonal(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
