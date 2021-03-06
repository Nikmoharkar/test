package com.fastacash.opensdk;

import android.content.Intent;

import com.fastacash.opensdk.plugin.Plugin;
import com.fastacash.opensdk.plugin.PluginManager;

/**
 * Created by nikhil on 10/28/2015.
 */
public class TwitterPlugin extends Plugin {

    /**
     * Here, registering the plugin in constructor
     *
     */
    public TwitterPlugin() {
        super();
        this.config.put("pluginName","twitter");
        registerPlugin();
    }

    @Override
    public void share() {
        super.share();
        Intent sendIntent = new Intent();
        sendIntent.setPackage("com.twitter.android");
        startSharingData(sendIntent);
    }
}
