package com.fastacash.opensdk.plugin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.fastacash.opensdk.R;
import com.fastacash.opensdk.controller.APIConfig;
import com.fastacash.opensdk.utils.Utility;

import java.util.HashMap;

/**
 * Plugin will be used to start the process of registering plugins
 * <p>
 * Created by Nikhil on 10/21/2015.
 */
public abstract class Plugin {
    protected Object object;
    /**
     * Maintains the config information.
     */
    protected HashMap<String, String> config = new HashMap<>();


    /**
     * Here, registering the plugin in constructor
     */
    public Plugin() {

    }

    /**
     * Contains the value of content which to be share
     *
     * @param object
     */
    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * Registers the plugin to Plaugin Manager.
     */
    protected void registerPlugin() {
        PluginManager pluginManager = PluginManager.getInstance();
        pluginManager.registerPlugin(this);
        init();
    }

    /**
     * This method is used to initialize the plugin
     */
    private void init() {
        HashMap<String, String> config = new HashMap<>();
        config.put("pluginVersion", "1.0");
        configure(config);
    }

    /**
     * Here the configuration of plugin will be done
     */
    public void configure(HashMap<String, String> config) {
        this.config.put("pluginVersion", config.get("pluginVersion"));
    }

    /**
     * here the sharing is done
     */
    public void share() {
        System.out.println("share");
    }

    public String getPluginName() {
        if (config != null) {
            return config.get("pluginName");
        }
        return null;
    }


    public String getPluginVersion() {
        if (config != null) {
            return config.get("pluginVersion");
        }
        return null;
    }

    /**
     * Here it start the process to share the data with respective social channel
     *
     * @param sendIntent
     */
    protected void startSharingData(Intent sendIntent) {

        Context context = APIConfig.getInstance().getContext();
        if (context != null) {
            //Here we are checking that the selected app is present in device or not
            if (Utility.isAppInstalled(context, sendIntent)) {
                if ((object != null)) {
                    sendIntent.setAction(Intent.ACTION_SEND);
                    String app = APIConfig.getInstance().getApp();
                    String message = null;
                    if (object instanceof String) {
                        message = (String) object;
                    }
                    if (message != null) {
                        if (app.equals("fasta_share")) {

                            sendIntent.putExtra(Intent.EXTRA_TEXT, message);
                            sendIntent.setType("text/plain");
                        } else {
                            sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(message));
                            sendIntent.setType("image/*");
                        }
                    }
                    try {

                        context.startActivity(sendIntent);
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(context, config.get("pluginName") + " " + context.getResources().getString(R.string.mayNotBeInstalledCorrectlyText), Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                if (config != null) {
                    Toast.makeText(context, config.get("pluginName") + " " + context.getResources().getString(R.string.appIsNotInstalledText), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
