package com.perrigogames.gdxjubeat.input;

import com.perrigogames.gdxjubeat.board.Direction;

import static com.perrigogames.gdxjubeat.JubeatScreen.GRID_HEIGHT;
import static com.perrigogames.gdxjubeat.JubeatScreen.GRID_WIDTH;
import static com.perrigogames.gdxjubeat.util.L.Func3;

/** A {@link JBInputHandler} that converts controller input from coordinates and
 * an index into a direction, based on the relative location of the button pressed.
 * Created by corey on 2/9/17. */
public abstract class DirectionalJBInputHandler implements JBInputHandler {

    /** Shorthand for the format of function used in {@link DirectionalJBInput} */
    private interface JBDirectionFunc extends Func3<Direction, Integer, Integer, Integer> {}

    /** Enum describing the different {@link Direction} calculation methods that can
     * be used by {@link DirectionalJBInputHandler} */
    public enum DirectionalJBInput {
        /** Full 8-direction mode, based on edges.
         * Tapping on any of the one-tile-wide edges will trigger that direction.
         * The corners will produce diagonal directions.
         * The center 2x2 square produces CENTER */
        FULL(new JBDirectionFunc() {

            @Override
            public Direction invoke(Integer index, Integer x, Integer y) {
                int xDel = (x == 0) ? -1 : (x == GRID_WIDTH - 1) ? 1 : 0;
                int yDel = (y == 0) ? -1 : (y == GRID_HEIGHT - 1) ? 1 : 0;
                return Direction.from(xDel, yDel);
            }
        }),
        /** Diagonal directions only, based on corners.
         * Each 2x2 corner represents the direction that corner is relative to the
         * other three. */
        CORNER(new JBDirectionFunc() {

            @Override
            public Direction invoke(Integer index, Integer x, Integer y) {
                int xDel = (x < GRID_WIDTH / 2) ? -1 : 1;
                int yDel = (y < GRID_HEIGHT / 2) ? -1 : 1;
                return Direction.from(xDel, yDel);
            }
        }); // 4 directions

        private JBDirectionFunc handler;

        DirectionalJBInput(JBDirectionFunc handler) {
            this.handler = handler;
        }

        public Direction directionForCell(int index, int x, int y) {
            return handler.invoke(index, x, y);
        }
    }

    private DirectionalJBInput type;

    public DirectionalJBInputHandler(DirectionalJBInput type) {
        this.type = type;
    }

    @Override
    public boolean onTouch(boolean down, int index, int x, int y) {
        return onDirection(down, type.directionForCell(index, x, y));
    }

    public abstract boolean onDirection(boolean down, Direction direction);
}
