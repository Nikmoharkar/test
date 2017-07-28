package com.vyako.smartfactory.main.network.listeners;

/**
 * Created by ashwini on 1/24/2017.
 */
public interface IDialogCallback {

    public void onPositiveButtonPress(int requestCode);

    public void onNegativeButtonPress(int requestCode);
}
