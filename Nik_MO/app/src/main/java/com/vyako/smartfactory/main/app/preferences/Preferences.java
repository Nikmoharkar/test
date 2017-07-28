package com.vyako.smartfactory.main.app.preferences;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by kaushik on 25-May-17.
 * This class is used to save the data in preferences and get the data from preferences.
 */

public class Preferences {

    public static final byte VALUE_DEFAULT_DECIMAL = 0;
    private SharedPreferences preferences;
    private int PRIVATE_MODE = 0;
    public static final int KEY_NIGHT_MODE = 1;
    private SharedPreferences.Editor edit;

    /**
     * Constructor
     *
     * @param context
     */
    public Preferences(Context context) {
        preferences = context.getSharedPreferences("", PRIVATE_MODE);
        edit = preferences.edit();
    }

    /**
     * Put the String value into preferences
     *
     * @param strKey
     * @param strValue
     */
    public void putString(String strKey, String strValue) {
        edit.putString(strKey, strValue);
        edit.commit();
    }

    /**
     * Put the integer value into preferences
     *
     * @param strKey
     * @param value
     */
    public void putInt(String strKey, int value) {
        edit.putInt(strKey, value);
        edit.commit();
    }


    /**
     * Put the boolean value into preferences
     *
     * @param strKey
     * @param value
     */
    public void putBoolean(String strKey, boolean value) {
        edit.putBoolean(strKey, value);
        edit.commit();
    }


    /**
     * Put the long value into preferences
     *
     * @param strKey
     * @param value
     */
    public void putLong(String strKey, Long value) {
        edit.putLong(strKey, value);
        edit.commit();
    }

    /**
     * Put the double value into preferences
     *
     * @param strKey
     * @param value
     */
    public void putDouble(String strKey, String value) {
        edit.putString(strKey, value);
        edit.commit();
    }

    /**
     * get the string value from preferences
     *
     * @param strKey
     * @param defaultValue
     * @return
     */
    public String getString(String strKey, String defaultValue) {
        return preferences.getString(strKey, defaultValue);
    }


    /**
     * get the boolean value from preferences
     *
     * @param strKey
     * @param defaultValue
     * @return
     */
    public boolean getBoolean(String strKey, boolean defaultValue) {
        return preferences.getBoolean(strKey, defaultValue);
    }


    /**
     * get the integer value from preferences
     *
     * @param strKey
     * @param defaultValue
     * @return
     */
    public int getInt(String strKey, int defaultValue) {
        return preferences.getInt(strKey, defaultValue);
    }


    /**
     * get the double value from preferences
     *
     * @param strKey
     * @param defaultValue
     * @return
     */
    public double getDouble(String strKey, double defaultValue) {
        return Double.parseDouble(preferences.getString(strKey, ""
                + defaultValue));
    }

    /**
     * get the long value from preferences
     *
     * @param strKey
     */
    public long getLong(String strKey) {
        return preferences.getLong(strKey, VALUE_DEFAULT_DECIMAL);
    }

    /**
     * Commit the preferences after value added or remove
     */
    public void commit() {
        edit.commit();
    }

    /**
     * Remove the values from preferences
     *
     * @param strKey
     */
    public void removeFromPreference(String strKey) {
        edit.remove(strKey);
        edit.commit();
    }

    /**
     * Clear the values form preferences
     */
    public void clearPreference() {
        edit.clear();
        edit.commit();
    }

}




