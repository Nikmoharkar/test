package com.vyako.smartfactory.main.views.baseviews;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.vyako.smartfactory.main.contracts.HomeScreenContract.IPermissionIds.LAUNCH_MO_MODULE_SCREEN;
import static com.vyako.smartfactory.main.contracts.LoginScreenContract.IPermissionIds.LOGIN;
import static com.vyako.smartfactory.mo.contracts.MoDetailFragmentContract.IPermissionIds.GET_MO_DETAILS;
import static com.vyako.smartfactory.mo.contracts.MoListFragmentContract.IPermissionIds.CHANGE_ZONE_OF_MO;
import static com.vyako.smartfactory.mo.contracts.MoListFragmentContract.IPermissionIds.CREATE_NEW_MO;
import static com.vyako.smartfactory.mo.contracts.MoListFragmentContract.IPermissionIds.DELETE_MO;
import static com.vyako.smartfactory.mo.contracts.MoListFragmentContract.IPermissionIds.EDIT_MO;
import static com.vyako.smartfactory.mo.contracts.MoListFragmentContract.IPermissionIds.LAUNCH_MO_DETAIL_FRAGMENT_SCREEN;
import static com.vyako.smartfactory.mo.contracts.MoListFragmentContract.IPermissionIds.LAUNCH_MO_LIST_FRAGMENT_SCREEN;
import static com.vyako.smartfactory.mo.contracts.MoListFragmentContract.IPermissionIds.MO_LIST_PERMISSION;

import static com.vyako.smartfactory.main.contracts.SplashScreenContract.IPermissionIds.FINISH_SPLASH_SCREEN;
import static com.vyako.smartfactory.main.contracts.SplashScreenContract.IPermissionIds.LAUNCH_HOME_SCREEN;
import static com.vyako.smartfactory.main.contracts.SplashScreenContract.IPermissionIds.LAUNCH_LOGIN_SCREEN;

/**
 * Base Contract class which help you manage the common constant permission ids
 * And def of permission ids so not other than that permission ids are accepted
 * Created by kaushik on 06-Jun-17.
 */

public interface IBaseContract {

    /**
     * Annotations for events id
     * Make here if events id are common
     * otherwise maintain in base class
     */
    @Retention(RetentionPolicy.CLASS)
    @IntDef({FINISH_SPLASH_SCREEN,
            LAUNCH_LOGIN_SCREEN,
            LAUNCH_HOME_SCREEN,
            LOGIN,
            LAUNCH_MO_MODULE_SCREEN,
            CREATE_NEW_MO,
            DELETE_MO,
            EDIT_MO,
            CHANGE_ZONE_OF_MO,
            MO_LIST_PERMISSION,
            LAUNCH_MO_LIST_FRAGMENT_SCREEN,
            LAUNCH_MO_DETAIL_FRAGMENT_SCREEN,
            GET_MO_DETAILS
    })
    @interface PermissionIds {

    }
}
