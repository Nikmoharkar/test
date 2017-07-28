/**
 *
 */
package com.vyako.smartfactory.main.app.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.vyako.smartfactory.main.app.factories.ViewFactory;


/**
 * UIController.java
 * <p>
 * The UIController Class, which helps for add and remove the View from the Device Screen
 */

public class UiController {

    /**
     * private sInstance of UIControllers for singleton Design Pattern
     */
    private static UiController sInstance;


    /**
     * Empty Constructor
     */
    private UiController() {

    }

    /**
     * Get Single sInstance of UiController
     *
     * @return UiController single sInstance
     */
    public static UiController getInstance() {
        if (sInstance == null) {
            // creating new sInstance of application controller
            sInstance = new UiController();
        }
        return sInstance;
    }


    /**
     * Launch the respective activity
     *
     * @param screenId
     */
    public void launchActivity(@ViewFactory.ScreenIds int screenId) {
        Intent intent = new Intent(ApplicationController.getInstance().getContext(), ViewFactory.getActivityClass(screenId));
        ApplicationController.getInstance().getContext().startActivity(intent);

    }

    /**
     * Launch the respective activity
     *
     * @param screenId
     * @param bundle
     */
    public void launchActivity(@ViewFactory.ScreenIds int screenId, @NonNull Bundle bundle) {
        Intent intent = new Intent(ApplicationController.getInstance().getContext(), ViewFactory.getActivityClass(screenId));
        ApplicationController.getInstance().getContext().startActivity(intent, bundle);
    }

    /**
     * Launch the respective activity
     *
     * @param screenId
     * @param launchMode
     */
    public void launchActivity(@ViewFactory.ScreenIds int screenId,  int launchMode) {
        Intent intent = new Intent(ApplicationController.getInstance().getContext(), ViewFactory.getActivityClass(screenId));
        //TODO impl the launch mode logic
        ApplicationController.getInstance().getContext().startActivity(intent);
    }


    /**
     * Launch the respective activity for result
     *
     * @param screenId
     */
    public void launchActivityForResult(@ViewFactory.ScreenIds int screenId, int reqCode) {
        Intent intent = new Intent(ApplicationController.getInstance().getContext(), ViewFactory.getActivityClass(screenId));
//      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Activity activity = ApplicationController.getInstance().getActivity();
        if (activity != null) {
            activity.startActivityForResult(intent, reqCode);
        }
    }

    /**
     * Launch the respective fragments
     *
     * @param screenId
     */
    public void launchFragments(@ViewFactory.ScreenIds int screenId) {
        //TODO impl later
    }

}
