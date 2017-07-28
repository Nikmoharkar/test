package com.vyako.smartfactory.mo.contracts;

import com.vyako.smartfactory.main.views.baseviews.IBaseActivityView;
import com.vyako.smartfactory.main.views.baseviews.IBaseContract;
import com.vyako.smartfactory.main.views.managers.IBaseDataManager;

/**
 * Created by ranjeetd on 6/8/2017.
 */

public interface MoScreenContract {
    /**
     * Maintain the screen level events id here.
     * Add the events id in @IntDef of super class {@link IBaseContract}
     */
    interface IPermissionIds extends IBaseContract {
        // int LAUNCH_MO_LIST_SCREEN = 101;
        int LAUNCH_MO_LIST_FRAGMENT_SCREEN = 104;
        int LAUNCH_MO_DETAIL_FRAGMENT_SCREEN = 105;
    }

    /**
     * Operation belongs to view
     */
    interface IView extends IBaseActivityView {

        void setData();

    }

    /**
     * Operation belongs in presenter impl
     */
    interface IPresenter {

        /**
         * Launches the Mo details fragment screen.
         */
        void launchMoDetailFragment(Object args);
    }


    /**
     * Operation belongs to data manager impl
     */
    interface IDataManager extends IBaseDataManager {

    }
}
