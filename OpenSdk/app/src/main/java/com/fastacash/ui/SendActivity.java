package com.fastacash.ui;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.fastacash.Constant;
import com.fastacash.opensdk.OpenSDK;
import com.fastacash.opensdk.R;
import com.fastacash.opensdk.constants.ServiceAPIConstants;
import com.fastacash.opensdk.controller.APIConfig;
import com.fastacash.opensdk.plugin.Plugin;
import com.fastacash.opensdk.plugin.PluginManager;
import com.fastacash.opensdk.utils.Utility;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SendActivity extends BaseActivity {

    static String message;
    static String path;
    static boolean send;
    private GridView socialAppIconsGridView;
    private SocilaGridViewAdapter socilaGridViewAdapter;
    private ImageView bottomLogo;
    /**
     * Background view to set the background color.
     */
    private View backgroundView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        colorTitle = getIntent().getIntExtra(Constant.COLOR_TITLE, getResources().getColor(R.color.color_title));
        colorTitleText = getIntent().getIntExtra(Constant.COLOR_TITLE_TEXT, getResources().getColor(R.color.color_title_text));
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        message = getIntent().getStringExtra(Constant.MESSAGE);
        path = getIntent().getStringExtra(Constant.PATH);


        if (message == null && path == null) {
            finish();
        }

        setContentView(R.layout.activity_send);
        initViews();
        setBackground();
        setAdapter();
        setListnerToViews();
    }

    /**
     * Sets the listner to the views
     */
    private void setListnerToViews() {
        if (socialAppIconsGridView != null && socilaGridViewAdapter != null)
            socialAppIconsGridView.setOnItemClickListener(socilaGridViewAdapter);
    }

    /**
     * Initializes the views
     */
    private void initViews() {
        socialAppIconsGridView = (GridView) findViewById(R.id.socialAppIconsGridView);
        backgroundView = findViewById(R.id.backgroundView);
        bottomLogo = (ImageView) findViewById(R.id.bottomLogo);
    }

    /**
     * Gets the callback from the adapter when click on any social app icon
     *
     * @param appName
     */
    public void onSocialAppIconClick(String appName, String name) {
        Plugin plugin = PluginManager.getInstance().getPlugin(appName);
        if (plugin != null) {
            if (message != null) {
                plugin.setObject(message);
            } else if (path != null) {
                plugin.setObject(path);
            }
            plugin.share();
        } else {
            String message = "plugin is not available";
            if (name != null) {
                message += name + " " + message;
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Sets the adapter to the views
     */
    private void setAdapter() {
        socilaGridViewAdapter = new SocilaGridViewAdapter(this);
        socialAppIconsGridView.setAdapter(socilaGridViewAdapter);
    }

    /**
     * Sets the background color based on the type of app feature.
     */
    private void setBackground() {
        String app = APIConfig.getInstance().getApp();
        if (app != null) {
            if (app.equals(ServiceAPIConstants.LINK_PURPOSE_FAST_A_SHARE)) {
                //sets the black color as a background.
                backgroundView.setBackgroundColor(Color.BLACK);
                bottomLogo.setImageResource(R.drawable.fc_sociallinked_white);
            } else if (app.equals(ServiceAPIConstants.LINK_PURPOSE_FAST_A_SHORT)) {

            } else if (app.equals(ServiceAPIConstants.LINK_PURPOSE_FAST_A_SSHORT)) {

            } else if (app.equals(ServiceAPIConstants.LINK_PURPOSE_FAST_A_SHOT)) {
                //sets the black color & alpha for background.
                backgroundView.setBackgroundColor(Color.BLACK);
                backgroundView.setAlpha(0.8f);
                bottomLogo.setImageResource(R.drawable.fc_sociallinked_white);
            } else if (app.equals(ServiceAPIConstants.LINK_PURPOSE_FAST_A_SCREEN_SHOT)) {
                //sets the white color as a background.
                backgroundView.setBackgroundColor(Color.WHITE);
                bottomLogo.setImageResource(R.drawable.fc_sociallinked_grey);
            }
        }
    }

    public void buttonClick(View v) {
        //mantain the application name which is currently selected
        String applicationName = "";
        Intent sendIntent = new Intent();
        send = true;
        int i = v.getId();
        if (i == R.id.facebook) {
            applicationName = "Facebook";
            sendIntent.setPackage("com.facebook.katana");

        } else if (i == R.id.wechat) {
            applicationName = "Wechat";
            sendIntent.setPackage("com.tencent.mm");

        } else if (i == R.id.linkedin) {
            applicationName = "Linkedin";
            sendIntent.setPackage("com.linkedin.android");

        } else if (i == R.id.twitter) {
            applicationName = "Twitter";
            sendIntent.setPackage("com.twitter.android");

        } else if (i == R.id.line) {
            applicationName = "Line";
            sendIntent.setPackage("jp.naver.line.android");

        } else if (i == R.id.sms) {
            applicationName = "SMS";
            sendIntent.setPackage("com.google.android.apps.messaging");

        } else if (i == R.id.googleplus) {
            applicationName = "Google plus";
            sendIntent.setPackage("com.google.android.apps.plus");

        } else if (i == R.id.instagram) {
            applicationName = "Instagram";
            if (path != null) {
                sendIntent.setPackage("com.instagram.android");
            } else {
                Toast.makeText(this, "Only photo can be sent through instagram", Toast.LENGTH_SHORT).show();
                send = false;
            }

        } else if (i == R.id.viber) {
            applicationName = "Viber";
            sendIntent.setPackage("com.viber.voip");

        } else if (i == R.id.hangout) {
            applicationName = "Hangout";
            sendIntent.setPackage("com.google.android.talk");

        } else if (i == R.id.whatsapp) {
            applicationName = "Whatsapp";
            sendIntent.setPackage("com.whatsapp");

        } else if (i == R.id.email) {
            applicationName = "Email";
            sendIntent.setPackage("com.google.android.gm");

        } else if (i == R.id.skype) {
            applicationName = "Skype";
            sendIntent.setPackage("com.skype.raider");

        } else if (i == R.id.snapchat) {
            applicationName = "Snapchat";
            sendIntent.setPackage("com.snapchat.android");

        } else if (i == R.id.hike) {
            applicationName = "Hike";
            sendIntent.setPackage("com.bsb.hike");

        } else if (i == R.id.kakaotalk) {
            applicationName = "Kakao talk";
            sendIntent.setPackage("com.kakao.talk");

        } else if (i == R.id.vk) {
            applicationName = "VK";
            sendIntent.setPackage("com.vkontakte.android");

        } else if (i == R.id.zalo) {
            applicationName = "Zalo";
            sendIntent.setPackage("com.zing.zalo");

        } else if (i == R.id.odokassniki) {
            applicationName = "Odokassniki";
            sendIntent.setPackage("ru.ok.android");

        } else if (i == R.id.messenger) {
            applicationName = "Messenger";
            sendIntent.setPackage("com.facebook.orca");

        } else if (i == R.id.gmail) {
            applicationName = "Gmail";
            sendIntent.setPackage("com.google.android.gm");

        } else {
            Toast.makeText(this, "Button not found", Toast.LENGTH_SHORT).show();
        }
        //Here we are checking that the selected app is present in device or not
        if (Utility.isAppInstalled(this, sendIntent)) {
            if (send && (message != null || path != null)) {
                sendIntent.setAction(Intent.ACTION_SEND);
                if (message != null) {
                    sendIntent.putExtra(Intent.EXTRA_TEXT, message);
                    sendIntent.setType("text/plain");
                }

                if (path != null) {
                    sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(path));
                    sendIntent.setType("image/*");
                }

                startActivity(sendIntent);
                sendReqToCreateaLink();
            }
        } else {
            Toast.makeText(this, applicationName + " " + getResources().getString(R.string.appIsNotInstalledText), Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    /**
     * Calls the Create Link API request.
     */
    private void sendReqToCreateaLink() {
        OpenSDK openSDK = new OpenSDK();
        HashMap<String, Object> requestData = new HashMap();
        requestData.put("link_purpose", APIConfig.getInstance().getApp());

        openSDK.createLink(requestData, new Callback<HashMap<String, Object>>() {
            @Override
            public void success(HashMap<String, Object> stringObjectHashMap, Response response) {
//not handling any response here
            }

            @Override
            public void failure(RetrofitError error) {
//not handling any response here

            }
        });
    }
}
