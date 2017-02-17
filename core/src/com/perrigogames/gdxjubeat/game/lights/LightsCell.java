package com.perrigogames.gdxjubeat.game.lights;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.perrigogames.gdxjubeat.assets.A;

/**
 * Created by corey on 2/9/17.
 */
public class LightsCell extends Table {

    private Texture black, white;
    private Sprite sprite;
    private boolean active;

    public LightsCell() {
        super();
        black = A.tex(A.black);
        white = A.tex(A.white);
        setBackground(new SpriteDrawable(sprite = new Sprite(black)));
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        sprite.setTexture(active ? white : black);
    }

    public void flipActive() {
        setActive(!active);
    }
}
