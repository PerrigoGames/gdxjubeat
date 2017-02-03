package com.perrigogames.gdxjubeat.util;

import com.badlogic.gdx.InputProcessor;
import com.perrigogames.gdxjubeat.util.Log;

/**
 * Created by corey on 2/3/17.
 */
public class InputLogger implements InputProcessor {

    public InputProcessor real;
    public boolean logKeyDown, logKeyUp, logKeyTyped,
            logTouchDown, logTouchUp, logTouchDragged,
            logMouseMoved, logScrolled;

    public InputLogger (InputProcessor real) {
        this.real = real;
    }

    public void setLogKeys (boolean log) {
        logKeyDown = logKeyUp = logKeyTyped = log;
    }

    public void setLogTouches (boolean log) {
        logTouchDown = logTouchUp = logTouchDragged = log;
    }

    public void setLogMouse (boolean log) {
        logMouseMoved = logScrolled = log;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (logKeyDown)
            Log.v("keyDown: %d", keycode);
        return real != null && real.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (logKeyUp)
            Log.v("keyUp: %d", keycode);
        return real != null && real.keyUp(keycode);
    }

    @Override
    public boolean keyTyped(char character) {
        if (logKeyTyped)
            Log.v("keyTyped: %s", character);
        return real != null && real.keyTyped(character);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (logTouchDown)
            Log.v("touchDown: x:%d, y:%d, pointer:%d, button:%d", screenX, screenY, pointer, button);
        return real != null && real.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (logTouchUp)
            Log.v("touchUp: x:%d, y:%d, pointer:%d, button:%d", screenX, screenY, pointer, button);
        return real != null && real.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (logTouchDragged)
            Log.v("touchDragged: x:%d, y:%d, pointer:%d", screenX, screenY, pointer);
        return real != null && real.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if (logMouseMoved)
            Log.v("mouseMoved: x:%d, y:%d", screenX, screenY);
        return real != null && real.mouseMoved(screenX, screenY);
    }

    @Override
    public boolean scrolled(int amount) {
        if (logScrolled)
            Log.v("scrolled: %d", amount);
        return real != null && real.scrolled(amount);
    }
}
