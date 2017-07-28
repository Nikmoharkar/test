package com.vyako.smartfactory.main.contracts;

import android.support.annotation.NonNull;

import com.vyako.smartfactory.main.views.baseviews.IBaseActivityView;
import com.vyako.smartfactory.main.views.baseviews.IBaseContract;
import com.vyako.smartfactory.main.views.managers.IBaseDataManager;
import com.vyako.smartfactory.main.network.listeners.APIResponseClientCallback;

/**
 * Created by kaushik on 01-Jun-17.
 * This class contain all the method declarations that are going to use in splash screen
 */

public interface LoginScreenContract {

    /**
     * Maintain the screen level events id here.
     * Add the events id in @IntDef of super class {@link IBaseContract}
     */
    interface IPermissionIds extends IBaseContract {
        int LOGIN = 20;
    }

    /**
     * Operation belongs to view
     */
    interface IView extends IBaseActivityView {

        /**
         * Set the Data on View UI
         */
        void setData();

        /**
         * Check the Validation for enter fields
         * If all field are valid return true
         * If any field are invalid return false
         *
         * @return
         */
        boolean areValidFields();

        /**
         * Method which return the login credentials
         *
         * @return
         */
        String getLoginCredentials();

        /**
         * Show progress loading
         */
        void showLoading();

        /**
         * hide progress loading
         */
        void stopLoading();
    }

    /**
     * Operation belongs in presenter impl
     */
    interface IPresenter {

        /**
         * Method which prepare for login after permission is granted
         */
        void prepareForLogin();

    }


    /**
     * Operation belongs to data manager impl
     */
    interface IDataManager extends IBaseDataManager {
        /**
         * Method which handle the login process
         * Request for login
         *
         * @param loginCredentials
         * @param apiResponseClientCallback
         */
        void handleLoginProcess(@NonNull String loginCredentials, @NonNull APIResponseClientCallback apiResponseClientCallback);

    }
}
