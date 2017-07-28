package com.vyako.smartfactory.main.app.models;

import com.vyako.smartfactory.main.app.controllers.ApplicationController;
import com.vyako.smartfactory.main.app.preferences.Preferences;

import static com.vyako.smartfactory.main.app.constants.AppConstants.PREFERENCES.KEY_HOST_URL;

/**
 * Created by kaushik on 25-May-17.
 */

public class LocalModel {
    /**
     * private instance of LocalModel for singleton Design Pattern
     */
    private static LocalModel localModel;

    private boolean isDialogShowing = false;

    /**
     * LocalModel method to gets the single ton class.
     *
     * @return
     */
    public static LocalModel getInstance() {
        if (localModel == null) {
            localModel = new LocalModel();
        }
        return localModel;
    }

    /**
     * Constructor
     */
    private LocalModel() {
        //todo
    }


    /**
     * Show dialog status
     * return true if dialog is showing
     * return false if dialog is not showing
     *
     * @return
     */
    public boolean isDialogShowing() {
        return isDialogShowing;
    }

    /**
     * @param dialogShowing
     */
    public void setDialogShowing(boolean dialogShowing) {
        isDialogShowing = dialogShowing;
    }


    /**
     * Method which returns the host URL.
     *
     * @return
     */
    public String getHostUrl() {
        Preferences preferences = new Preferences(ApplicationController.getInstance().getContext());
        String hostUrl = preferences.getString(KEY_HOST_URL, null);
        if (hostUrl != null) {
            hostUrl = "http://" + hostUrl + "/smart_factory/api";
        }
        return hostUrl;
    }
}
