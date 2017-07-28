package com.vyako.smartfactory.main.views.baseviews;

/**
 * Created by nikhilm on 20-Jun-17.
 */

public interface IBaseFragmentView extends IBaseView {

    /**
     * Sets the Attributes require for the fragment to be Initiated with
     *
     * @param args object can be passed to save data from it.
     */
    public void setArguments(Object args);

    /**
     * Finished the current fragment either by calling activity back pressed method or remove fragment.
     */
    public void finish();
}
