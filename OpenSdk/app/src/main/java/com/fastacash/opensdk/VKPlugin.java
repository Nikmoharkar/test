package com.fastacash.opensdk;

import android.content.Intent;

import com.fastacash.opensdk.plugin.Plugin;
import com.fastacash.opensdk.plugin.PluginManager;

/**
 * Created by nikhil on 10/28/2015.
 */
public class VKPlugin extends Plugin {

    /**
     * Here, registering the plugin in constructor
     */
    public VKPlugin() {
        super();
        this.config.put("pluginName","vk");
        registerPlugin();
    }

    @Override
    public void share() {
        super.share();
        Intent sendIntent = new Intent();
        sendIntent.setPackage("com.vkontakte.android");
        startSharingData(sendIntent);
    }
}