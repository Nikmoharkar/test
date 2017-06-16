package com.fastacash.opensdk.controller;


import android.app.Application;
import android.content.Context;

import com.fastacash.opensdk.R;
import com.fastacash.opensdk.callbacks.CallbackImpl;
import com.fastacash.opensdk.daos.RequestModel;
import com.squareup.okhttp.OkHttpClient;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * @author Gaurav Gupta <gaurav@thegauravgupta.com>
 * @since 27/May/2015
 */
public class APIServices {
    //Address URL
    private static final String ADDRESS_URL = "http://finoculus.com/openapi/api";
    private static final String AUTH_CREDENTIALS = "demo:";


    private static RestAdapter createAdapter(Context context, final int reqType) {
        OkHttpClient okHttpClient = new OkHttpClient();
        // loading CAs from an InputStream
        InputStream cert = context.getResources().openRawResource(R.raw.mycert);
        Certificate ca;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            ca = cf.generateCertificate(cert);
            cert.close();

            // creating a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            // creating a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            // creating an SSLSocketFactory that uses our TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            okHttpClient.setSslSocketFactory(sslContext.getSocketFactory());

        } catch (Exception exception) {

        }

        return new RestAdapter.Builder()
                .setEndpoint(APIConfig.HOST_URL)
                .setClient(new OkClient(okHttpClient))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        //add all headers here.
                        HashMap<String, String> headers = APIConfig.getInstance().getHeaders(reqType);
                        //gets the all headers key here.
                        Set<String> headerKeys = headers.keySet();
                        for (String key : headerKeys) {
                            request.addHeader(key, headers.get(key));
                        }
                    }
                }).build();

    }

    /**
     * @return FastacashService
     */
    public static FastacashService getFastacashService(int reqType) {
        return createAdapter(APIConfig.getInstance().getContext(), reqType).create(FastacashService.class);
    }

    //Create a service interface to query API
    public interface FastacashService {
        @FormUrlEncoded
        @POST("/register")
        void register(@Field("id") String id, Callback<Object> response);

        @PUT(ADDRESS_URL)
        void update();

        @POST("/links/3/hju")
        public void createLink(@Header("user_key") String user_key, @Body RequestModel requestModel, CallbackImpl response);

        @POST("/links/{linkcode}")
        public void updateLink(@Header("user_key") String user_key, @Path("linkcode") String linkcode, @Body RequestModel requestModel, CallbackImpl response);

        @GET("/links{linkcode}")
        public void getLink(@Header("user_key") String user_key, @Path("linkcode") String linkcode, @Body RequestModel requestModel, CallbackImpl response);

        @POST("/{path}")
        public void postData(@Path(value = "path", encode = false) String path, @Body HashMap<String, Object> body, Callback<HashMap<String, Object>> response);

        @GET("/{path}")
        public void getData(@Path(value = "path", encode = false) String path, @Body HashMap<String, Object> body, Callback<HashMap<String, Object>> response);

        @PUT("/{path}")
        public void putData(@Path(value = "path", encode = false) String path, @Body HashMap<String, Object> body, Callback<HashMap<String, Object>> response);
    }
}

