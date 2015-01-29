package luje.excercise2;

import android.os.AsyncTask;
import android.util.Log;

import org.joda.time.DateTime;

/**
 * The shared preferences async task class.
 */
public class SharedPreferencesAsyncTask extends AsyncTask<Void, Void, Object> {

    private Boolean mWriteMode;
    private IUpdate mIUpdate;

    private Person mPerson;

    /**
     * Instantiates a new SharedPreferencesAsyncTask in load mode.
     *
     * @param iUpdate the instance of class implementing
     *                 IUpdate interface
     */
    public SharedPreferencesAsyncTask(IUpdate iUpdate) {
        this.mWriteMode = false;
        this.mIUpdate = iUpdate;
    }

    /**
     * Instantiates a new SharedPreferencesAsyncTask in save mode.
     *
     * @param iUpdate the instance of class implementing
     *                 IUpdate interface
     * @param person  the person object, that contains
     *                 data to save
     */
    public SharedPreferencesAsyncTask(IUpdate iUpdate, Person person) {
        this(iUpdate);
        this.mWriteMode = true;
        this.mPerson = person;
    }

    @Override
    protected Object doInBackground(Void... params) {
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(mIUpdate.getCurrentContext());
        if (mWriteMode.equals(true)) {
            return sharedPreferencesManager.savePerson(mPerson);
        }

        return sharedPreferencesManager.loadPerson();
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        if (mWriteMode.equals(true)) {
            mIUpdate.updateAfterSave((Boolean) result);
        } else {
            mIUpdate.updateAfterLoad((Person) result);
        }
    }

}
