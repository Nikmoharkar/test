/**
 *
 */
package com.vyako.smartfactory.main.app.factories;


import android.support.annotation.IntDef;

import com.vyako.smartfactory.main.app.controllers.ApplicationController;
import com.vyako.smartfactory.main.views.activities.HomeActivity;
import com.vyako.smartfactory.main.views.activities.LoginActivity;
import com.vyako.smartfactory.mo.views.activity.MoActivity;
import com.vyako.smartfactory.main.views.activities.SplashActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.vyako.smartfactory.main.app.factories.ViewFactory.ScreenIds.HOME_SCREEN;
import static com.vyako.smartfactory.main.app.factories.ViewFactory.ScreenIds.LOGIN_SCREEN;
import static com.vyako.smartfactory.main.app.factories.ViewFactory.ScreenIds.MO_MODULE_SCREEN;
import static com.vyako.smartfactory.main.app.factories.ViewFactory.ScreenIds.SPLASH_SCREEN;

/**
 * Created by kaushik on 01-Jun-17.
 * ViewFactory.java The Class which returns the Class (Screen) to the
 * application frame. Developer should use this class to get the reference of
 * any screen in the application. He should not create the screen by him/her
 * self
 */
public class ViewFactory {

    @Retention(RetentionPolicy.CLASS)
    @IntDef({SPLASH_SCREEN, LOGIN_SCREEN, HOME_SCREEN, MO_MODULE_SCREEN})
    public @interface ScreenIds {
        int SPLASH_SCREEN = 1000;
        int LOGIN_SCREEN = 1001;
        int HOME_SCREEN = 1002;
        int MO_MODULE_SCREEN = 1003;
    }

    /**
     * Reference of Application Controller
     */
    private ApplicationController mApplicationController = null;

    /**
     * Constructor
     */
    private ViewFactory() {
        mApplicationController = ApplicationController.getInstance();
    }

    /**
     * This function should only be used when whole application is made by
     * multiple activity.
     *
     * @param id
     * @return Activity class
     */
    public static Class getActivityClass(@ScreenIds int id) {

        switch (id) {
            case SPLASH_SCREEN: {
                return SplashActivity.class;
            }

            case LOGIN_SCREEN: {
                return LoginActivity.class;
            }

            case HOME_SCREEN: {
                return HomeActivity.class;
            }

            case MO_MODULE_SCREEN: {
                return MoActivity.class;
            }

            default:
                throw new IllegalStateException("Invalid screen id");
        }

    }


    /**
     * This function should only be used when whole application is made by
     * multiple Fragment.
     *
     * @param id
     * @return Fragment class
     */
    public static Class getFragmentClass(@ScreenIds int id) {

        switch (id) {
            //todo logic for fragments are same
//            case SPLASH_SCREEN: {
//                return SplashActivity.class;
//            }
//
//            case LOGIN_SCREEN: {
//                return LoginActivity.class;
//            }
//
//            case HOME_SCREEN: {
//                return HomeActivity.class;
//            }

            default:
                throw new IllegalStateException("Invalid Event id");

        }

    }
}
