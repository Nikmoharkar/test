package com.vyako.smartfactory.main.network.responsedos;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kaushik on 1/24/2017.
 */
public class CommonResponse {
    private int status_code;
    private String message;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    /**
     * Format the JSON format
     *
     * @return
     */
    public JSONObject getJSONFormat(int code, String message) {
        JSONObject mediaJSON = new JSONObject();
        try {
            mediaJSON.put("status_code", code);
            mediaJSON.put("message", message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mediaJSON;
    }
}
