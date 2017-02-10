package com.perrigogames.gdxjubeat.input;

import java.util.HashMap;

/** Implementation of JBInputHandler that allows robust configuration
 * of a multiplayer control scheme in which multiple players share the
 * grid of inputs.
 *
 * This class functions differently than standard input, which provided
 * not only an index, but also x- and y-coordinates corresponding to the
 * physical position of the button.  In a multiplayer control scheme, the
 * input handler is only provided an index and a player number. It's
 * understood that an index on one player's side should have the same effect
 * on another player when the same index is passed to that player.  This
 * is not a strict requirement, as implementations can configure how they
 * interpret the indices however they want (especially in the case of non-
 * symmetrical gameplay).
 *
 * Created by corey on 2/10/17. */
/** FIXME: this class mixes keycodes and standard JB input, should probably
 *  FIXME: only deal with keycodes, possibly hybridize */
public abstract class MultiplayerJBInputHandler implements JBInputHandler {

    /** Small data class to hold information spatially about a
     * particular key, namely the player it's associated with and
     * the index that should be passed to the input handler. */
    private static class MultiplayerJBInputKey {
        /** The player the key belongs to */
        int player;
        /** The index that should be passed to the listener */
        int index;
    }

    private int players;
    private HashMap<Integer, MultiplayerJBInputKey> keymap;

    public MultiplayerJBInputHandler() {
        this.players = numberOfPlayers();
        for (int i = 0; i < players; i++) {

        }
    }

    @Override
    public boolean onTouch(boolean down, int index, int x, int y) {
        return false;
    }

    /** Creates an ordered list of keycodes that correspond to this
     * player's inputs. The order designates the index that will be
     * stored and passed to the input handler. The index passed will
     * always be equal to the index that keycode had in the array.
     *
     * @param player the player that is expecting keycodes
     * @return an ordered array of keycodes for the specified player */
    protected abstract int[] keycodesForPlayer(int player);

    /** @return the number of players this input method supports */
    protected abstract int numberOfPlayers();
}
