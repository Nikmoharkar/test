package com.vyako.smartfactory.main.presenters;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import com.vyako.smartfactory.main.app.constants.AppConstants;
import com.vyako.smartfactory.main.app.controllers.ApplicationController;
import com.vyako.smartfactory.main.app.preferences.Preferences;
import com.vyako.smartfactory.main.contracts.SplashScreenContract;
import com.vyako.smartfactory.main.views.baseviews.IBaseContract;

/**
 * Created by hp on 31-05-2017.
 */

public class SplashActivityPresenterImpl extends BasePresenter implements SplashScreenContract.IPresenter {
    private final int SPLASH_INTERVAL = 3000;

    public SplashActivityPresenterImpl(Context context, SplashScreenContract.IView splashView) {
        super(context, splashView);
    }

    @Override
    public void finishSplashScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                launchRespectiveScreen();
            }
        }, SPLASH_INTERVAL);
    }

    @Override
    public void launchRespectiveScreen() {
        Preferences preferences = new Preferences(context);
        boolean isLaunchHomeScreen = preferences.getBoolean(AppConstants.PREFERENCES.IS_LAUNCH_HOME_SCREEN, false);
        if (isLaunchHomeScreen) {
            dispatch(SplashScreenContract.IPermissionIds.LAUNCH_HOME_SCREEN, true);
        } else {
            dispatch(SplashScreenContract.IPermissionIds.LAUNCH_LOGIN_SCREEN, true);
        }
    }


    @Override
    public void launchLoginScreen() {
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
        ((Activity) context).finish();
    }

    @Override
    public void launchHomeScreen() {
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_HOME_SCREEN);
        ((Activity) context).finish();
    }

    @Override
    public void create() {
        super.create();

    }

    @Override
    public void stop() {
    }

    @Override
    public void onPermissionAccess(@IBaseContract.PermissionIds int permissionId, @Nullable Object data) {
        switch (permissionId) {
            case SplashScreenContract.IPermissionIds.FINISH_SPLASH_SCREEN:
                Log.d(AppConstants.LOG_CAT, ">> FINISH_SPLASH_SCREEN");
                finishSplashScreen();
                break;

            case SplashScreenContract.IPermissionIds.LAUNCH_HOME_SCREEN:
                Log.d(AppConstants.LOG_CAT, ">> LAUNCH_HOME_SCREEN");
                launchHomeScreen();
                break;

            case SplashScreenContract.IPermissionIds.LAUNCH_LOGIN_SCREEN:
                Log.d(AppConstants.LOG_CAT, ">> LAUNCH_LOGIN_SCREEN");
                launchLoginScreen();
                break;

        }
    }


}
