package com.vyako.smartfactory.main.models.database.controllers;


import com.vyako.smartfactory.main.app.application.MyApplication;
import com.vyako.smartfactory.main.models.database.greendao.DemoDao;

/**
 * Created by Namrata on 6/2/2017.
 * Controller from which we get all the DAO related to the ALL the Table created by Using Green Dao.
 */
public class GreenDaoController {
    private static GreenDaoController greenDaoController;

    public GreenDaoController() {
    }

    public static GreenDaoController getInstance() {
        if (greenDaoController == null) {
            greenDaoController = new GreenDaoController();
        }
        return greenDaoController;
    }

    /**
     * Returns the Demo Table Dao which holds all the Demo Table related information.
     *
     * @return
     */
    public DemoDao getDemoTableDao() {
        return MyApplication.getInstance().getDaoSession().getDemoDao();
    }


}
