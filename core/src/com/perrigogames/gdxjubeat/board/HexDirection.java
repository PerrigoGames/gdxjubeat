package com.perrigogames.gdxjubeat.board;

/** Enum value to describe directions on a hexagonal grid board.
 * Created by corey on 1/3/17. */
public enum HexDirection implements GenericDirection {
    NONE("", 0, 0, 0),
    UP("U", 0, 1, 1),
    LEFT_UP("LU", -1, 1, 0),
    LEFT_DOWN("LD", -1, 0, 1),
    DOWN("D", 0, -1, -1),
    RIGHT_DOWN("RD", 1, 0, 1),
    RIGHT_UP("RU", 1, 1, 0);

    private static BoardCoordinate temp = new BoardCoordinate();

    public final String abbreviation;
    public final int x, yOdd, yEven;

    HexDirection(String abbr, int x, int yOdd, int yEven) {
        abbreviation = abbr;
        this.x = x;
        this.yOdd = yOdd;
        this.yEven = yEven;
    }

    public BoardCoordinate translate(BoardCoordinate origin) {
        return temp.set(origin.x + x, origin.y + ((origin.y % 2 == 0) ? yEven : yOdd));
    }

    public BoardCoordinate translate(BoardCoordinate origin, int amount) {
        temp.set(origin);
        for (int i = 0; i < amount; i++) {
            translate(temp);
        }
        return temp;
    }

    @Override
    public HexDirection opposite () {
        switch(this) {
            case NONE:       return NONE;
            case UP:         return DOWN;
            case LEFT_UP:    return RIGHT_DOWN;
            case LEFT_DOWN:  return RIGHT_UP;
            case DOWN:       return UP;
            case RIGHT_DOWN: return LEFT_UP;
            case RIGHT_UP:   return LEFT_DOWN;
            default:         return NONE;
        }
    }
}
