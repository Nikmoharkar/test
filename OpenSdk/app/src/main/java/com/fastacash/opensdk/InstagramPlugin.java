package com.fastacash.opensdk;

import android.content.Intent;

import com.fastacash.opensdk.plugin.Plugin;
import com.fastacash.opensdk.plugin.PluginManager;

/**
 * Created by nikhil on 10/28/2015.
 */
public class InstagramPlugin extends Plugin {

    /**
     * Here, registering the plugin in constructor
     *
     */
    public InstagramPlugin() {
        super();
        this.config.put("pluginName", "instagram");
        registerPlugin();
    }

    @Override
    public void share() {
        super.share();
        Intent sendIntent = new Intent();
        sendIntent.setPackage("com.instagram.android");
        startSharingData(sendIntent);
    }
}