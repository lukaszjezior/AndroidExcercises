package luje.excercise1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * The applications main activity class.
 */
public class MainActivity extends Activity implements IUpdate {

    @InjectView(R.id.testButton) Button testButton;

    private Context mContext;
    private RequestAsyncTask mRequestAsyncTask;
    private AlertDialog mLoadingAlertDialog;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mContext = getApplicationContext();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.testButton)
    public void testButtonClicked(){
        mLoadingAlertDialog = createDialog();
        mLoadingAlertDialog.show();

        mRequestAsyncTask = new RequestAsyncTask(this);
        mRequestAsyncTask.execute();
    }

    private AlertDialog createDialog() {
        mInflater = getLayoutInflater();
        AlertDialog.Builder loadingAlertDialogBuilder = new AlertDialog.Builder(this);
        loadingAlertDialogBuilder.setView(mInflater.inflate(R.layout.dialog, null));
        loadingAlertDialogBuilder.setTitle(getString(R.string.dialog_title));

        return loadingAlertDialogBuilder.create();
    }

    @Override
    public void update(Boolean result) {
        mLoadingAlertDialog.hide();
        if(result.equals(true)){
            showToast(getString(R.string.success));
        } else {
            showToast(getString(R.string.failure));
        }
    }

    private void showToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
