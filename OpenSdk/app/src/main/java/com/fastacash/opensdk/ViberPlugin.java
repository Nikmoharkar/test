package com.fastacash.opensdk;

import android.content.Intent;

import com.fastacash.opensdk.plugin.Plugin;
import com.fastacash.opensdk.plugin.PluginManager;

/**
 * Created by Nikhil on 10/21/2015.
 */
public class ViberPlugin extends Plugin {


    /**
     * Here, registering the plugin in constructor
     */
    public ViberPlugin() {
        super();
        this.config.put("pluginName", "viber");
        registerPlugin();
    }

    @Override
    public void share() {
        super.share();
        Intent sendIntent = new Intent();
        sendIntent.setPackage("com.viber.voip");
        startSharingData(sendIntent);
    }
}
