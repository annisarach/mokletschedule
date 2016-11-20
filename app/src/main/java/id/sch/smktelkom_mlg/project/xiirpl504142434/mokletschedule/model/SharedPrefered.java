package id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.model;

import android.content.SharedPreferences;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import static id.sch.smktelkom_mlg.project.xiirpl504142434.mokletschedule.MainActivity.contextOfApplication;


/**
 * Created by Alie on 19/11/2016.
 */

public class SharedPrefered
{

    public static final String PREF_NAME = "ENUMERATOR_PREFERENCES";
    public static final String PREF_NAME_REMEMBER = "ENUMERATOR_REMEMBER";
    public static final int MODE = Context.MODE_PRIVATE;


    public static final String nama = "nama";
    public static final String nis = "nis";
    public static final String kelas = "kelas";
    public static final String bool = "bool";
    public static final String guru = "guru";
    public static final String kog = "kog";


    public static void writeBoolean(Context context, String key, boolean value) {
        getEditor(context).putBoolean(key, value).commit();
    }

    public static boolean readBoolean(Context context, String key,
                                      boolean defValue) {
        return getPreferences(context).getBoolean(key, defValue);
    }

    public static void writeInteger(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();

    }

    public static int readInteger(Context context, String key, int defValue) {
        return getPreferences(context).getInt(key, defValue);
    }

    public static void writeString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();

    }

    public static String readString(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }

    public static void writeLong(Context context, String key, long value) {
        getEditor(context).putLong(key, value).commit();
    }

    public static long readLong(Context context, String key, long defValue) {
        return getPreferences(context).getLong(key, defValue);
    }

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }

    public static Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    /*public static void saveData(Context con, String variable, String data)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(con);
        prefs.edit().putString(variable, data).commit();
    }

    public static String getData(Context con, String variable, String defaultValue)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(con);
        String data = prefs.getString(variable, defaultValue);
        return data;
    }*/
}
