package com.vyako.smartfactory.main.contracts;


import com.vyako.smartfactory.main.views.baseviews.IBaseActivityView;
import com.vyako.smartfactory.main.views.baseviews.IBaseContract;
import com.vyako.smartfactory.main.views.managers.IBaseDataManager;

/**
 * Created by kaushik on 31-05-2017.
 * This class contain all the method declarations that are going to use in Home screen
 */

public interface HomeScreenContract {

    /**
     * Maintain the screen level events id here.
     * Add the events id in @IntDef of super class {@link IBaseContract}
     */
    interface IPermissionIds extends IBaseContract {
        //todo add permissions
        int LAUNCH_MO_MODULE_SCREEN = 103;


    }

    /**
     * Operation belongs to view
     */
    interface IView extends IBaseActivityView{

        void setAdapterToGridView();

    }

    /**
     * Operation belongs in presenter impl
     */
    interface IPresenter {

    }

    /**
     * Operation belongs to model impl
     */
    interface IDataManager extends IBaseDataManager {

    }

}
