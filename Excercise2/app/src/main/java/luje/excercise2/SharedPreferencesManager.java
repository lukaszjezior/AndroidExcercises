package luje.excercise2;

import android.content.Context;
import android.content.SharedPreferences;

import org.joda.time.DateTime;

import java.util.Map;

/**
 * The shared preferences manager class.
 */
public class SharedPreferencesManager {
    private SharedPreferences mShPref;
    private static final String PREFERENCES_NAME = "PERSON_DATA";

    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String IS_MARRIED = "IS_MARRIED";
    public static final String GENDER_ID = "GENDER_ID";
    public static final String BIRTH_DATE = "BIRTH_DATE";

    /**
     * Instantiates a new SharedPreferencesManager.
     *
     * @param context
     *          the context object
     */
    public SharedPreferencesManager(Context context) {
        mShPref = context.getSharedPreferences(PREFERENCES_NAME, 0);
    }

    /**
     * The method saves passed person to shared preferences.
     *
     * @param personToSave
     *          the person to save
     * @return the boolean value
     *          true if saved successfully, false otherwise
     */
    public Boolean savePerson(Person personToSave) {
        return mShPref.edit().putString(FIRST_NAME, personToSave.getFirstName())
                .putString(LAST_NAME, personToSave.getLastName())
                .putBoolean(IS_MARRIED, personToSave.getIsMarried())
                .putInt(GENDER_ID, personToSave.getGenderId())
                .putLong(BIRTH_DATE, personToSave.getBirthDate().getMillis()).commit();
    }

    /**
     * The method to load person from shared preferences.
     *
     * @return Person
     *          the Person object, containing loaded data
     */
    public Person loadPerson() {
        DateTime birthDate = new DateTime(mShPref.getLong(BIRTH_DATE, DateTime.now().getMillis()));

        Person person = new Person(mShPref.getString(FIRST_NAME, ""),
                mShPref.getString(LAST_NAME, ""), mShPref.getBoolean(IS_MARRIED, false),
                mShPref.getInt(GENDER_ID, 0), birthDate);

        return person;
    }

}
