package com.fastacash.opensdk.controller;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.fastacash.opensdk.R;
import com.fastacash.opensdk.constants.ServiceAPIConstants;
import com.fastacash.opensdk.utils.APIUtils;

import java.util.HashMap;

/**
 * Created by nikhil on 10/21/2015.
 */
public class APIConfig {

    private static APIConfig apiConfig;
    public static String HOST_URL = "http://finoculus.com/openapi/2.0";
    //    public static String HOST_URL = "https://api.fastacash.com/openapi/api";


    private Object initElement;
    private Object custom;
    //    private String apiUrl;
    private String pin;
    private String app;
    private int resId;
    /**
     * mantain the value of Accept to put in header
     */
    public static String accept = "application/json";

    private String url = "http://fastacash.com";
    private String customUrl = "fastacash";
    private String clickCount = "4";
    private String linkExpiry = "3";
    private String userKey;
    private String socialChannel;
    /**
     * mantain the value of user_key to put in header
     */
    private String user_key = "ad99043ed6e3c347f44c2e0d934b9fb0";
    private Context context;
    /**
     * button element to where  add template.
     */
    private View buttonElement;
    /**
     * Contains the data of social chaneel
     */
    private HashMap<Integer, HashMap<String, Object>> socialChannels = new HashMap<>();
    /**
     * Holds the values for sharing URL
     */
    private HashMap<String, Integer> fastaShareSocialAppIcons = new HashMap<>();
    /**
     * Holds the values for sharing Photo
     */
    private HashMap<String, Integer> fastaScreenShotSocialAppIcons = new HashMap<>();
    /**
     * Holds the values for sharing ScreenShot
     */
    private HashMap<String, Integer> fastaShotSocialAppIcons = new HashMap<>();

    private APIConfig() {

        setDefaultSocialChannel();
    }

    public void setSocialChannels(HashMap<Integer, HashMap<String, Object>> socialChannels) {
        this.socialChannels = socialChannels;
    }

    public HashMap<Integer, HashMap<String, Object>> getSocialChannels() {
        return socialChannels;
    }

    public static APIConfig getInstance() {
        if (apiConfig == null) {
            apiConfig = new APIConfig();
        }
        return apiConfig;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

//    public String getUser_key() {
//        return user_key;
//    }
//
//    public void setUser_key(String user_key) {
//        this.user_key = user_key;
//    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public View getButtonElement() {
        return buttonElement;
    }

    public void setButtonElement(View buttonElement) {
        this.buttonElement = buttonElement;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }


