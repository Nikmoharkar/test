package com.vyako.smartfactory.main.views.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.vyako.smartfactory.main.R;
import com.vyako.smartfactory.main.presenters.BasePresenter;
import com.vyako.smartfactory.main.views.fragments.BaseAbstractFragment;

/**
 * BaseAbstractActivity which help to declare all the common things that can useful in all
 * subclass
 * This class is generic type so always pass the presenter impl.
 * Created by kaushik on 01-Jun-17.
 */

public abstract class BaseAbstractActivity<T> extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    protected FrameLayout activityContainerLayout;
    protected FragmentTransaction fragmentTransaction;

    /**
     * Keeps the generic presenter object so can be used in every activity.
     */
    protected T presenter;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInstances();
        loadView();
        initializeViews();
        setListenerToViews();
        registerViews();
        setUpToolbar();
        setUpDrawer();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ((BasePresenter) presenter).start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * Set listener to all the view here.
     * Make sure to override the setListenerToViews in the activity
     * Make sure the make call super and then set all listeners to views.
     */
    protected void setListenerToViews() {

    }

    private void loadView() {
        setContentView(R.layout.main_activity_base_abtstract);
        activityContainerLayout = (FrameLayout) findViewById(R.id.activity_container_layout);
        // activityContainerLayout.addView(getView());
        View view = getView();
        if (view != null) {
            activityContainerLayout.addView(view);
        }
    }

    /**
     * get the view of activity
     * Need to set the view
     * Compulsory to Override in each activity
     *
     * @return
     */
    protected abstract View getView();


    /**
     * Here we set the generic presenter
     * Override setPresenter in all sub classes and set the presenter
     * Compulsory to Override in each screen
     */
    protected abstract void setPresenter();

    /**
     * initialize the instances required for the View & presenter.
     * Specially initialize the presenter here.
     */
    public void initializeInstances() {
        setPresenter();
    }

    /**
     * Register events to the views
     */
    private void registerViews() {

    }

    /**
     * initialize all the view here
     * Make sure to override the initializeViews in the activity
     * make sure the make call super and then initialize use views
     */
    protected void initializeViews() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    /**
     * Set the tool bar here
     */
    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /**
     * Hide the toolbar from activity
     */
    public void hideToolbar() {
        toolbar.setVisibility(View.GONE);
    }

    /**
     * Show the toolbar on activity
     */
    public void showToolbar() {
        toolbar.setVisibility(View.VISIBLE);
    }

    /**
     * Set up the navigation drawer here
     */
    private void setUpDrawer() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item);
    }

    /**
     * This Method close open drawer
     */
    public void drawerClose() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            //drawer is open then close it
            drawerLayout.closeDrawers();
        }
    }

    /**
     * It locks the drawer.
     * and set back button
     */
    public void lockDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        drawerToggle.setDrawerIndicatorEnabled(false);
    }

    /**
     * It unlocks the drawer.
     * and set drawer button
     */
    public void releaseDrawerLock() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        drawerToggle.setDrawerIndicatorEnabled(true);
    }

    /**
     * Here we can show the dialog box on login fail
     *
     * @param dialogMessage
     */
    protected void showDialog(String dialogTitle, String dialogMessage, final int request_id, boolean isOutsideCancalabel) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(dialogTitle);
        builder.setMessage(dialogMessage);

        builder.setPositiveButton(getString(R.string.ok_txt),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        onPositiveButtonClick(request_id);
                        dialog.dismiss();

                    }
                });
        builder.setNegativeButton(getString(R.string.cancel_txt),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        dialog.dismiss();
                        onCancelButtonClick(request_id);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(isOutsideCancalabel);
        dialog.show();
    }

    /**
     * Replaces the fragment.
     *
     * @param fragment
     */
    protected void replaceFragment(BaseAbstractFragment fragment) {
        if (fragment != null) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.activity_container_layout, fragment);
            //fragmentTransaction.addToBackStack(null);
            //fragmentTransaction.commitAllowingStateLoss();j
            fragmentTransaction.commit();
        } else {
            Log.e("Replace Fragment", "Fragment is null!");
        }
    }

    /**
     * Add new fragment to the stack.
     *
     * @param fragment
     */
    protected void addFragmentToStack(BaseAbstractFragment fragment) {
        System.out.println("Activity: addFragmentToStack");
        if (fragment != null) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.activity_container_layout, fragment);
            fragmentTransaction.addToBackStack(null);
            //fragmentTransaction.commitAllowingStateLoss();j
            fragmentTransaction.commit();
        } else {
            Log.e("Add Fragment", "Fragment is null!");
        }
    }


    /**
     * Remove top Fragment.
     */
    protected void removeTopFragment() {
        onBackPressed();
    }

    /**
     * Get called when we click on positive button
     *
     * @param request_id
     */
    protected void onPositiveButtonClick(int request_id) {
        switch (request_id) {
//            case REQ_LOGOUT:
//
//                break;
//
//            case REQ_LOGIN:
//
//                break;
        }

    }

    /**
     * Get called when we click on negative button
     *
     * @param request_id
     */
    protected void onCancelButtonClick(int request_id) {
        switch (request_id) {
//            case REQ_LOGOUT:
//                break;
//
//
//            case REQ_LOGIN:
//                break;
        }
    }


    /**
     * Show loading.
     */
    public void showLoading(final Context context, final String loadingText, final boolean isCanceledOnTouchOutside) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    pDialog = new ProgressDialog(context);
                    pDialog.setMessage(loadingText);
                    pDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
                    pDialog.show();
                } catch (Exception e) {
                    Log.d("AlertDialog", "Progress dialog can not be shown. ;-(");
                }
            }
        });
    }


    /**
     * Hide loading.
     */
    public void hideLoading() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (pDialog != null)
                        pDialog.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
