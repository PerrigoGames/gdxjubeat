package com.perrigogames.gdxjubeat.game.numbersllider;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.perrigogames.gdxjubeat.JubeatScreen;
import com.perrigogames.gdxjubeat.board.Direction;
import com.perrigogames.gdxjubeat.input.DirectionalJBInputHandler;
import com.perrigogames.gdxjubeat.input.DirectionalJBInputHandler.DirectionalJBInput;
import com.perrigogames.gdxjubeat.util.Log;

/**
 * Created by corey on 2/8/17.
 */
public class NumberSlider extends JubeatScreen {

    private final NumberSliderCell[][] numberCells;
    private final DirectionalJBInputHandler input;

    public NumberSlider () {
        super();
        this.inputHandlers.add(input = new DirectionalJBInputHandler(DirectionalJBInput.FULL) {

            @Override
            public boolean onDirection(boolean down, Direction direction) {
                if (!down) return false;
                handleDirection(direction);
                return true;
            }
        });
        numberCells = new NumberSliderCell[GRID_WIDTH][GRID_HEIGHT];
    }

    public void handleDirection(Direction direction) {
        Log.v("%s", direction);
    }

    public NumberSliderCell getCell(int x, int y) {
        if (numberCells[x][y] == null) {
            numberCells[x][y] = new NumberSliderCell();
        }
        return numberCells[x][y];
    }

    @Override
    protected void populateCell(Table cell, int index, int x, int y) {
        cell.add(getCell(x, y)).expand().fill();
        getCell(x, y).setElements(index);
    }

    @Override
    protected void populateTopPortion(Table region) {
        super.populateTopPortion(region);
    }
}
