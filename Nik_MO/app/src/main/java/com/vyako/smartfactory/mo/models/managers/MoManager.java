package com.vyako.smartfactory.mo.models.managers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.vyako.smartfactory.main.network.constants.NetworkConstants;
import com.vyako.smartfactory.main.network.listeners.APIResponseClientCallback;
import com.vyako.smartfactory.main.network.listeners.APIResponseManagerCallback;
import com.vyako.smartfactory.mo.dos.MOListDO;
import com.vyako.smartfactory.mo.dos.MoDO;
import com.vyako.smartfactory.mo.models.MoApiModel;
import com.vyako.smartfactory.mo.models.requestdos.RequestMODO;
import com.vyako.smartfactory.mo.models.responsedos.MODetailsResponseDO;
import com.vyako.smartfactory.mo.models.responsedos.MOListResponseDO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by ranjeetd on 6/9/2017.
 */

public class MoManager implements APIResponseManagerCallback {


    private APIResponseClientCallback apiResponseClientCallback;

    /**
     * private Instance of Mo Manager to follow singleTon Design pattern.
     */
    private static MoManager sMoManager;
    private JSONObject jsonFormatRequestBody;
    private DataHolder dataHolder;
    /**
     * Keeps the references of the client callbacks, so that call back will be uniquely identified with requestcode.
     */
    private Hashtable<Integer, APIResponseClientCallback> clientCallBacks = new Hashtable<>();

    private MoManager() {
        dataHolder = new DataHolder();
    }

    /**
     * Method that get the single instance of MoManger.
     *
     * @return sMoManager.
     */
    public static MoManager getInstance() {
        if (sMoManager == null) {
            sMoManager = new MoManager();
        }
        return sMoManager;
    }


    /**
     * Data Holder class to keep the global accessible data respect to MO.
     */
    private class DataHolder {
        /**
         * List of the MOS comes from the server.
         */
        private ArrayList<MoDO> mos;

        public ArrayList<MoDO> getMos() {
            return mos;
        }

        public void setMos(ArrayList<MoDO> mos) {
            this.mos = mos;
        }

        public MoDO getCurrentMO() {
            return currentMO;
        }

        public void setCurrentMO(MoDO currentMO) {
            this.currentMO = currentMO;
        }

        /**
         * The Mo which is being displayed currently.
         */
        private MoDO currentMO;
    }


    public void sendRequestToGetMoList(@NonNull APIResponseClientCallback apiResponseClientCallback) throws JSONException {
        //first put callbacks to the client hash table.
        clientCallBacks.put(NetworkConstants.RequestCode.GET_MO_LIST, apiResponseClientCallback);
        MoApiModel moApiModel = new MoApiModel(this);
        RequestMODO requestMODO = new RequestMODO.Builder()
                .status(1)
                .zoneId(1)
                .startDate("2017-04-28")
                .endDate("2017-05-14").build();

        JSONObject requestBody = requestMODO.getListRequestBody();
        if (requestBody != null) {
            moApiModel.requestForMoList(NetworkConstants.RequestCode.GET_MO_LIST, true, requestBody);
        }
    }

    /**
     * Sends the request to get mo details to the MO model
     *
     * @param moId
     * @param apiResponseClientCallback
     */
    public void sendRequestToGetMoDetails(int moId, APIResponseClientCallback apiResponseClientCallback) {
        //first put callbacks to the client hash table.
        clientCallBacks.put(NetworkConstants.RequestCode.GET_MO, apiResponseClientCallback);
        MoApiModel moApiModel = new MoApiModel(this);
        moApiModel.requestForMoDetails(NetworkConstants.RequestCode.GET_MO, true, moId, null);
    }

    @Override
    public void onSuccessResponse(@NetworkConstants.RequestCode int requestId, int statusCode, String message, String response,
                                  @Nullable Object object) {
        switch (requestId) {
            case NetworkConstants.RequestCode.GET_MO_LIST:
                onMoListResponse(requestId, statusCode, message, response, object);
                break;
            case NetworkConstants.RequestCode.GET_MO:
                onGetMOResponse(requestId, statusCode, message, response, object);
                break;
            default:
                throw new IllegalStateException("Invalid Request code id");
        }

    }

    /**
     * Calls if GetMO response details response comes successfully.
     *
     * @param requestId
     * @param statusCode
     * @param message
     * @param response
     * @param object
     */
    private void onGetMOResponse(int requestId, int statusCode, String message, String response, Object object) {
        Gson gson = new Gson();
        MODetailsResponseDO moDetailsResponseDO = gson.fromJson(response, MODetailsResponseDO.class);
        MoDO moDO = null;
        if (moDetailsResponseDO != null) {
            moDO = moDetailsResponseDO.getResponse();

        }
        APIResponseClientCallback apiResponseClientCallback = clientCallBacks.get(requestId);
        if (apiResponseClientCallback != null) {
            apiResponseClientCallback.onSuccessResponse(requestId, statusCode, message, response);
        }
    }

    private void onMoListResponse(int requestId, int status, String message, String responseString, Object object) {
        System.out.println("<< responseString" + responseString);
        Gson gson = new Gson();
        MOListResponseDO moListResponseDO = gson.fromJson(responseString, MOListResponseDO.class);
        MOListDO moListDO = null;
        if (moListResponseDO != null) {
            moListDO = moListResponseDO.getResponse();

        }
        APIResponseClientCallback apiResponseClientCallback = clientCallBacks.get(requestId);
        if (apiResponseClientCallback != null) {
            apiResponseClientCallback.onSuccessResponse(requestId, status, message, moListDO);
        }
    }

    @Override
    public void onFailureResponse(@NetworkConstants.RequestCode int requestId, @NonNull String errorString) {
        APIResponseClientCallback apiResponseClientCallback = clientCallBacks.get(requestId);
        if (apiResponseClientCallback != null) {
            apiResponseClientCallback.onFailureResponse(requestId, errorString);
        }
    }


}