package com.perrigogames.gdxjubeat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.perrigogames.gdxjubeat.assets.A;
import com.perrigogames.gdxjubeat.board.BoardCoordinate;
import com.perrigogames.gdxjubeat.game.numbersllider.TextNumberCell;
import com.perrigogames.gdxjubeat.input.JBInputHandler;
import com.perrigogames.gdxjubeat.input.JBTouchListener;
import com.perrigogames.gdxjubeat.util.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corey on 2/2/17.
 */
public abstract class JubeatScreen<T extends Actor> extends Table implements JBInputHandler {

    public static final int GRID_WIDTH = 4, GRID_HEIGHT = 4;
    private static final BoardCoordinate temp = new BoardCoordinate();

    public final List<JBInputHandler> inputHandlers = new ArrayList<>();
    private final List<List<T>> jbCells = new ArrayList<>();
    private final Table topPortion = new Table();
    private final Table cellTable = new Table();
    private final Table contentTable = new Table();
    private final Group animations = new Group();
    private final JubeatScreenConfig config;

    /** Shorthand for the {@link L.Func4} that's commonly passed to the
     * board's cell iterator. */
    public interface CellFunc extends L.Func4<Boolean, TextNumberCell, Integer, Integer, Integer>;

    public static class JubeatScreenConfig {

        public float[] borderThickness = new float[] { 32, 32, 32, 32 };
        public float cellSpacing = 16;
        public int gridWidth = GRID_WIDTH, gridHeight = GRID_HEIGHT;
    }

    public JubeatScreen() {
        this(new JubeatScreenConfig());
    }

    public JubeatScreen(JubeatScreenConfig config) {
        this.config = config;
        for (int x = 0; x < config.gridWidth; x++) {
            jbCells.add(new ArrayList<T>());
        }
        A.load(A.white, Texture.class);
        A.load(A.black, Texture.class);
        A.Fonts.loadFonts();
        A.finishLoading();

        Pools.set(BoardCoordinate.class, new Pool<BoardCoordinate>() {

            @Override
            protected BoardCoordinate newObject() {
                return new BoardCoordinate();
            }
        });

        addAction(Actions.run(new Runnable() {

            public void run() {
                createLayout();
                onCreate();
            }
        }));
    }

    /** Invoked after the layout has been successfully created. Do size-dependent
     * operations here. */
    public void onCreate() {}

    public void createLayout() {
        clear();
        contentTable.clear();
        animations.clear();
        cellTable.clear();

        stack(contentTable, animations).expand().fill();
        animations.setTouchable(Touchable.disabled);
        contentTable.add(topPortion).expand().fill().row();
        contentTable.add(cellTable).expandX().pad(
                config.borderThickness[0],
                config.borderThickness[1],
                config.borderThickness[2],
                config.borderThickness[3]);

        populateTopPortion(topPortion);
        cellTable.defaults().space(config.cellSpacing);
        int size = (int) (GdxJubeat.VIEWPORT_WIDTH
                        - config.borderThickness[1]
                        - config.borderThickness[3]
                        - ((config.gridWidth - 1) * config.cellSpacing))
                        / config.gridWidth;
        for (List l : jbCells) {
            l.clear();
        }
        for (int y = 0; y < config.gridHeight; y++) {
            for (int x = 0; x < config.gridWidth; x++) {
                final int xCell = x, yCell = y;
                final int index = (y * config.gridWidth) + x;
                jbCells.get(x).add(createCell(index, x, y));
                T curr = jbCells.get(x).get(y);
                curr.setTouchable(Touchable.enabled);
                populateCell(curr, index, x, y);
                curr.addListener(new JBTouchListener(index, xCell, yCell, JubeatScreen.this));
                cellTable.add(curr).size(size);
            }
            cellTable.row();
        }
    }

    /** Finds and returns the cell at the specified index */
    public T getCell(int index) {
        return getCell(coordFromIndex(index));
    }

    /** Finds and returns the cell at the specified {@link BoardCoordinate} */
    public T getCell(BoardCoordinate coord) {
        return getCell(coord.x, coord.y);
    }

