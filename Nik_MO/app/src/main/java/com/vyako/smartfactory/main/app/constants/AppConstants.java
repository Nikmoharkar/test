package com.vyako.smartfactory.main.app.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.vyako.smartfactory.main.app.constants.AppConstants.EventIds.LAUNCH_HOME_SCREEN;
import static com.vyako.smartfactory.main.app.constants.AppConstants.EventIds.LAUNCH_LOGIN_SCREEN;
import static com.vyako.smartfactory.main.app.constants.AppConstants.EventIds.LAUNCH_MO_DETAIL_FRAGMENT_SCREEN;
import static com.vyako.smartfactory.main.app.constants.AppConstants.EventIds.LAUNCH_MO_LIST_FRAGMENT_SCREEN;
import static com.vyako.smartfactory.main.app.constants.AppConstants.EventIds.LAUNCH_MO_MODULE_SCREEN;


/**
 * Created by kaushik on 25-May-17.
 * Maintain the app level constant
 */

public interface AppConstants {

    String LOG_CAT = ">>logs : ";


    /**
     * Application Controller events ids
     * Maintain all app level event ids and def of that event ids
     */
    @Retention(RetentionPolicy.CLASS)
    @IntDef({LAUNCH_LOGIN_SCREEN, LAUNCH_HOME_SCREEN, LAUNCH_MO_MODULE_SCREEN, LAUNCH_MO_LIST_FRAGMENT_SCREEN, LAUNCH_MO_DETAIL_FRAGMENT_SCREEN})
    @interface EventIds {
        int LAUNCH_LOGIN_SCREEN = 101;
        int LAUNCH_HOME_SCREEN = 102;
        int LAUNCH_MO_MODULE_SCREEN = 103;

        int LAUNCH_MO_LIST_FRAGMENT_SCREEN = 104;
        int LAUNCH_MO_DETAIL_FRAGMENT_SCREEN = 105;
    }


    /**
     * Maintain the PREFERENCES Keys and constant
     */
    interface PREFERENCES {
        String IS_LAUNCH_HOME_SCREEN = "isLaunchHomeScreen";
        String KEY_PREF_X_AUTH_TOKEN = "X-AUTH-TOKEN";

        String KEY_HOST_URL = "hostUrl";
    }


}
