package com.vyako.smartfactory.main.views.baseviews;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vyako.smartfactory.main.views.fragments.BaseAbstractFragment;

/**
 * Created by nikhilm on 20-Jun-17.
 */

public interface IBaseActivityView extends IBaseView {

    /**
     * Sets the root fragment for the activity, return null if it is not activity where this method is overriding.
     *
     * @param rootFragment object of the root fragment.
     */
    void setRootFragment(BaseAbstractFragment rootFragment);

    /**
     * Adds the fragment to the activity, if not activity then keep empty.
     *
     * @param fragment fragment object that to be added to the stack
     * @param tag      identifier mapped with the fragment, can be null
     */
    void addFragment(@NonNull BaseAbstractFragment fragment, @Nullable String tag);

    /**
     * Removes the fragment from the top.
     *
     * @param tag
     */
    void removeTopFragment(@Nullable String tag);
}