    /** Finds and returns the cell at the specified x- and y-coordinates */
    public T getCell(int x, int y) {
        return jbCells.get(x).get(y);
    }

    /** Converts an index value to its corresponding {@link BoardCoordinate}
     * in the context of this board
     * @return the converted {@link BoardCoordinate}, or null if the index
     * doesn't properly refer to a cell */
    public BoardCoordinate coordFromIndex(int index) {
        if (!isValidIndex(index))
            return null;
        return temp.set(index % config.gridWidth, index / config.gridWidth);
    }

    /** Converts a set of x- and y-coordinates into the index that represents
     * it in the context of this board.
     * @return the index of the supplied coordinates, or -1 if any of the
     * supplied parameters are out of bounds for this board */
    public int indexFromCoord(int x, int y) {
        if (x < 0 || x >= config.gridWidth || y < 0 || y >= config.gridHeight)
            return -1;
        return x + (y * config.gridWidth);
    }

    /** Converts a {@link BoardCoordinate} into the index that represents
     * it in the context of this board.
     * @return the index of the supplied coordinates, or -1 if any of the
     * supplied parameters are out of bounds for this board */
    public int indexFromCoord(BoardCoordinate coord) {
        return indexFromCoord(coord.x, coord.y);
    }

    /** Calculates whether a particular index is valid in the context of
     * this board */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < size();
    }

    /** Calculates whether a particular set of coordinates is valid in the
     * context of this board */
    public boolean isValidCoordinate(int x, int y) {
        return isValidIndex(indexFromCoord(x, y));
    }

    /** Calculates whether a particular set of coordinates is valid in the
     * context of this board */
    public boolean isValidCoordinate(BoardCoordinate coord) {
        return isValidIndex(indexFromCoord(coord));
    }

    /** The number of indices this {@link JubeatScreen} can possibly address */
    public int size() {
        return config.gridWidth * config.gridHeight;
    }

    /** Creates a cell, to be implemented by subclasses. {@link JubeatScreen}
     * will provide the newly-created cell's index and x,y-coordinates.
     * @return the newly-created cell */
    protected abstract T createCell(int index, int x, int y);

    /** Called after the cell has been created and added to the group. Put code
     * dependent on the actor being the right size in here. {@link JubeatScreen}
     * will provide the newly-created cell's index and x,y-coordinates.
     * @param cell the cell that's being populated */
    protected void populateCell(T cell, int index, int x, int y) {}

    /** Called after the top portion of the screen has been created and added to
     * the group. Put code dependent on the actor being the right size in here.
     * @param top the {@link Table} containing the top part of the screen */
    protected void populateTopPortion(Table top) {}

    @Override
    public boolean onTouch(boolean down, int index, int x, int y) {
        for (JBInputHandler handler : inputHandlers) {
            if (handler.onTouch(down, index, x, y)) return true;
        }
        return false;
    }

    /** Shorthand for getting a {@link BoardCoordinate} from the app-wide pool.
     * Remember to call {@link Pools#free(Object)} when done to return it to
     * the pool. */
    protected BoardCoordinate poolCoord() {
        return Pools.obtain(BoardCoordinate.class);
    }

    /** Shorthand for getting a {@link BoardCoordinate} from the app-wide pool,
     * setting its coordinate values. at the same time. Remember to call
     * {@link Pools#free(Object)} when done to return it to the pool. */
    protected BoardCoordinate poolCoord(int x, int y) {
        return poolCoord().set(x, y);
    }

    /** Shorthand function to iterate through all of the cells in the board.
     * @param block Block function to execute on each cell. The board will pass
     *              in (in order) the cell itself, the cell's index, and it's x-
     *              and y- coordinates. The block will return whether the iterator
     *              should continue to iterate or not. */
    public void forEachCell(CellFunc block) {
        BoardCoordinate temp = poolCoord();
        for (int idx = 0; idx < size(); idx++) {
            temp.set(coordFromIndex(idx));
            Boolean cont = L.$(block, getCell(temp), idx, temp.x, temp.y);
            if (cont == null || !cont.booleanValue()) {
                break;
            }
        }
        Pools.free(temp);
    }
}
