package com.fastacash.opensdk;

import android.content.Intent;

import com.fastacash.opensdk.plugin.Plugin;
import com.fastacash.opensdk.plugin.PluginManager;

/**
 * Created by nikhil on 10/28/2015.
 */
public class SnapChatPlugin extends Plugin {

    /**
     * Here, registering the plugin in constructor
     *
     */
    public SnapChatPlugin() {
        super();
        this.config.put("pluginName","snapchat");
        registerPlugin();
    }

    @Override
    public void share() {
        super.share();
        Intent sendIntent = new Intent();
        sendIntent.setPackage("com.snapchat.android");
        startSharingData(sendIntent);
    }
}