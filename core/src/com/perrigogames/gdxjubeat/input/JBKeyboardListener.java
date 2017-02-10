package com.perrigogames.gdxjubeat.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.perrigogames.gdxjubeat.JubeatScreen;

/**
 * Created by corey on 2/9/17.
 */
public class JBKeyboardListener extends InputListener {

    private final JBInputHandler handler;

    public JBKeyboardListener(JBInputHandler handler) {
        this.handler = handler;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        boolean handled = handleKey(keyIndex(keycode), true);
        return handled ? handled : super.keyDown(event, keycode);
    }

    @Override
    public boolean keyUp(InputEvent event, int keycode) {
        boolean handled = handleKey(keyIndex(keycode), false);
        return handled ? handled : super.keyUp(event, keycode);
    }

    private boolean handleKey(int index, boolean down) {
        if (index != -1) {
            return handler.onTouch(down, index,
                    index % JubeatScreen.GRID_WIDTH,
                    index / JubeatScreen.GRID_WIDTH);
        }
        return false;
    }

    private int keyIndex(int keycode) {
        switch(keycode) { //FIXME: this won't work on a different grid size
            case Input.Keys.NUM_1: return 0;
            case Input.Keys.NUM_2: return 1;
            case Input.Keys.NUM_3: return 2;
            case Input.Keys.NUM_4: return 3;
            case Input.Keys.Q: return 4;
            case Input.Keys.W: return 5;
            case Input.Keys.E: return 6;
            case Input.Keys.R: return 7;
            case Input.Keys.A: return 8;
            case Input.Keys.S: return 9;
            case Input.Keys.D: return 10;
            case Input.Keys.F: return 11;
            case Input.Keys.Z: return 12;
            case Input.Keys.X: return 13;
            case Input.Keys.C: return 14;
            case Input.Keys.V: return 15;
            default: return -1;
        }
    }
}
