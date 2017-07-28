package com.vyako.smartfactory.mo.contracts;

import com.vyako.smartfactory.main.views.baseviews.IBaseContract;
import com.vyako.smartfactory.main.views.managers.IBaseDataManager;
import com.vyako.smartfactory.main.views.baseviews.IBaseFragmentView;

/**
 * Created by ranjeetd on 6/12/2017.
 */

public interface MoDetailFragmentContract {
    interface IPermissionIds extends IBaseContract {
        int GET_MO_DETAILS = 40;
    }

    interface IView extends IBaseFragmentView {
        void setData(Object object);
    }

    interface IPresenter {
        void requestToGetMo();

        /**
         * Calls to remove the current fragment from the stack.
         */
        void onBackButtonPressed();

    }

    interface IDataManager extends IBaseDataManager {

    }
}