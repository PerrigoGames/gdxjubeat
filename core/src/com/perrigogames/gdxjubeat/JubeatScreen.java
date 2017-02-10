package com.perrigogames.gdxjubeat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.perrigogames.gdxjubeat.assets.A;
import com.perrigogames.gdxjubeat.input.JBInputHandler;
import com.perrigogames.gdxjubeat.input.JBTouchListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corey on 2/2/17.
 */
public class JubeatScreen extends Table implements JBInputHandler {

    public static final int GRID_WIDTH = 4, GRID_HEIGHT = 4;

    public final List<JBInputHandler> inputHandlers = new ArrayList<>();
    private final Table[][] cellActors = new Table[GRID_WIDTH][GRID_HEIGHT];
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
        A.load(A.white, Texture.class);
        A.load(A.black, Texture.class);
        A.finishLoading();
        addAction(Actions.run(new Runnable() {

            public void run() {
                createLayout();
            }
        }));
    }

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
        for (int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                final int xCell = x, yCell = y;
                final int index = (y * GRID_WIDTH) + x;
                if (cellActors[x][y] == null) {
                    cellActors[x][y] = new Table();
                }
                Table curr = cellActors[x][y];
                curr.setTouchable(Touchable.enabled);
                populateCell(curr, index, x, y);
                curr.addListener(new JBTouchListener(index, xCell, yCell, JubeatScreen.this));
                cellTable.add(curr).size(size);
            }
            cellTable.row();
        }
    }

    protected Table getTopPortion() {
        return topPortion;
    }

    protected Table getCell(int index) {
        return getCell(index / GRID_WIDTH, index % GRID_WIDTH);
    }

    protected Table getCell(int x, int y) {
        return cellActors[x][y];
    }

    protected void populateCell(Table cell, int index, int x, int y) {
        cell.setBackground(A.d(A.black));
    }

    protected void populateTopPortion(Table region) {
        region.setBackground(A.d(A.black));
    }

    @Override
    public boolean onTouch(boolean down, int index, int x, int y) {
        for (JBInputHandler handler : inputHandlers) {
            if (handler.onTouch(down, index, x, y)) return true;
        }
        return false;
    }
}
