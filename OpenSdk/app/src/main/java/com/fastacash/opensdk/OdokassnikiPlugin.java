package com.fastacash.opensdk;

import android.content.Intent;

import com.fastacash.opensdk.plugin.Plugin;
import com.fastacash.opensdk.plugin.PluginManager;

/**
 * Created by nikhil on 10/28/2015.
 */
public class OdokassnikiPlugin extends Plugin {

    /**
     * Here, registering the plugin in constructor
     */
    public OdokassnikiPlugin() {
        super();
        this.config.put("pluginName", "odokassniki");
        registerPlugin();
    }

    @Override
    public void share() {
        super.share();
        Intent sendIntent = new Intent();
        sendIntent.setPackage("ru.ok.android");
        startSharingData(sendIntent);
    }
}
