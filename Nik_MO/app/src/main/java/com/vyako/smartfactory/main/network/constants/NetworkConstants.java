package com.vyako.smartfactory.main.network.constants;

import android.support.annotation.IntDef;

import com.vyako.smartfactory.main.BuildConfig;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.vyako.smartfactory.main.network.constants.NetworkConstants.RequestCode.GET_MO;
import static com.vyako.smartfactory.main.network.constants.NetworkConstants.RequestCode.GET_MO_LIST;
import static com.vyako.smartfactory.main.network.constants.NetworkConstants.RequestCode.GET_USER_LOGOUT;
import static com.vyako.smartfactory.main.network.constants.NetworkConstants.RequestCode.POST_USER_LOGIN;
import static com.vyako.smartfactory.main.network.constants.NetworkConstants.ResponseCode.INVALID_MACHINE;
import static com.vyako.smartfactory.main.network.constants.NetworkConstants.ResponseCode.MACHINE_UNDER_NPR;
import static com.vyako.smartfactory.main.network.constants.NetworkConstants.ResponseCode.SESSION_EXPIRE;
import static com.vyako.smartfactory.main.network.constants.NetworkConstants.ResponseCode.SUCCESS;
import static com.vyako.smartfactory.main.network.constants.NetworkConstants.ResponseCode.USER_NOT_FOUND;

/**
 * Created by kaushik on 25-May-17.
 * Maintain the network level constant
 */

public interface NetworkConstants {

    public interface Headers {
        String X_AUTH_TOKEN = "X-AUTH-TOKEN";
        String BASIC_AUTH_TOKEN = "Authorization";
    }

    public interface URL {
        String DEMO_URL = "http://staging.vyako.com/shopvilla/addresses/getAddressList/3.json";
        String PRIMARY_BASE_URL = BuildConfig.BASE_URL;

//        String BASE_URL = LocalModel.getInstance().getHostUrl() != null ? LocalModel.getInstance().getHostUrl() : PRIMARY_BASE_URL;

        String BASE_URL = BuildConfig.BASE_URL;
        String USER = "/user";
        String URL_USER_LOGIN = BASE_URL + USER + "/login";
        String URL_USER_LOGOUT = BASE_URL + USER + "/logout";
        String MO = "/mo?";
        String PAGE = "page=1";
        String LIMIT = "&limit=10000";
        String URL_FILTERED_MO_LIST_PAGINATION = BASE_URL + MO + PAGE + LIMIT;
        String GET_MO = BASE_URL + "/mo/";
    }


    /**
     * Application Controller events ids
     * Maintain all app level event ids and def of that event ids
     */
    @Retention(RetentionPolicy.CLASS)
    @IntDef({GET_MO, GET_MO_LIST, POST_USER_LOGIN, GET_USER_LOGOUT})
    @interface RequestCode {
        int GET_MO_LIST = 222;
        int POST_USER_LOGIN = 111;
        int GET_USER_LOGOUT = 112;
        int GET_MO = 333;


    }

    @Retention(RetentionPolicy.CLASS)
    @IntDef({SUCCESS, SESSION_EXPIRE, USER_NOT_FOUND, INVALID_MACHINE, MACHINE_UNDER_NPR})
    @interface ResponseCode {
        int SUCCESS = 200;
        int SESSION_EXPIRE = 1017;
        int USER_NOT_FOUND = 1006;
        int INVALID_MACHINE = 1051;
        int MACHINE_UNDER_NPR = 1078;
    }
}
