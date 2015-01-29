package luje.excercise1;

/**
 * The update interface.
 */
public interface IUpdate {

    /**
     * Updates the user interface after doing some work on the other thread.
     *
     * @param result
     *          the boolean value
     *          true if request was correct
     *          false otherwise
     */
    void update(Boolean result);
}
