package com.fastacash.opensdk;

import android.content.Intent;

import com.fastacash.opensdk.plugin.Plugin;

/**
 * Created by nikhil on 10/28/2015.
 */
public class WhatsAppPlugin extends Plugin {

    /**
     * Here, registering the plugin in constructor
     */
    public WhatsAppPlugin() {
        super();
        this.config.put("pluginName", "whatsapp");
        registerPlugin();
    }

    @Override
    public void share() {
        super.share();
        Intent sendIntent = new Intent();
        sendIntent.setPackage("com.whatsapp");
        startSharingData(sendIntent);

    }
}
