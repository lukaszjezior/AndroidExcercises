package luje.excercise1;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The request async task class.
 */
public class RequestAsyncTask extends AsyncTask<Void, Void, Boolean> {

    public static final String MOBICA_SITE_ADDRESS = "http://www.mobica.com/";

    private IUpdate mIUpdate;

    public RequestAsyncTask(IUpdate IUpdate) {
        mIUpdate = IUpdate;
    }

    protected Boolean doInBackground(Void... urls) {
        try {
            runRequest();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void runRequest() throws IOException {
        URL url = new URL(MOBICA_SITE_ADDRESS);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        } finally {
            urlConnection.disconnect();
        }
    }

    protected void onPostExecute(Boolean result) {
        mIUpdate.update(result);
    }
}
