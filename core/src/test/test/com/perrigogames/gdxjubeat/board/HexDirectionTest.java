package com.perrigogames.gdxjubeat.board;

import org.junit.Test;

import static com.perrigogames.gdxjubeat.board.HexDirection.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by corey on 2/15/17.
 */
public class HexDirectionTest {

    @Test
    public void translate() throws Exception {
        int baseX = 3, baseY = 5;
        BoardCoordinate coord = new BoardCoordinate(baseX, baseY);
        testCoordinateEquals(NONE.translate(coord), baseX, baseY);
        testCoordinateEquals(UP.translate(coord), baseX, baseY - 1);
        testCoordinateEquals(RIGHT_UP.translate(coord), baseX, baseY);
        testCoordinateEquals(RIGHT_DOWN.translate(coord), baseX, baseY);
        testCoordinateEquals(LEFT_UP.translate(coord, 2), baseX, baseY);
        testCoordinateEquals(LEFT_DOWN.translate(coord, 2), baseX, baseY);
    }

    @Test
    public void opposite() throws Exception {
        assertEquals(NONE, NONE.opposite());
        assertEquals(UP, DOWN.opposite());
        assertEquals(DOWN, UP.opposite());
        assertEquals(LEFT_DOWN, RIGHT_UP.opposite());
        assertEquals(LEFT_UP, RIGHT_DOWN.opposite());
        assertEquals(RIGHT_DOWN, LEFT_UP.opposite());
        assertEquals(RIGHT_UP, LEFT_DOWN.opposite());
        for (HexDirection dir : HexDirection.values()) {
            assertEquals(dir, dir.opposite().opposite());
        }
    }

    private void testCoordinateEquals(BoardCoordinate coord, int expX, int expY) {
        assertEquals(coord.x, expX);
        assertEquals(coord.y, expY);
    }
}