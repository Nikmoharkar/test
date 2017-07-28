package com.vyako.smartfactory.mo.contracts;

import android.support.v4.app.Fragment;

import com.vyako.smartfactory.main.views.baseviews.IBaseContract;
import com.vyako.smartfactory.main.views.baseviews.IBaseFragmentView;
import com.vyako.smartfactory.mo.dos.MOListDO;

/**
 * Created by ranjeetd on 6/9/2017.
 */

public interface MoListFragmentContract {

    interface IPermissionIds extends IBaseContract {
        int CREATE_NEW_MO = 1;
        int EDIT_MO = 2;
        int DELETE_MO = 3;
        int CHANGE_ZONE_OF_MO = 4;
        int LAUNCH_MO_LIST_FRAGMENT_SCREEN = 104;
        int LAUNCH_MO_DETAIL_FRAGMENT_SCREEN = 105;

        int MO_LIST_PERMISSION = 6;
    }

    public interface IView extends IBaseFragmentView {
        void setData();

        void refreshMOListAdapter(MOListDO moListDO);
    }


    interface IPresenter {
        void prepareToGetMoList();

        void createNewMo();

        void deleteMo();

        void editMo();

        void changeZoneOfMo();

        void launchFragment(Fragment fragment);

        void launchMODetails(Object args);
    }

}
