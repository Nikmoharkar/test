package com.fastacash.opensdk;

import android.app.Activity;
import android.view.View;

import com.fastacash.opensdk.constants.ServiceAPIConstants;
import com.fastacash.opensdk.controller.APIConfig;
import com.fastacash.opensdk.controller.APIServiceManager;
import com.fastacash.opensdk.plugin.Plugin;
import com.fastacash.opensdk.template.FastaShortTemplate;
import com.fastacash.opensdk.template.FastaGuardTemplate;
import com.fastacash.opensdk.template.FastaPicTemplate;
import com.fastacash.opensdk.template.FastaScreenTemplate;
import com.fastacash.opensdk.template.FastaShareTemplate;
import com.fastacash.opensdk.template.Template;
import com.fastacash.opensdk.template.TemplateManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit.Callback;

/**
 * Created by nikhil on 10/26/2015.
 */
public class OpenSDK {
    private String message;
    private String subject;
    private String fastaLink;
    private Object others;


    public void config(JSONObject options) {
        boolean isCustomSocialListAdded = false;
        APIConfig apiConfig = APIConfig.getInstance();
        if (options != null) {
            if (options.has("buttonElement")) {
                try {
                    apiConfig.setButtonElement((View) options.get("buttonElement"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (options.has("initElement")) {
                try {
                    apiConfig.setInitElement(options.get("initElement"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (options.has("app")) {
                try {
                    apiConfig.setApp((String) options.get("app"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (options.has("resId")) {
                try {
                    apiConfig.setResId((int) options.get("resId"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (options.has("url")) {
                try {
                    apiConfig.setUrl((String) options.get("url"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (options.has("customUrl")) {
                try {
                    apiConfig.setCustomUrl((String) options.get("customUrl"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (options.has("clickCount")) {
                try {
                    apiConfig.setClickCount((String) options.get("clickCount"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (options.has("linkExpiry")) {
                try {
                    apiConfig.setLinkExpiry((String) options.get("linkExpiry"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (options.has("social")) {
                try {
                    isCustomSocialListAdded = replaceSocialAppList(options.get("social"), apiConfig);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (options.has("userKey")) {
                try {
                    apiConfig.setUserKey((String) options.get("userKey"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (!isCustomSocialListAdded) {
                //based on the app & available social icons setting icons to set of social infos.
                APIConfig.getInstance().setSocialIconsDrawableToSocials();
            }

            if (options.has("PIN")) {
                try {
                    Object object = options.get("PIN");
                    if (object != null) {
                        apiConfig.setPin((String) options.get("PIN"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //based on the app & available social icons setting icons to set of social infos.
            APIConfig.getInstance().setSocialIconsDrawableToSocials();
            registerPlugins();

        }

//        opensdk.initWithConfig({
//                "buttonElement" : "btnDiv",
//                "initElement" : "data-init",
//                "debug" : false,
//                "pin" : true,
//                "apiUrl" : "",
//                "app" : ""
//        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFastaLink() {
        return fastaLink;
    }

    public void setFastaLink(String fastaLink) {
        this.fastaLink = fastaLink;
    }

    public Object getOthers() {
        return others;
    }

    public void setOthers(Object others) {
        this.others = others;
    }

    /**
     * Retrive the user social order and replace the existing order with new one
     *
     * @param social
     * @param apiConfig
     */
    private void AddOrderToExistingSocial(Object social, APIConfig apiConfig) {
        HashMap<Integer, HashMap<String, Object>> socialChannels = apiConfig.getSocialChannels();
        if (social instanceof HashMap) {
            HashMap<Integer, HashMap<String, Object>> listOfSocialApp = (HashMap<Integer, HashMap<String, Object>>) social;
            if (listOfSocialApp != null) {
                for (Integer key : listOfSocialApp.keySet()) {
                    HashMap<String, Object> customOrder = listOfSocialApp.get(key);
                    socialChannels.put(key, customOrder);
                }
            }
        }
    }


    /**
     * Replace the user old social order with new one
     *
     * @param social
     * @param apiConfig
     */
    private boolean replaceSocialAppList(Object social, APIConfig apiConfig) {
        if (social instanceof HashMap) {
            HashMap<Integer, HashMap<String, Object>> listOfSocialApp = (HashMap<Integer, HashMap<String, Object>>) social;
            apiConfig.setSocialChannels(listOfSocialApp);
            return true;
        }
        return false;
    }
//private void replaceNewSocial

    /**
     * Here registers the plugins
     */
    private void registerPlugins() {
        HashMap<Integer, HashMap<String, Object>> socialChannels = APIConfig.getInstance().getSocialChannels();
        if (socialChannels != null) {
            for (Integer key : socialChannels.keySet()) {
                HashMap<String, Object> socialDetails = socialChannels.get(key);
                if (socialDetails != null && socialDetails.containsKey("name")) {
                    String socialName = (String) socialDetails.get("name");
                    socialName += "Plugin";

                    try {
                        Class<?> aClass = Class.forName("com.fastacash.opensdk." + socialName);
                        Object object = aClass.newInstance();
                        if (object instanceof Plugin) {
                            Plugin plugin = (Plugin) object;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void init() {
        Template template = null;
        String app = APIConfig.getInstance().getApp();
        if (app != null) {
            if (app.equals(ServiceAPIConstants.LINK_PURPOSE_FAST_A_SHARE)) {
                FastaShareTemplate fastaShareTemplate = new FastaShareTemplate();
                template = fastaShareTemplate;

            } else if (app.equals(ServiceAPIConstants.LINK_PURPOSE_FAST_A_SCREEN_SHOT)) {
                FastaScreenTemplate fastaScreenShotTemplate = new FastaScreenTemplate();
                template = fastaScreenShotTemplate;
            }
            if (app.equals(ServiceAPIConstants.LINK_PURPOSE_FAST_A_SHOT)) {
                FastaPicTemplate fastaPicTemplate = new FastaPicTemplate();
                template = fastaPicTemplate;

            } else if (app.equals(ServiceAPIConstants.LINK_PURPOSE_FAST_A_SHORT)) {
                FastaShortTemplate fastaShortTemplate = new FastaShortTemplate();
                template = fastaShortTemplate;
            }
            if (app.equals(ServiceAPIConstants.LINK_PURPOSE_FAST_A_SSHORT)) {
                FastaGuardTemplate fastaGuardTemplate = new FastaGuardTemplate();
                template = fastaGuardTemplate;

            }

        }
        TemplateManager templateManager = new TemplateManager();
        if (template != null) {
            templateManager.loadTemplate(template);
        }
    }

    public void initWithConfig(JSONObject options) {
        config(options);
        init();
    }

    /**
     * Creates the link with provided parameters by requesting to the server.
     */
    public void createLink(HashMap<String, Object> requestData, Callback<HashMap<String, Object>> callback) {
        APIServiceManager.getInstance().createLink(requestData, callback);
    }

    /**
     * Update the link with provided parameters by requesting to the server.
     */

    public void updateLink(Activity context, HashMap<String, Object> requestData, Callback<HashMap<String, Object>> callback) {
        APIServiceManager.getInstance().updateLink(context, requestData, callback);
    }

    /**
     * Fetches the link with provided parameters by requesting to the server.
     */
    public void fetchLink(Activity context, HashMap<String, Object> requestData, Callback<HashMap<String, Object>> callback) {
        APIServiceManager.getInstance().fetchLink(context, requestData, callback);
    }

}
