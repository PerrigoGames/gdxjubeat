package com.perrigogames.gdxjubeat.game.numbersllider;

import com.perrigogames.gdxjubeat.JubeatScreen;
import com.perrigogames.gdxjubeat.board.Direction;
import com.perrigogames.gdxjubeat.input.DirectionalJBInputHandler;
import com.perrigogames.gdxjubeat.input.DirectionalJBInputHandler.DirectionalJBInput;
import com.perrigogames.gdxjubeat.util.L;

import java.util.Random;

/**
 * Created by corey on 2/8/17.
 */
public class NumberSlider extends JubeatScreen<TextNumberCell> {

    private final TextNumberCell[][] numberCells;
    private final DirectionalJBInputHandler input;
    private final Random rand = new Random();

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
            forEachCell(new CellFunc() {

                @Override
                public Boolean invoke(TextNumberCell cell, Integer index, Integer x, Integer y) {
                    
                    return true;
                }
            });
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

    private void animateCell(int indexFrom, int indexTo) {
        TextNumberCell from = getCell(indexFrom);
        TextNumberCell to = getCell(indexTo);
        to.setValue(from.getValue());
        from.setValue(null);
    }

    private void spawnCell(Integer value) {
        getCell(rand.nextInt(size())).setValue(value);
    }
}
