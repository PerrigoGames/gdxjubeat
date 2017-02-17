package com.perrigogames.gdxjubeat.board;

import com.perrigogames.gdxjubeat.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.perrigogames.gdxjubeat.board.Direction.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by corey on 2/13/17.
 */
public class DirectionTest {

    private static final List<Direction> cardinal = new ArrayList<Direction>() {
        {
            add(UP);
            add(DOWN);
            add(LEFT);
            add(RIGHT);
        }
    };
    private static final ArrayList<Direction> diagonal = new ArrayList<Direction>() {
        {
            add(UP_LEFT);
            add(DOWN_LEFT);
            add(UP_RIGHT);
            add(DOWN_RIGHT);
        }
    };

    @org.junit.Test
    public void from() throws Exception {
        assertEquals(Direction.from(-1, -1), UP_LEFT);
        assertEquals(Direction.from(-1, 0), LEFT);
        assertEquals(Direction.from(-1, 1), DOWN_LEFT);
        assertEquals(Direction.from(0, -1), UP);
        assertEquals(Direction.from(0, 0), NONE);
        assertEquals(Direction.from(0, 1), DOWN);
        assertEquals(Direction.from(1, -1), UP_RIGHT);
        assertEquals(Direction.from(1, 0), RIGHT);
        assertEquals(Direction.from(1, 1), DOWN_RIGHT);
    }

    @org.junit.Test
    public void translate() throws Exception {
        int baseX = 3, baseY = 5;
        BoardCoordinate coord = new BoardCoordinate(baseX, baseY);
        testCoordinateEquals(NONE.translate(coord), baseX, baseY);
        testCoordinateEquals(DOWN.translate(coord), baseX, baseY + 1);
        testCoordinateEquals(UP_RIGHT.translate(coord), baseX + 1, baseY - 1);
        testCoordinateEquals(LEFT.translate(coord, 2), baseX - 2, baseY);
        testCoordinateEquals(DOWN_LEFT.translate(coord, 3), baseX - 3, baseY +3);
    }

    @org.junit.Test
    public void cardinal() throws Exception {
        for (Direction d : Direction.values()) {
            Log.v(d.name());
            assertEquals(cardinal.contains(d), d.cardinal());
        }
    }

    @org.junit.Test
    public void diagonal() throws Exception {
        for (Direction d : Direction.values()) {
            Log.v(d.name());
            assertEquals(diagonal.contains(d), d.diagonal());
        }
    }

    @org.junit.Test
    public void directional() throws Exception {
        for (Direction d : Direction.values()) {
            Log.v(d.name());
            assertEquals(cardinal.contains(d) || diagonal.contains(d), d.directional());
        }
    }

    @org.junit.Test
    public void opposite() throws Exception {
        assertEquals(DOWN_RIGHT, UP_LEFT.opposite());
        assertEquals(DOWN, UP.opposite());
        assertEquals(DOWN_LEFT, UP_RIGHT.opposite());
        assertEquals(RIGHT, LEFT.opposite());
        assertEquals(NONE, NONE.opposite());
        assertEquals(LEFT, RIGHT.opposite());
        assertEquals(UP_RIGHT, DOWN_LEFT.opposite());
        assertEquals(UP, DOWN.opposite());
        assertEquals(UP_LEFT, DOWN_RIGHT.opposite());
        for (Direction dir : Direction.values()) {
            assertEquals(dir, dir.opposite().opposite());
        }
    }

    private void testCoordinateEquals(BoardCoordinate coord, int expX, int expY) {
        assertEquals(coord.x, expX);
        assertEquals(coord.y, expY);
    }
}