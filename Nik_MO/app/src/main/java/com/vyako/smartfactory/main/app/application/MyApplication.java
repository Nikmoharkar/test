package com.vyako.smartfactory.main.app.application;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.stetho.Stetho;
import com.vyako.smartfactory.main.models.database.greendao.DaoMaster;
import com.vyako.smartfactory.main.models.database.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;


/**
 * Created by kaushik on 01-Jun-17.
 * {@link MyApplication}
 * Main Application class init
 */
public class MyApplication extends Application {

    /**
     * private instance of MyApplication for singleton Design Pattern
     */
    private static MyApplication myApplication = null;

    /**
     * instance of Dao session with getter for interacting with green dao tables.
     */
    private DaoSession daoSession;

    /**
     * Make to API request in queues
     */
    private RequestQueue mRequestQueue;

    /**
     * Name of database
     */
    private final String DATABASE_NAME = "framework_db";


    /**
     * Making the Application class single ton
     *
     * @return
     */
    public static MyApplication getInstance() {

        return myApplication;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        initStetho();
        configureGreenDaoSettings();
    }

    /**
     * init the Green Dao components for sql lite managements
     */
    private void configureGreenDaoSettings() {

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DATABASE_NAME); //The
        // users-db here is the name of our database.
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    /**
     * Accessing the green dao session for interacting for table created using green dao
     *
     * @return
     */
    public DaoSession getDaoSession() {
        return daoSession;
    }


    /**
     * init the stetho components to show Local Database
     */
    private void initStetho() {
        // Create an InitializerBuilder
        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        // Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        );

        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    /**
     * Method which return the Request into queues
     *
     * @return
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(this);
        }
        return mRequestQueue;
    }

    /**
     * Method which added the Request into queues
     *
     * @param req
     * @param tag
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req, int tag) {
        // set the default tag if tag is empty
        req.setTag(tag);
        getRequestQueue().add(req);
    }

}

