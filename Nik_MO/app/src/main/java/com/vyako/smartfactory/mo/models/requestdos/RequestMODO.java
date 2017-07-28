package com.vyako.smartfactory.mo.models.requestdos;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Keeps the all parameters that requires to build request body.
 * Created by nikhilm on 23-Jun-17.
 */

public class RequestMODO {

    private int status;
    private int zone_id;
    private String start_date;
    private String end_date;

    /**
     * methods that return the json format request body for Filtered Mo list.
     * jsonFormatRequestBody.put("start_date", "2017-04-28");
     * jsonFormatRequestBody.put("end_date", "2017-05-14");
     *
     * @return jsonFormatRequestBody
     * @throws JSONException
     */
    public JSONObject getListRequestBody() {
        JSONObject jsonFormatRequestBody = new JSONObject();
        try {
            jsonFormatRequestBody.put("status", status);
            jsonFormatRequestBody.put("zone_id", zone_id);
            jsonFormatRequestBody.put("start_date", start_date);
            jsonFormatRequestBody.put("end_date", end_date);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonFormatRequestBody;
    }


    public static class Builder {
        private RequestMODO requestMODO;

        public Builder() {
            requestMODO = new RequestMODO();
        }

        public Builder status(int status) {
            requestMODO.status = status;
            return this;
        }

        public Builder zoneId(int zoneId) {
            requestMODO.zone_id = zoneId;
            return this;
        }

        public Builder startDate(String startDate) {
            requestMODO.start_date = startDate;
            return this;
        }

        public Builder endDate(String endDate) {
            requestMODO.end_date = endDate;
            return this;
        }

        public RequestMODO build() {
            return requestMODO;
        }
    }


}
