package com.perrigogames.gdxjubeat.input;

import com.perrigogames.gdxjubeat.JubeatScreen;

/** Interface for handling input from the {@link JubeatScreen} */
public interface JBInputHandler {

    /** Handles the user touching down on a particular square.
     * @param index the index of the square being touched (0 is top left,
     *              moving horizontal)
     * @param x the horizontal index, 0 being left
     * @param y the vertical index, 0 being top
     * @return whether the input event was handled */
    boolean touchDown(int index, int x, int y);

    /** Handles the user touching up on a particular square.
     * @param index the index of the square being touched (0 is top left,
     *              moving horizontal)
     * @param x the horizontal index, 0 being left
     * @param y the vertical index, 0 being top
     * @return whether the input event was handled */
    boolean touchUp(int index, int x, int y);
}