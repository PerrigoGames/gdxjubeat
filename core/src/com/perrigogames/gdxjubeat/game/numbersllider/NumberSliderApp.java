package com.perrigogames.gdxjubeat.game.numbersllider;

import com.badlogic.gdx.graphics.Color;
import com.perrigogames.gdxjubeat.GdxJubeat;
import com.perrigogames.gdxjubeat.JubeatScreen;

/**
 * Created by corey on 2/8/17.
 */
public class NumberSliderApp extends GdxJubeat {

    @Override
    protected JubeatScreen createScreen() {
        return new NumberSlider();
    }

    @Override
    protected Color getBackgroundColor() {
        return new Color(0.3f, 0.3f, 0.3f, 1f);
    }
}
