package com.perrigogames.gdxjubeat.util;

/** Enum value to describe directions on a standard grid board.
 * Created by corey on 12/26/16. */
public enum Direction {
    NONE("", 0, 0),
    UP("U", 0, -1),
    UP_LEFT("UL", -1, -1),
    LEFT("L", -1, 0),
    DOWN_LEFT("DL", -1, 1),
    DOWN("D", 0, 1),
    DOWN_RIGHT("DR", 1, 1),
    RIGHT("R", 1, 0),
    UP_RIGHT("UR", 1, -1);

    private static BoardCoordinate temp = new BoardCoordinate();
    private static Direction[][] relative = new Direction[3][3];

    static {
        for (Direction d : values()) {
            relative[d.x + 1][d.y + 1] = d;
        }
    }

    public static Direction from(int deltaX, int deltaY) {
        deltaX = normalize(deltaX);
        deltaY = normalize(deltaY);
        return relative[deltaX + 1][deltaY + 1];
    }

    private static int normalize(int val) {
        return (val == 0) ? 0 : val / Math.abs(val);
    }

    public final String abbreviation;
    public final int x, y;

    Direction(String abbr, int x, int y) {
        abbreviation = abbr;
        this.x = x;
        this.y = y;
    }

    public BoardCoordinate translate(BoardCoordinate origin) {
        return temp.set(origin.x + x, origin.y + y);
    }

    public BoardCoordinate translate(BoardCoordinate origin, int amount) {
        return temp.set(origin.x + (x * amount), origin.y + (y * amount));
    }
}
