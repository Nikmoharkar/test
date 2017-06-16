package com.fastacash.fastaservice;

import android.app.Activity;
import android.view.View;

import com.fastacash.opensdk.OpenSDK;
import com.fastacash.opensdk.callbacks.CallbackImpl;
import com.fastacash.opensdk.constants.ServiceAPIConstants;
import com.fastacash.opensdk.controller.APIConfig;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by nikhil on 10/27/2015.
 */
public class Fastashort {
    private OpenSDK openSDK;


    public Fastashort() {
        openSDK = new OpenSDK();
    }

//    public void config(JSONObject options) {
//        try {
//            options.put("app", "fasta_short");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        openSDK.config(options);
//    }

    /**
     * @param buttonElement
     * @param context
     * @param url
     * @param userKey
     */
    public void config(View buttonElement, Activity context, String customUrl, String url, String userKey) {
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("userKey", userKey);
            jsonObject.put("buttonElement", buttonElement);
            jsonObject.put("initElement", context);
            jsonObject.put("url", url);
            jsonObject.put("customUrl", customUrl);
            jsonObject.put("app", "fasta_short");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        openSDK.config(jsonObject);
    }


    public void init() {
        openSDK.init();
    }

    public void initWithConfig(JSONObject options) {
//        config(options);
        init();
    }

    /**
     * @param buttonElement
     * @param context
     * @param customUrl
     * @param url
     * @param userKey
     */
    public void initWithConfig(View buttonElement, Activity context, String customUrl, String url, String userKey) {
        config(buttonElement, context, customUrl, url, userKey);
        init();
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
        this.openSDK.setOthers(others);
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
     * @param activity
     * @param url
     * @param customUrl
     * @param callback
     */
    public void shortenLink(Activity activity, String url, String customUrl, String userKey, CallbackImpl callback) {
        initWithConfig(null, activity, "", url, userKey);
        HashMap<String, Object> requestDetails = new HashMap<>();
        requestDetails.put("url", url);
        requestDetails.put("link_purpose", ServiceAPIConstants.LINK_PURPOSE_FAST_A_SHORT);
        requestDetails.put("customUrl", customUrl);
        APIConfig.getInstance().setCustomUrl(customUrl);
        reqToCreateLink(requestDetails, callback);
    }

    /**
     * Requesting Server to create link.
     */
    private void reqToCreateLink(HashMap<String, Object> requestDetails, final CallbackImpl callback) {
        openSDK.createLink(requestDetails, new Callback<HashMap<String, Object>>() {
                    @Override
                    public void success(HashMap<String, Object> responseData, Response response) {
                        if (responseData != null && responseData.containsKey("link")) {
                            System.out.println("Link: " + responseData.get("link"));
                            LinkedTreeMap<String, Object> linkData = (LinkedTreeMap<String, Object>) responseData.get("link");
                            if (linkData != null && linkData.containsKey("code")) {
                                String shortenUrl = getShortenUrl(APIConfig.getInstance().getCustomUrl(), (String) linkData.get("code"));
                                if (callback != null) {
                                    callback.success(shortenUrl);
                                }
                            }
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        if (callback != null) {
                            callback.failure(error.getMessage());
                        }
                    }
                }

        );
    }

    /**
     * Will apply the logic to f8orm the shorten Url.
     *
     * @param customizeUrl
     * @param linkCode
     * @return
     */
    private static String getShortenUrl(String customizeUrl, String linkCode) {
        String shortenUrl = ServiceAPIConstants.FAST_A_LINK + customizeUrl + linkCode;
        return shortenUrl;
    }

}
