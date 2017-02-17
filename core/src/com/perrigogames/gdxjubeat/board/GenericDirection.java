package com.perrigogames.gdxjubeat.board;

/** Interface to describe a generic direction.
 * Created by corey on 2/15/17. */
public interface GenericDirection {

    /** Provided an origin coordinate, translates the coordinate
     *  in this direction by one unit.
     * @param origin the origin coordinate to start from
     * @return a TEMPORARY {@link BoardCoordinate} with the properly
     * translated coordinates */
    BoardCoordinate translate(BoardCoordinate origin);

    /** Provided an origin coordinate, translates the coordinate
     *  in this direction.
     * @param origin the origin coordinate to start from
     * @param amount the magnitude of the translation
     * @return a TEMPORARY {@link BoardCoordinate} with the properly
     * translated coordinates */
    BoardCoordinate translate(BoardCoordinate origin, int amount);

    /** Returns the opposite of this direction
     * @param <T> the class to return, must be identical to the
     * implementing class or tests will fail
     * @return the opposite direction to this one */
    <T extends GenericDirection> T opposite ();
}
