package com.perrigogames.gdxjubeat.game.numbersllider;

import com.perrigogames.gdxjubeat.JubeatScreen;
import com.perrigogames.gdxjubeat.board.Direction;
import com.perrigogames.gdxjubeat.input.DirectionalJBInputHandler;
import com.perrigogames.gdxjubeat.input.DirectionalJBInputHandler.DirectionalJBInput;

/**
 * Created by corey on 2/8/17.
 */
public class NumberSlider extends JubeatScreen<TextNumberCell> {

    private final TextNumberCell[][] numberCells;
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
        numberCells = new TextNumberCell[GRID_WIDTH][GRID_HEIGHT];
    }

    public void handleDirection(Direction direction) {
        if (direction.cardinal()) {

        }
    }

    @Override
    protected TextNumberCell createCell(int index, int x, int y) {
        return new TextNumberCell();
    }

    @Override
    protected void populateCell(TextNumberCell cell, int index, int x, int y) {
        getCell(x, y).setValue(index);
    }
}
