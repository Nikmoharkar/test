package com.fastacash.fastaservice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.fastacash.Constant;
import com.fastacash.opensdk.OpenSDK;
import com.fastacash.opensdk.callbacks.CallbackImpl;
import com.fastacash.opensdk.controller.APIConfig;
import com.fastacash.ui.SendActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by nikhil on 10/27/2015.
 */
public class Fastashare {
    private OpenSDK openSDK;


    public Fastashare() {
        openSDK = new OpenSDK();
    }


    /**
     * @param buttonElement
     * @param context
     * @param url
     * @param resId
     * @param listOfSocialApp
     * @param userKey
     */
    public void config(View buttonElement, Activity context, String url, int resId,
                       HashMap<Integer, HashMap<String, Object>> listOfSocialApp, String userKey) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userKey", userKey);
            jsonObject.put("buttonElement", buttonElement);
            jsonObject.put("initElement", context);
            jsonObject.put("url", url);
            jsonObject.put("resId", resId);
            jsonObject.put("social", listOfSocialApp);
            jsonObject.put("app", "fasta_share");

        } catch (Exception e) {
            e.printStackTrace();
        }
        openSDK.config(jsonObject);
    }

    /**
     * @param buttonElement
     * @param context
     * @param url
     * @param resId
     * @param userKey
     */
    public void config(View buttonElement, Activity context, String url, int resId, String userKey) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userKey", userKey);
            jsonObject.put("buttonElement", buttonElement);
            jsonObject.put("initElement", context);
            jsonObject.put("url", url);
            jsonObject.put("resId", resId);
            jsonObject.put("app", "fasta_share");
        } catch (Exception e) {
            e.printStackTrace();
        }
        openSDK.config(jsonObject);
    }

    public void init() {
        openSDK.init();
    }


    /**
     * @param buttonElement
     * @param context
     * @param url
     * @param resId
     * @param userKey
     */
    public void initWithConfig(View buttonElement, Activity context, String url, int resId, String userKey) {
        config(buttonElement, context, url, resId, userKey);

        init();
    }

    /**
     * @param buttonElement
     * @param context
     * @param url
     * @param resId
     * @param listOfSocialApp
     * @param userKey
     */
    public void initWithConfig(View buttonElement, Activity context, String url, int resId,
                               HashMap<Integer, HashMap<String, Object>> listOfSocialApp, String userKey) {
        config(buttonElement, context, url, resId, listOfSocialApp, userKey);
        init();
    }

    /**
     * Get the message which to sahre
     *
     * @return
     */
    public String getMessage() {
        return openSDK.getMessage();
    }

    /**
     * Sets the message which to sahre
     *
     * @param message
     */
    public void setMessage(String message) {
        openSDK.setMessage(message);
    }

    /**
     * Get the subject
     *
     * @return
     */
    public String getSubject() {
        return openSDK.getSubject();
    }

    /**
     * Sets the subject
     *
     * @param subject
     */
    public void setSubject(String subject) {
        openSDK.setSubject(subject);
    }


    /**
     * Get the Others
     *
     * @return
     */

    public Object getOthers() {
        return openSDK.getOthers();
    }

    /**
     * Sets the Others
     *
     * @param others
     */
    public void setOthers(Object others) {
        this.setOthers(others);
    }


    /**
     * Sets the custom URL
     *
     * @param customLink
     */
    public void setCustomLink(String customLink) {
        APIConfig.getInstance().setCustomUrl(customLink);
    }

    /**
     * Gets the custom URL
     *
     * @return
     */
    public String getCustomLink() {
        return APIConfig.getInstance().getCustomUrl();
    }

    /**
     * Set Extra parameters on the request when creating/updating the link
     *
     * @param custom free-form JSON.
     */
    public void setCustom(Object custom) {
        APIConfig.getInstance().setCustom(custom);

    }

    /**
     * Gets the Object
     *
     * @return
     */
    public Object getCustom() {
        return APIConfig.getInstance().getCustom();

    }


    /**
     * Set a social channel on the fastalink
     */
    public String getSocialChannel() {
        return APIConfig.getInstance().getSocialChannel();
    }

    /**
     * Returns the name of social channel set on the fastalink.
     *
     * @param socialChannelName
     */
    public void setSocialChannel(String socialChannelName) {
        APIConfig.getInstance().setSocialChannel(socialChannelName);
    }

    /**
     * Gets the Fasta Link
     */
    public String getFastaLink() {
        return openSDK.getFastaLink();
    }

    /**
     * Sets the Fasta Link
     */
    public void setFastaLink(String fastaLink) {
        openSDK.setFastaLink(fastaLink);
    }

    /**
     * Add the social app data
     *
     * @param socialAppData JSON data in form of String
     */
    public void addSocialApps(String socialAppData) {
        HashMap<Integer, HashMap<String, Object>> socialChannels = APIConfig.getInstance().getSocialChannels();
        try {
            JSONObject jsonObject = new JSONObject(socialAppData);
            if (jsonObject != null) {
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    Object object = jsonObject.get(key);
                    if (object instanceof HashMap) {
                        HashMap<String, Object> socialAppInfo = (HashMap<String, Object>) object;
                        socialChannels.put(Integer.parseInt(key), socialAppInfo);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the social app data
     *
     * @param socialAppData JSON data in form of String
     */
    public void updateSocialApps(String socialAppData) {
        HashMap<Integer, HashMap<String, Object>> socialChannels = APIConfig.getInstance().getSocialChannels();
        try {
            JSONObject jsonObject = new JSONObject(socialAppData);
            if (jsonObject != null) {
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()) {
                    String keyName = (String) keys.next();
                    Object object = jsonObject.get(keyName);
                    if (object instanceof HashMap) {
                        int key = Integer.parseInt(keyName);
                        HashMap<String, Object> socialAppInfo = (HashMap<String, Object>) object;

                        if (socialChannels.containsKey(key)) {

                            socialChannels.put(key, socialAppInfo);
                        }

                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sgare the Data with social app
     *
     * @param url
     * @param callback
     */

    public void share(Activity activity, String url, String userKey, CallbackImpl callback) {
        //set resource id as -1 when no need to provide the resource id
        initWithConfig(null, activity, url, -1, userKey);
        launchSendActivity(url);
    }

    /**
     * @param activity
     * @param url
     * @param listOfSocialApp
     * @param callback
     */
    public void share(Activity activity, String url, HashMap<Integer, HashMap<String, Object>> listOfSocialApp, String userKey, CallbackImpl callback) {
        //set resource id as -1 when no need to provide the resource id
        initWithConfig(null, activity, url, -1, listOfSocialApp, userKey);
        launchSendActivity(url);
    }

    private void launchSendActivity(String url) {
        Intent intent = new Intent(APIConfig.getInstance().getContext(), SendActivity.class);
        APIConfig apiConfig = APIConfig.getInstance();
        Context context = apiConfig.getContext();
        if (context != null) {
            if (url != null) {
                intent.putExtra(Constant.MESSAGE, url);

            }

        }
        APIConfig.getInstance().getContext().startActivity(intent);
    }
}
