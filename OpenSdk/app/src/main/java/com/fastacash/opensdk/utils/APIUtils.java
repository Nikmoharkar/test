package com.fastacash.opensdk.utils;

import com.fastacash.opensdk.constants.ServiceAPIConstants;
import com.fastacash.opensdk.controller.APIConfig;

import java.util.HashMap;

/**
 * Created by nikhil on 10/21/2015.
 */
public class APIUtils {

//    public static String getPath

    public static String getPath(int reqType, HashMap<String, String> pathParams) {
        String path = constructEndPath(reqType, pathParams);
        return path;
    }

    /**
     * Construct the end path for url.
     *
     * @param reqType
     * @param pathParams
     * @return
     */
    private static String constructEndPath(int reqType, HashMap<String, String> pathParams) {
        String endPath = "";
        //gets the path fields here.
        String[] pathFields = APIConfig.getInstance().getPathFields(reqType);
        if (pathFields != null && pathParams != null) {
            for (String pathKey : pathParams.keySet()) {
                //now travelling to path fields to find for current key.
                for (int pathFieldsInd = 0; pathFieldsInd < pathFields.length; pathFieldsInd++) {
                    if (pathFields[pathFieldsInd].equals(pathKey)) {
                        //if the path key is matched then replacing it with value from path params.
                        pathFields[pathFieldsInd] = pathParams.get(pathKey);
                    }
                }
            }
            endPath = concat("/", pathFields);
            //removing slash from first index of the constructed path.
            if (endPath.startsWith("/")) {
                endPath = endPath.substring(1);
            }
        }
        return endPath;

    }

    /**
     * Concats the splitted strings to form single separated by provided separator.
     *
     * @param separator
     * @param array
     * @return
     */
    public static String concat(String separator, String[] array) {
        String concatedString = "";
        for (String str : array) {
            concatedString += "/" + str;
        }
        return concatedString;
    }


    /**
     * Based on the current app value & socialAppName retrieving & returning its drawable res id.
     *
     * @param socialAppName
     * @return
     */
    public static int getSocialAppIconResId(String socialAppName, APIConfig apiConfig) {
        String app = apiConfig.getApp();
        HashMap<String, Integer> fastaShareSocialAppIcons = apiConfig.getFastaShareSocialAppIcons();
        HashMap<String, Integer> fastaShotSocialAppIcons = apiConfig.getFastaShotSocialAppIcons();
        HashMap<String, Integer> fastaScreenShotSocialAppIcons = apiConfig.getFastaScreenShotSocialAppIcons();

        if (app != null) {
            if (app.equals(ServiceAPIConstants.LINK_PURPOSE_FAST_A_SHARE)) {
                if (fastaShareSocialAppIcons.containsKey(socialAppName)) {
                    return fastaShareSocialAppIcons.get(socialAppName);
                }

            } else if (app.equals(ServiceAPIConstants.LINK_PURPOSE_FAST_A_SHORT)) {

            } else if (app.equals(ServiceAPIConstants.LINK_PURPOSE_FAST_A_SSHORT)) {

            } else if (app.equals(ServiceAPIConstants.LINK_PURPOSE_FAST_A_SHOT)) {
                if (fastaShotSocialAppIcons.containsKey(socialAppName)) {
                    return fastaShotSocialAppIcons.get(socialAppName);
                }

            } else if (app.equals(ServiceAPIConstants.LINK_PURPOSE_FAST_A_SCREEN_SHOT)) {
                if (fastaScreenShotSocialAppIcons.containsKey(socialAppName)) {
                    return fastaScreenShotSocialAppIcons.get(socialAppName);
                }
            }
        }
        return -1;
    }
}
