package luje.excercise2;

import android.content.Context;

/**
 * The update interface.
 */
public interface IUpdate {

    /**
     * The get current Context method.
     *
     * @return Context
     *          the current context
     */
    Context getCurrentContext();

    /**
     * Method provides a callback, allowing to update
     * user interface, after loading data is done.
     *
     * @param person
     *          the loaded person object
     */
    void updateAfterLoad(Person person);

    /**
     * Method provides a callback, allowing to update
     * user interface, after saving data is done.
     *
     * @param result
     *          the boolean value
     *          true if success, false otherwise
     */
    void updateAfterSave(Boolean result);
}
