package com.perrigogames.gdxjubeat.game.numbersllider;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.perrigogames.gdxjubeat.assets.A;

/**
 * Created by corey on 2/9/17.
 */
public class NumberSliderCell extends Table {

    private Texture texture;

    public NumberSliderCell() {
        super();
        texture = A.tex(A.white);
        defaults().space(4);
    }

    public void setElements(int elements) {
        clear();
        int squareSize = 0;
        for (; Math.pow(squareSize, 2) < elements; squareSize++) {}
        for (int i = 0; i < elements; i++) {
            if (i % squareSize == 0) {
                row();
            }
            add(new Image(texture)).size(8);
        }
    }
}
