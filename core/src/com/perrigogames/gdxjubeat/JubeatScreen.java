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

    public static class JubeatScreenConfig {

        public float[] borderThickness = new float[] { 32, 32, 32, 32 };
        public float cellSpacing = 16;
    }

    public JubeatScreen() {
        this(new JubeatScreenConfig());
    }

    public JubeatScreen(JubeatScreenConfig config) {
        this.config = config;
        for (int x = 0; x < GRID_WIDTH; x++) {
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
                        - ((GRID_WIDTH - 1) * config.cellSpacing))
                        / GRID_WIDTH;
        for (List l : jbCells) {
            l.clear();
        }
        for (int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                final int xCell = x, yCell = y;
                final int index = (y * GRID_WIDTH) + x;
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

    public T getCell(int index) {
        return getCell(coordFromIndex(index));
    }

    public T getCell(BoardCoordinate coord) {
        return getCell(coord.x, coord.y);
    }

    public T getCell(int x, int y) {
        return jbCells.get(x).get(y);
    }

    public BoardCoordinate coordFromIndex(int index) {
        return temp.set(index % GRID_WIDTH, index / GRID_WIDTH);
    }

    public int indexFromCoord(int x, int y) {
        if (x < 0 || x >= GRID_WIDTH || y < 0 || y >= GRID_HEIGHT)
            return -1;
        return x + (y * GRID_WIDTH);
    }

    public int indexFromCoord(BoardCoordinate coord) {
        return indexFromCoord(coord.x, coord.y);
    }

    public boolean isValidIndex(int index) {
        return index >= 0 && index < size();
    }

    public boolean isValidCoordinate(int x, int y) {
        return isValidIndex(indexFromCoord(x, y));
    }

    public boolean isValidCoordinate(BoardCoordinate coord) {
        return isValidIndex(indexFromCoord(coord));
    }

    /** The number of indices this {@link JubeatScreen} can possibly address */
    public int size() {
        return GRID_WIDTH * GRID_HEIGHT;
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

    protected void populateTopPortion(Table region) {}

    @Override
    public boolean onTouch(boolean down, int index, int x, int y) {
        for (JBInputHandler handler : inputHandlers) {
            if (handler.onTouch(down, index, x, y)) return true;
        }
        return false;
    }

    protected BoardCoordinate poolCoord() {
        return Pools.obtain(BoardCoordinate.class);
    }

    protected BoardCoordinate poolCoord(int x, int y) {
        return poolCoord().set(x, y);
    }

    public void forEachCell(L.Func4<Boolean, T, Integer, Integer, Integer> block) {
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
