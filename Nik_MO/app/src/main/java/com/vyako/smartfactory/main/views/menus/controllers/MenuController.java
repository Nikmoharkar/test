package com.vyako.smartfactory.main.views.menus.controllers;

import com.vyako.smartfactory.main.views.baseviews.IBaseContract;

/**
 * Created by kaushik on 01-Jun-17.
 */

public class MenuController {
    /**
     * private instance of MenuController for singleton Design Pattern
     */
    private static MenuController menuController;


    /**
     * Get Single instance of ApplicationController
     *
     * @return ApplicationController single instance
     */
    public static MenuController getInstance() {
        if (menuController == null) {
            menuController = new MenuController();
        }
        return menuController;
    }

    /**
     * Constructor
     */
    public MenuController() {

    }

    /**
     * Method which check the permission for Menu, Submenu, Event and Action
     *
     * @param permissionId
     * @return true when permission is granted
     */
    public boolean isPermissionGranted(@IBaseContract.PermissionIds int permissionId) {
        return true;
    }

}