    public Object getInitElement() {
        return initElement;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getCustomUrl() {
        return customUrl;
    }

    public void setCustomUrl(String customUrl) {
        this.customUrl = customUrl;
    }

    public String getClickCount() {
        return clickCount;
    }

    public void setClickCount(String clickCount) {
        this.clickCount = clickCount;
    }

    public String getLinkExpiry() {
        return linkExpiry;
    }

    public void setLinkExpiry(String linkExpiry) {
        this.linkExpiry = linkExpiry;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, Integer> getFastaShareSocialAppIcons() {
        return fastaShareSocialAppIcons;
    }


    public HashMap<String, Integer> getFastaScreenShotSocialAppIcons() {
        return fastaScreenShotSocialAppIcons;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Object getCustom() {
        return custom;
    }

    public void setCustom(Object custom) {
        this.custom = custom;
    }

    public String getSocialChannel() {
        return socialChannel;
    }

    public void setSocialChannel(String socialChannel) {
        this.socialChannel = socialChannel;
    }

    public HashMap<String, Integer> getFastaShotSocialAppIcons() {
        return fastaShotSocialAppIcons;
    }


    public void setInitElement(Object initElement) {
        this.initElement = initElement;
        try {
            Activity context = (Activity) initElement;
            setContext(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public String getApiUrl() {
//        return apiUrl;
//    }
//
//    public void setApiUrl(String apiUrl) {
//        this.apiUrl = apiUrl;
//    }

    /**
     * Gets the path fields required for particular request.
     *
     * @param reqType Type of Url ex. ServiceAPIConstants.CREATE_LINK
     * @return Array of the fields.
     */
    public String[] getPathFields(int reqType) {
        switch (reqType) {
            case ServiceAPIConstants.CREATE_LINK:
                return new String[]{"links"};

            case ServiceAPIConstants.UPDATE_LINK:
                return new String[]{"links", "linkcode"};

            case ServiceAPIConstants.GET_LINK:
                return new String[]{"links", "linkcode"};
        }
        return null;
    }

    /**
     * Sets default social app icons for fasta share feature
     */
    private void setDefaultFastaShareSocialAppIcons() {
        fastaShareSocialAppIcons.put("whatsapp", R.drawable.icon_sharernd_whatsapp);
        fastaShareSocialAppIcons.put("viber", R.drawable.icon_sharernd_viber);
        fastaShareSocialAppIcons.put("email", R.drawable.icon_sharernd_email);
        fastaShareSocialAppIcons.put("fbweb", R.drawable.icon_sharernd_fb);
        fastaShareSocialAppIcons.put("hangout", R.drawable.icon_sharernd_ghangout);
        fastaShareSocialAppIcons.put("googleplus", R.drawable.icon_sharernd_gplus);
        fastaShareSocialAppIcons.put("linkedin", R.drawable.icon_sharernd_link);
        fastaShareSocialAppIcons.put("sms", R.drawable.icon_sharernd_sms);
        fastaShareSocialAppIcons.put("wechat", R.drawable.icon_sharernd_wechat);
        fastaShareSocialAppIcons.put("twitter", R.drawable.icon_sharernd_tw);

        fastaShareSocialAppIcons.put("line", R.drawable.icon_sharernd_line);
        fastaShareSocialAppIcons.put("snapchat", R.drawable.icon_sharernd_snapchat);
        fastaShareSocialAppIcons.put("skype", R.drawable.icon_sharernd_skype);
        fastaShareSocialAppIcons.put("hike", R.drawable.icon_sharernd_hike);
        fastaShareSocialAppIcons.put("KakaoTalk", R.drawable.icon_sharernd_kakaotalk);
        fastaShareSocialAppIcons.put("vk", R.drawable.icon_sharernd_vkontakte);
        fastaShareSocialAppIcons.put("zalo", R.drawable.icon_sharernd_zalo);
        fastaShareSocialAppIcons.put("messenger", R.drawable.icon_sharernd_fbmsg);

        fastaShareSocialAppIcons.put("gmail", R.drawable.icon_sharernd_gmail);
        fastaShareSocialAppIcons.put("odokassniki", R.drawable.icon_sharernd_odno);
        fastaShareSocialAppIcons.put("instagram", R.drawable.icon_sharernd_ig);


    }

    /**
     * Sets default social app icons for fasta shot feature
     */
    private void setDefaultFastaShotSocialAppIcons() {

        fastaShotSocialAppIcons.put("whatsapp", R.drawable.icon_sharesq_whatsapp);
        fastaShotSocialAppIcons.put("viber", R.drawable.icon_sharesq_viber);
        fastaShotSocialAppIcons.put("email", R.drawable.icon_sharesq_email);
        fastaShotSocialAppIcons.put("fbweb", R.drawable.icon_sharesq_fb);
        fastaShotSocialAppIcons.put("hangout", R.drawable.icon_sharesq_ghangout);
        fastaShotSocialAppIcons.put("googleplus", R.drawable.icon_sharesq_gplus);
//        fastaShotSocialAppIcons.put("googleplus", R.drawable.icon_sharesq_gpius);
        fastaShotSocialAppIcons.put("linkedin", R.drawable.icon_sharesq_link);
        fastaShotSocialAppIcons.put("sms", R.drawable.icon_sharesq_sms);
        fastaShotSocialAppIcons.put("wechat", R.drawable.icon_sharesq_wechat);
        fastaShotSocialAppIcons.put("twitter", R.drawable.icon_sharesq_tw);
        fastaShotSocialAppIcons.put("snapchat", R.drawable.icon_sharesq_snapchat);
        fastaShotSocialAppIcons.put("line", R.drawable.icon_sharesq_line);

        fastaShotSocialAppIcons.put("skype", R.drawable.icon_sharesq_skype);
        fastaShotSocialAppIcons.put("hike", R.drawable.icon_sharesq_hike);
        fastaShotSocialAppIcons.put("KakaoTalk", R.drawable.icon_sharesq_kakaotalk);
        fastaShotSocialAppIcons.put("vk", R.drawable.icon_sharesq_vkontakte);
        fastaShotSocialAppIcons.put("zalo", R.drawable.icon_sharesq_zalo);
        fastaShotSocialAppIcons.put("messenger", R.drawable.icon_sharesq_fbmsg);

        fastaShotSocialAppIcons.put("gmail", R.drawable.icon_sharesq_gmail);
        fastaShotSocialAppIcons.put("odokassniki", R.drawable.icon_sharesq_odno);
        fastaShotSocialAppIcons.put("instagram", R.drawable.icon_sharesq_ig);


    }

    /**
     * Sets default social app icons for fasta screen shot feature
     */
    private void setDefaultFastaScreenShotSocialAppIcons() {

        fastaScreenShotSocialAppIcons.put("whatsapp", R.drawable.icon_share_whatsapp);
        fastaScreenShotSocialAppIcons.put("viber", R.drawable.icon_share_viber);
        fastaScreenShotSocialAppIcons.put("email", R.drawable.icon_share_email);
        fastaScreenShotSocialAppIcons.put("fbweb", R.drawable.icon_share_fbr);
        fastaScreenShotSocialAppIcons.put("hangout", R.drawable.icon_share_ghangout);
        fastaScreenShotSocialAppIcons.put("googleplus", R.drawable.icon_share_gplus);
        fastaScreenShotSocialAppIcons.put("linkedin", R.drawable.icon_share_link);
        fastaScreenShotSocialAppIcons.put("sms", R.drawable.icon_share_sms);
        fastaScreenShotSocialAppIcons.put("wechat", R.drawable.icon_share_wechat);
        fastaScreenShotSocialAppIcons.put("twitter", R.drawable.icon_share_tw);
        fastaScreenShotSocialAppIcons.put("snapchat", R.drawable.icon_share_snapchat);
        fastaScreenShotSocialAppIcons.put("line", R.drawable.icon_share_line);
        fastaScreenShotSocialAppIcons.put("KakaoTalk", R.drawable.icon_share_kakaotalk);
        fastaScreenShotSocialAppIcons.put("messenger", R.drawable.icon_share_fbmsg);
        fastaScreenShotSocialAppIcons.put("skype", R.drawable.icon_share_skype);
        fastaScreenShotSocialAppIcons.put("zalo", R.drawable.icon_share_zalo);
        fastaScreenShotSocialAppIcons.put("vk", R.drawable.icon_share_vkontakte);
        fastaScreenShotSocialAppIcons.put("hike", R.drawable.icon_share_hike);

        fastaScreenShotSocialAppIcons.put("gmail", R.drawable.icon_share_gmail);
        fastaScreenShotSocialAppIcons.put("odokassniki", R.drawable.icon_share_odno);
        fastaScreenShotSocialAppIcons.put("instagram", R.drawable.icon_share_ig);


    }


    /**
     * Here it add the details of default social channels
     */
    private void setDefaultSocialChannel() {
        setDefaultFastaScreenShotSocialAppIcons();
        setDefaultFastaShareSocialAppIcons();
        setDefaultFastaShotSocialAppIcons();

        HashMap<String, Object> whatsapp = new HashMap<>();
        whatsapp.put("name", "WhatsApp");
        whatsapp.put("display", true);
        whatsapp.put("img", APIUtils.getSocialAppIconResId("whatsapp", this));
        whatsapp.put("appname", "whatsapp");
        socialChannels.put(1, whatsapp);

        HashMap<String, Object> viber = new HashMap<>();
        viber.put("name", "Viber");
        viber.put("display", true);
        viber.put("img", APIUtils.getSocialAppIconResId("viber", this));
        viber.put("appname", "viber");
        socialChannels.put(2, viber);

        HashMap<String, Object> fbweb = new HashMap<>();
        fbweb.put("name", "Facebook");
        fbweb.put("display", true);
        fbweb.put("img", APIUtils.getSocialAppIconResId("fbweb", this));
        fbweb.put("appname", "fbweb");
        socialChannels.put(3, fbweb);

        HashMap<String, Object> wechat = new HashMap<>();
        wechat.put("name", "WeChat");
        wechat.put("display", true);
        wechat.put("img", APIUtils.getSocialAppIconResId("wechat", this));
        wechat.put("appname", "wechat");
        socialChannels.put(4, wechat);

        HashMap<String, Object> twitter = new HashMap<>();
        twitter.put("name", "Twitter");
        twitter.put("display", true);
        twitter.put("img", APIUtils.getSocialAppIconResId("twitter", this));
        twitter.put("appname", "twitter");
        socialChannels.put(5, twitter);

        HashMap<String, Object> hangout = new HashMap<>();
        hangout.put("name", "Hangout");
        hangout.put("display", true);
        hangout.put("img", APIUtils.getSocialAppIconResId("hangout", this));
        hangout.put("appname", "hangout");
        socialChannels.put(6, hangout);

        HashMap<String, Object> snapchat = new HashMap<>();
        snapchat.put("name", "SnapChat");
        snapchat.put("display", true);
        snapchat.put("img", APIUtils.getSocialAppIconResId("snapchat", this));
        snapchat.put("appname", "snapchat");
        socialChannels.put(7, snapchat);

        HashMap<String, Object> googleplus = new HashMap<>();
        googleplus.put("name", "GooglePlus");
        googleplus.put("display", true);
        googleplus.put("img", APIUtils.getSocialAppIconResId("googleplus", this));
        googleplus.put("appname", "googleplus");
        socialChannels.put(8, googleplus);

        HashMap<String, Object> email = new HashMap<>();
        email.put("name", "Email");
        email.put("display", true);
        email.put("img", APIUtils.getSocialAppIconResId("email", this));
        email.put("appname", "email");
        socialChannels.put(9, email);

        HashMap<String, Object> sms = new HashMap<>();
        sms.put("name", "SMS");
        sms.put("display", true);
        sms.put("img", APIUtils.getSocialAppIconResId("sms", this));
        sms.put("appname", "sms");
        socialChannels.put(10, sms);

        HashMap<String, Object> linkedin = new HashMap<>();
        linkedin.put("name", "Linkedin");
        linkedin.put("display", true);
        whatsapp.put("img", APIUtils.getSocialAppIconResId("linkedin", this));
        linkedin.put("appname", "linkedin");
        socialChannels.put(11, linkedin);

        HashMap<String, Object> line = new HashMap<>();
        line.put("name", "Line");
        line.put("display", true);
        line.put("img", APIUtils.getSocialAppIconResId("line", this));
        line.put("appname", "line");
        socialChannels.put(12, line);

        HashMap<String, Object> instagram = new HashMap<>();
        instagram.put("name", "Instagram");
        instagram.put("display", true);
        instagram.put("img", APIUtils.getSocialAppIconResId("instagram", this));
        instagram.put("appname", "instagram");
        socialChannels.put(13, instagram);

        HashMap<String, Object> skype = new HashMap<>();
        skype.put("name", "Skype");
        skype.put("display", true);
        skype.put("img", APIUtils.getSocialAppIconResId("skype", this));
        skype.put("appname", "skype");
        socialChannels.put(14, skype);

        HashMap<String, Object> hike = new HashMap<>();
        hike.put("name", "Hike");
        hike.put("display", true);
        hike.put("img", APIUtils.getSocialAppIconResId("hike", this));
        hike.put("appname", "hike");
        socialChannels.put(15, hike);

        HashMap<String, Object> kakaotalk = new HashMap<>();
        kakaotalk.put("name", "KakaoTalk");
        kakaotalk.put("display", true);
        kakaotalk.put("img", APIUtils.getSocialAppIconResId("KakaoTalk", this));
        kakaotalk.put("appname", "KakaoTalk");
        socialChannels.put(16, kakaotalk);

        HashMap<String, Object> vk = new HashMap<>();
        vk.put("name", "VK");
        vk.put("display", true);
        vk.put("img", APIUtils.getSocialAppIconResId("vk", this));
        vk.put("appname", "vk");
        socialChannels.put(17, vk);

        HashMap<String, Object> zalo = new HashMap<>();
        zalo.put("name", "Zalo");
        zalo.put("display", true);
        zalo.put("img", APIUtils.getSocialAppIconResId("zalo", this));
        zalo.put("appname", "zalo");
        socialChannels.put(18, zalo);

        HashMap<String, Object> odokassniki = new HashMap<>();
        odokassniki.put("name", "Odokassniki");
        odokassniki.put("display", true);
        odokassniki.put("img", APIUtils.getSocialAppIconResId("odokassniki", this));
        odokassniki.put("appname", "odokassniki");
        socialChannels.put(19, odokassniki);

        HashMap<String, Object> messenger = new HashMap<>();
        messenger.put("name", "Messenger");
        messenger.put("display", true);
        messenger.put("img", APIUtils.getSocialAppIconResId("messenger", this));
        messenger.put("appname", "messenger");
        socialChannels.put(20, messenger);

        HashMap<String, Object> gmail = new HashMap<>();
        gmail.put("name", "Gmail");
        gmail.put("display", true);
        gmail.put("img", APIUtils.getSocialAppIconResId("gmail", this));
        gmail.put("appname", "gmail");
        socialChannels.put(21, gmail);


    }

    /**
     * Sets the icons drawable to socials info based on current type..
     */
    public void setSocialIconsDrawableToSocials() {
        HashMap<Integer, HashMap<String, Object>> socialChannels = APIConfig.getInstance().getSocialChannels();
        for (Integer key : socialChannels.keySet()) {
            HashMap<String, Object> socialInfoHashmap = socialChannels.get(key);
            if (socialInfoHashmap != null && socialInfoHashmap.containsKey("appname")) {
                socialInfoHashmap.put("img", APIUtils.getSocialAppIconResId((String) socialInfoHashmap.get("appname"), this));

            }
        }
//        for (HashMap<String, Object> socialInfo : socialChannels) {
//            if (socialInfo != null && socialInfo.containsKey("appname")) {
//                socialInfo.put("img", APIUtils.getSocialAppIconResId((String) socialInfo.get("appname"), this));
//
//            }
//        }
    }

    /**
     * Gets the headers required for particular request.
     *
     * @param reqType Type of Url ex. ServiceAPIConstants.CREATE_LINK
     * @return returns the hash map containing the headers.
     */
    public HashMap<String, String> getHeaders(int reqType) {
        HashMap<String, String> headers = new HashMap<>();
        // create Base64 encodet string
        headers.put("Accept", accept);
        headers.put("user_key", user_key);

        switch (reqType) {
            case ServiceAPIConstants.CREATE_LINK:
//                headers.put("user_key", "");
                break;

            case ServiceAPIConstants.UPDATE_LINK:
//                headers.put("user_key", "");
                break;
            case ServiceAPIConstants.GET_LINK:
//                headers.put("user_key", "");
                break;
        }
        return headers;
    }
}
