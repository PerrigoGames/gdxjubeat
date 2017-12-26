package com.perrigogames.gdxjubeat.game.lights;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Pools;
import com.perrigogames.gdxjubeat.JubeatScreen;
import com.perrigogames.gdxjubeat.assets.A;
import com.perrigogames.gdxjubeat.board.BoardCoordinate;
import com.perrigogames.gdxjubeat.input.JBInputHandler;
import com.perrigogames.gdxjubeat.util.Boxed;
import com.perrigogames.gdxjubeat.util.L;
import com.perrigogames.gdxjubeat.util.Log;

import java.util.Random;

/**
 * Created by corey on 2/8/17.
 */
public class Lights extends JubeatScreen<LightsCell> {

    private final JBInputHandler input;
    private Label moveCounter, winIndicator;
    private int moves = 0;

    public Lights() {
        super();
        this.inputHandlers.add(input = new JBInputHandler() {

            @Override
            public boolean onTouch(boolean down, int index, int x, int y) {
                if (!down)
                    return true;
                triggerCoord(x, y);
                if (isWin()) {
                    winIndicator.setText("YOU WIN");
                }
                moves++;
                updateMoveCounter();
                return true;
            }
        });
    }

    public void resetGame() {
        forEachCell(new L.Func4<Boolean, LightsCell, Integer, Integer, Integer>() {

            @Override
            public Boolean invoke(LightsCell cell, Integer index, Integer x, Integer y) {
                cell.setActive(false);
                return true;
            }
        });
        triggerRandomly(40);
        moves = 0;
        updateMoveCounter();
    }

    @Override
    public void onCreate() {
        resetGame();
    }

    @Override
    protected LightsCell createCell(int index, int x, int y) {
        return new LightsCell();
    }

    @Override
    protected void populateTopPortion(Table region) {
        super.populateTopPortion(region);
        final Label.LabelStyle style = new Label.LabelStyle(A.bFont(A.Fonts.sfBold), Color.WHITE);
        region.defaults().space(8);
        region.add(new Label("LIGHTS", style)).row();
        region.add(moveCounter = new Label("", style)).row();
        region.add(winIndicator = new Label("", style)).row();
        region.add(new Button(new Button.ButtonStyle()) {
            {
                setBackground(A.d(A.black));
                add(new Label("RESET", style));
                addListener(new ClickListener() {

                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        resetGame();
                    }
                });
            }
        }).size(80, 50);
    }

    private void triggerCoord(BoardCoordinate coord) {
        triggerCoord(coord.x, coord.y);
    }

    private void triggerCoord(int x, int y) {
        for (BoardCoordinate i : new BoardCoordinate[] {
                poolCoord(x, y),
                poolCoord(x+1, y),
                poolCoord(x-1, y),
                poolCoord(x, y+1),
                poolCoord(x, y-1)
        }) {
            if (isValidCoordinate(i)) {
                getCell(i).flipActive();
            }
            Pools.free(i);
        }
    }

    private void triggerRandomly(int times) {
        Random rand = new Random();
        BoardCoordinate temp = poolCoord();
        for (int i = 0; i < times; i++) {
            triggerCoord(temp.set(coordFromIndex(rand.nextInt(size()))));
        }
        Pools.free(temp);
    }

    private boolean isWin() {
        final Boxed<Boolean> win = new Boxed<>(true);
        forEachCell(new L.Func4<Boolean, LightsCell, Integer, Integer, Integer>() {

            @Override
            public Boolean invoke(LightsCell cell, Integer index, Integer x, Integer y) {
                if (!cell.isActive()) {
                    win.value = false;
                    return false;
                }
                return true;
            }
        });
        return win.value;
    }

    private void updateMoveCounter() {
        moveCounter.setText(String.format("Moves: %d", moves));
    }
}
