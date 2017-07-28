package com.vyako.smartfactory.main.app.controllers;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.vyako.smartfactory.main.app.application.MyApplication;
import com.vyako.smartfactory.main.app.constants.AppConstants;
import com.vyako.smartfactory.main.app.factories.ViewFactory;


/**
 * Created by kaushik on 01-Jun-17.
 * ApplicationController.java
 * <p/>
 * The ApplicationController Class, which helps to manage different Controllers,
 * Models, Views. This Class will be initialize as the platform requirement.
 */

public class ApplicationController {

    /**
     * private instance of ApplicationController for singleton Design Pattern
     */
    private static ApplicationController instance;

    /**
     * private instance of UIController for managing different AbstractViews
     */
    public UiController uiController;

    /**
     * private instance of ViewFactory for fast accessing.
     */
    public ViewFactory viewFactory;

    public Activity mActivity;
    public Context mContext;

    private MyApplication application;

    /**
     * Constructor of ApplicationController
     */
    private ApplicationController() {
        uiController = UiController.getInstance();
    }

    /**
     * Get Single instance of ApplicationController
     *
     * @return ApplicationController single instance
     */
    public static ApplicationController getInstance() {
        if (instance == null) {
            // creating new instance of application controller
            instance = new ApplicationController();
        }
        return instance;
    }
//
//    /**
//     * This Function will get called from MainActivity or Any Activity which is
//     * going to display first screen after launching this application
//     */
//    public void initialize() {
//
//        // initialize the ModelFacade
//        modelFacade.initialize();
//
//        // set the reference for UI Controller
//        uiController = UIController.getInstance();
//
//        // initialize the UIController
//        uiController.initialize();
//
//        // set the viewFactory reference for further use in code.
//        viewFactory = ViewFactory.getInstance();
//
//    }

    /**
     * returns the current mActivity
     *
     * @return
     */
    public Activity getActivity() {
        return mActivity;
    }

    /**
     * sets the reference of current mActivity
     *
     * @param mActivity
     */
    public void setActivity(@NonNull Activity mActivity) {
        this.mActivity = mActivity;
    }


    /**
     * @return the application mContext
     */
    public MyApplication getApplication() {
        return application;
    }

    /**
     * sets the reference of application
     *
     * @param application
     */
    public void setApplication(MyApplication application) {
        this.application = application;
    }

    /**
     * @return the mContext of current mActivity
     */
    public Context getContext() {
        return mContext;
    }


    /**
     * sets the reference of mContext
     *
     * @param
     */
    public void setContext(@NonNull Context mContext) {
        this.mContext = mContext;
    }


    /**
     * Handle Event Based on Event ID
     *
     * @param eventId Event raised by View
     */
    public void handleEvent(@AppConstants.EventIds int eventId) {
        handleEvent(eventId, null);
    }

    /**
     * Handle Event Based on Event ID and Object
     *
     * @param eventId      Event Id based on user actions
     * @param eventObjects It stores the data for the given Event. so it can forward to
     *                     other events
     */
    public void handleEvent(@AppConstants.EventIds int eventId, Object eventObjects) {
        Log.d(AppConstants.LOG_CAT, "handleEvent : " + eventId);

        switch (eventId) {
            case AppConstants.EventIds.LAUNCH_LOGIN_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.LOGIN_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_HOME_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.HOME_SCREEN);
                break;
            case AppConstants.EventIds.LAUNCH_MO_MODULE_SCREEN:
                uiController.launchActivity(ViewFactory.ScreenIds.MO_MODULE_SCREEN);
                break;
            default:
                throw new IllegalStateException("Invalid Event id");
        }
    }
}
