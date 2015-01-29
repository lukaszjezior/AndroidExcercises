package luje.excercise2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.joda.time.DateTime;

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * The class handling main activity.
 */
public class MainActivity extends Activity implements IUpdate {

    @InjectView(R.id.firstNameEditText) EditText firstNameEditText;
    @InjectView(R.id.lastNameEditText) EditText lastNameEditText;
    @InjectView(R.id.marriedCheckBox) CheckBox marriedCheckBox;
    @InjectView(R.id.genderRadioGroup) RadioGroup genderRadioGroup;
    @InjectView(R.id.birthdayDatePicker) DatePicker birthdayDatePicker;
    @InjectView(R.id.saveButton) Button saveButton;
    @InjectView(R.id.loadButton) Button loadButton;

    private AlertDialog mWarningAlertDialog;
    private SharedPreferencesAsyncTask mSharedPreferencesAsyncTask;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mContext = getApplicationContext();
        birthdayDatePicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    }

    /**
     * The save method, that is used to validate data and save them asynchronously
     * through async task.
     */
    @OnClick(R.id.saveButton)
    public void save() {
        if (validateName(firstNameEditText.getText().toString()) &&
                validateName(lastNameEditText.getText().toString())) {
            DateTime birthDate = new DateTime()
                    .withYear(birthdayDatePicker.getYear())
                    .withMonthOfYear(birthdayDatePicker.getMonth() + 1)
                    .withDayOfMonth(birthdayDatePicker.getDayOfMonth());
            Person person = new Person(firstNameEditText.getText().toString(),
                    lastNameEditText.getText().toString(),
                    marriedCheckBox.isChecked(),
                    genderRadioGroup.getCheckedRadioButtonId(),
                    birthDate);

            mSharedPreferencesAsyncTask = new SharedPreferencesAsyncTask(this, person);
            mSharedPreferencesAsyncTask.execute();
        } else {
            mWarningAlertDialog = createWarningDialog();
            mWarningAlertDialog.show();
        }
    }

    /**
     * The load method, that is used to load data asynchronously through async task.
     */
    @OnClick(R.id.loadButton)
    public void load() {
        mSharedPreferencesAsyncTask = new SharedPreferencesAsyncTask(this);
        mSharedPreferencesAsyncTask.execute();
    }

    private AlertDialog createWarningDialog() {
        AlertDialog.Builder warningAlertDialogBuilder = new AlertDialog.Builder(this);
        warningAlertDialogBuilder.setIcon(R.drawable.warning);
        warningAlertDialogBuilder.setTitle(getString(R.string.dialog_title));
        warningAlertDialogBuilder.setMessage(getString(R.string.dialog_message));

        return warningAlertDialogBuilder.create();
    }

    private Boolean validateName(String name) {
        if (name != null && name.length() > 2) {
            return true;
        }
        return false;
    }

    @Override
    public Context getCurrentContext() {
        return mContext;
    }

    @Override
    public void updateAfterLoad(Person person) {
        firstNameEditText.setText(person.getFirstName());
        lastNameEditText.setText(person.getLastName());
        marriedCheckBox.setChecked(person.getIsMarried());
        genderRadioGroup.check(person.getGenderId());
        birthdayDatePicker.updateDate(person.getBirthDate().getYear(),
                person.getBirthDate().getMonthOfYear() - 1,
                person.getBirthDate().getDayOfMonth());
        showToast(getString(R.string.load_success));
    }

    @Override
    public void updateAfterSave(Boolean result) {
        if(result.equals(true)){
            showToast(getString(R.string.save_success));
        } else {
            showToast(getString(R.string.save_failure));
        }
    }

    private void showToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
    }

}
