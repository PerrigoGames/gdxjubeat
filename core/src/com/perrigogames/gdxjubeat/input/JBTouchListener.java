package com.perrigogames.gdxjubeat.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by corey on 2/9/17.
 */
public class JBTouchListener extends ClickListener {

    private final JBInputHandler handler;
    private final int index, x, y;

    public JBTouchListener(int index, int x, int y, JBInputHandler handler) {
        this.index = index;
        this.x = x;
        this.y = y;
        this.handler = handler;
    }

    @Override
    public boolean touchDown(InputEvent event, float xA, float yA, int pointer, int button) {
        return handler.touchDown(index, x, y);
    }

    @Override
    public void touchUp(InputEvent event, float xA, float yA, int pointer, int button) {
        handler.touchUp(index, x, y);
    }
}
