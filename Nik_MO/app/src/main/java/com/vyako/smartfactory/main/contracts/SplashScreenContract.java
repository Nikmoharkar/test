package com.vyako.smartfactory.main.contracts;


import com.vyako.smartfactory.main.views.baseviews.IBaseActivityView;
import com.vyako.smartfactory.main.views.baseviews.IBaseContract;
import com.vyako.smartfactory.main.views.managers.IBaseDataManager;

/**
 * Created by hp on 31-05-2017.
 * This class contain all the method declarations that are going to use in splash screen
 */

public interface SplashScreenContract {

    /**
     * Maintain the screen level events id here.
     * Add the events id in @IntDef of super class {@link IBaseContract}
     */
    interface IPermissionIds extends IBaseContract {
        int FINISH_SPLASH_SCREEN = 10;
        int LAUNCH_LOGIN_SCREEN = 11;
        int LAUNCH_HOME_SCREEN = 12;
    }


    /**
     * Operation belongs to view
     */
    interface IView extends IBaseActivityView{

        void setData();

        void setAnimation();

    }

    /**
     * Operation belongs in presenter impl
     */
    interface IPresenter {

        void finishSplashScreen();

        void launchRespectiveScreen();

        void launchLoginScreen();

        void launchHomeScreen();


    }

    /**
     * Operation belongs to model impl
     */
    interface IDataManager extends IBaseDataManager {

        void postDataToServer();

        void saveDataToDB();
    }

}
