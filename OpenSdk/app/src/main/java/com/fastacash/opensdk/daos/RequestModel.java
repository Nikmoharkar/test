package com.fastacash.opensdk.daos;

import java.util.HashMap;

/**
 * Created by Nikhil on 10/19/2015.
 */
public class RequestModel {

    /**
     * Type of request made by enduser.
     */
    private int reqType;

    /**
     * method type of request.
     */
    private byte methodType;
    /**
     * hashmap to keep required headers for current request.
     */
    private HashMap<String, String> headers = new HashMap<>();

    /**
     * Keeps the all path params requires for requesting service call.
     */
    private HashMap<String, String> pathParams = new HashMap<>();
    /**
     * Keeps the all query params requires for requesting service call.
     */
    private HashMap<String, String> queryParams = new HashMap<>();
    /**
     * Keeps the all body params requires for requesting service call.
     */
    private HashMap<String, Object> bodyParams = new HashMap<>();


    public HashMap<String, Object> getBodyParams() {
        return bodyParams;
    }


    public int getReqType() {
        return reqType;
    }

    public void setReqType(int reqType) {
        this.reqType = reqType;
    }

    public byte getMethodType() {
        return methodType;
    }

    public void setMethodType(byte methodType) {
        this.methodType = methodType;
    }

    public void addBodyParam(String key, Object value) {
        this.bodyParams.put(key, value);
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public HashMap<String, String> getPathParams() {
        return pathParams;
    }

    public void addPathParam(String key, String value) {
        this.pathParams.put(key, value);
    }

    public HashMap<String, String> getQueryParams() {
        return queryParams;
    }

    public void addQueryParams(String key, String value) {
        this.queryParams.put(key, value);
    }
}
