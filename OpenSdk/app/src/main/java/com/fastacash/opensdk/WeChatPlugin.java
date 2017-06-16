package com.fastacash.opensdk;

import android.content.Intent;

import com.fastacash.opensdk.plugin.Plugin;

/**
 * Created by nikhil on 10/28/2015.
 */
public class WeChatPlugin extends Plugin {

    /**
     * Here, registering the plugin in constructor
     */
    public WeChatPlugin() {
        super();
        this.config.put("pluginName","wechat");
        registerPlugin();
    }

    @Override
    public void share() {
        super.share();
        Intent sendIntent = new Intent();
        sendIntent.setPackage("com.tencent.mm");
        startSharingData(sendIntent);
    }
}
