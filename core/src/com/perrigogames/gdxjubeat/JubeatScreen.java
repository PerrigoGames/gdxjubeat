package com.perrigogames.gdxjubeat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by corey on 2/2/17.
 */
public class JubeatScreen extends Table {

    public static final int GRID_WIDTH = 4, GRID_HEIGHT = 4;

    private final Table[][] cellActors = new Table[GRID_WIDTH][GRID_HEIGHT];
    private final Table topPortion = new Table();
    private final Table cellTable = new Table();
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
        createLayout();
    }

    public void createLayout() {
        clear();
        cellTable.clear();

        add(topPortion).expand().fill();
        add(cellTable).expandX().bottom().pad(
                config.borderThickness[0],
                config.borderThickness[1],
                config.borderThickness[2],
                config.borderThickness[3]);

        cellTable.defaults().space(config.cellSpacing);
        Texture tex = new Texture("badlogic.jpg");
        topPortion.add(new Image(tex)).expand().fill();
        int size = (int) (GdxJubeat.VIEWPORT_WIDTH
                        - config.borderThickness[1]
                        - config.borderThickness[3]
                        - ((GRID_WIDTH - 1) * config.cellSpacing))
                        / GRID_WIDTH;
        for (int x = 0; x < GRID_WIDTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                if (cellActors[x][y] == null) {
                    cellActors[x][y] = new Table();
                }
                cellActors[x][y].add(new Image(tex)).expand().fill();
                cellTable.add(cellActors[x][y]).size(size);
            }
            cellTable.row();
        }
    }
}
