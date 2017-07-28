package com.vyako.smartfactory.main.views.baseviews;

/**
 * Created by kaushik on 23-May-17.
 * IBaseView interface declare same common  methods here
 * This class is an generic type so set the presenter
 */

public interface IBaseView {
    /**
     * Initialize the things which are common for all subclasses
     */
    void initializeInstances();

}
