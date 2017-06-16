package com.fastacash.opensdk.template;

import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fastacash.Constant;
import com.fastacash.opensdk.R;
import com.fastacash.opensdk.constants.ServiceAPIConstants;
import com.fastacash.opensdk.controller.APIConfig;
import com.fastacash.opensdk.controller.APIServiceManager;
import com.fastacash.opensdk.utils.ErrorHandler;
import com.fastacash.opensdk.utils.Utility;
import com.google.gson.internal.LinkedTreeMap;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by nikhil on 10/27/2015.
 */
public  class FastaGuardTemplate extends Template {
    private Button fastaSecureShortenUrlButton;
    private Button fastaSecureCopyButton;
    private LinearLayout fastasSecuerUrlLinearLayout;
    private RelativeLayout fastSecureUrlRelativeLayout;
    private RelativeLayout unlimitedClicksLayout;
    private CheckBox unlimitedClicksCheckBox;
    private EditText numberOfClicksEditText;
    private EditText fastSecureUrlEdittext;
    private TextView fastasSecuerUrlTextVIew;
    private EditText pinEditText;
    private EditText expirationDaysEditText;
    //    private EditText dateEditText;
//    private EditText yearEditText;
//    private EditText monthEditText;
//    private EditText hourEditText;
//    private EditText minuteEditText;
    private EditText fastaLinkSecureEdittext;

    public FastaGuardTemplate() {
        init(R.layout.fasta_secure_short);
    }

    @Override
    protected void init(int resId) {
        super.init(resId);
    }

    @Override
    protected void initViews() {
        super.initViews();
        fastaSecureShortenUrlButton = (Button) templateView.findViewById(R.id.fastaSecureShortenUrlButton);
        fastaSecureShortenUrlButton = (Button) templateView.findViewById(R.id.fastaSecureShortenUrlButton);
        fastaSecureCopyButton = (Button) templateView.findViewById(R.id.fastaSecureCopyButton);
        fastasSecuerUrlLinearLayout = (LinearLayout) templateView.findViewById(R.id.fastasSecuerUrlLinearLayout);
        fastSecureUrlRelativeLayout = (RelativeLayout) templateView.findViewById(R.id.fastSecureUrlRelativeLayout);
        unlimitedClicksLayout = (RelativeLayout) templateView.findViewById(R.id.unlimitedClicksLayout);
        unlimitedClicksCheckBox = (CheckBox) templateView.findViewById(R.id.unlimitedClicksCheckBox);
        numberOfClicksEditText = (EditText) templateView.findViewById(R.id.numberOfClicksEditText);
        fastSecureUrlEdittext = (EditText) templateView.findViewById(R.id.fastSecureUrlEdittext);
        fastasSecuerUrlTextVIew = (TextView) templateView.findViewById(R.id.fastasSecuerUrlTextVIew);
        pinEditText = (EditText) templateView.findViewById(R.id.pinEditText);
        expirationDaysEditText = (EditText) templateView.findViewById(R.id.expirationDaysEditText);

        fastaLinkSecureEdittext = (EditText) templateView.findViewById(R.id.fastaLinkSecureEdittext);

    }

    @Override
    protected void setOnClickListeners() {
        super.setOnClickListeners();
        fastaSecureShortenUrlButton.setOnClickListener(this);
        fastaSecureCopyButton.setOnClickListener(this);
        unlimitedClicksLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
        if (i == R.id.fastaSecureShortenUrlButton) {
            //Toast.makeText(APIConfig.getInstance().getContext(), "SHORT BUTTON CLICKED", Toast.LENGTH_SHORT).show();
            onShortenUrlClick();

        } else if (i == R.id.fastaSecureCopyButton) {
            Utility.copyToClipboard(fastasSecuerUrlTextVIew.getText().toString(), APIConfig.getInstance().getContext());

        } else if (i == R.id.unlimitedClicksLayout) {
            onUnlimitedClicksLayoutClick();

        }
    }

    @Override
    protected void setOnTextChangeListner() {
        super.setOnTextChangeListner();
        numberOfClicksEditText.addTextChangedListener(this);
        fastSecureUrlEdittext.addTextChangedListener(this);
        fastasSecuerUrlTextVIew.addTextChangedListener(this);
        pinEditText.addTextChangedListener(this);
        expirationDaysEditText.addTextChangedListener(this);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        super.afterTextChanged(editable);
        fastaSecureShortenUrlButton.setEnabled(true);
        fastaSecureShortenUrlButton.setFocusable(true);

    }

    /**
     * Updates the fields of config from respective textfields.
     */
    private void updateAPiConfig() {
        String tempValue = pinEditText.getText().toString();

        if (tempValue != null && !tempValue.isEmpty()) {
            APIConfig.getInstance().setPin(tempValue);
        }
        tempValue = fastaLinkSecureEdittext.getText().toString();

        if (tempValue != null && !tempValue.isEmpty()) {
            APIConfig.getInstance().setCustomUrl(tempValue);
        }

        tempValue = getNoOfClicks();
        if (tempValue != null && !tempValue.isEmpty()) {
            APIConfig.getInstance().setClickCount(tempValue);
        }

        tempValue = getExpirationDate();
        if (tempValue != null && !tempValue.isEmpty()) {
            APIConfig.getInstance().setLinkExpiry(tempValue);
        }

        tempValue = fastSecureUrlEdittext.getText().toString();
        if (tempValue != null && !tempValue.isEmpty()) {
            APIConfig.getInstance().setUrl(tempValue);
        }


    }

    /**
     * This method get called on action of shorten url buttton
     */
    private void onShortenUrlClick() {
        Context context = APIConfig.getInstance().getContext();
        if (isValid()) {
            fastSecureUrlEdittext.setError(null);
            updateAPiConfig();
            showProgressDialog();

            HashMap<String, Object> requestDetails = new HashMap<>();
            requestDetails.put("url", APIConfig.getInstance().getUrl());
            requestDetails.put("link_purpose", ServiceAPIConstants.LINK_PURPOSE_FAST_A_SSHORT);
            requestDetails.put("PIN", APIConfig.getInstance().getPin());
            requestDetails.put("max_clicks", APIConfig.getInstance().getClickCount());
            requestDetails.put("expires_in_days", APIConfig.getInstance().getLinkExpiry());

            if (Utility.isInternetConnectionAvaliable(context)) {
                reqToCreateLink(requestDetails);
            } else {
                progressDialog.dismiss();
                ErrorHandler errorHandler = new ErrorHandler(context);
                errorHandler.setType(Constant.ERROR_TYPE_NETWORK);
                errorHandler.setMessage(context.getResources().getString(R.string.network_not_available));
                errorHandler.showError();
            }
        } else {
            fastaSecureShortenUrlButton.setEnabled(false);
            fastaSecureShortenUrlButton.setFocusable(false);
        }
    }

    /**
     * It checks that all fields are valid or not
     *
     * @return
     */
    private boolean isValid() {
        Context context = APIConfig.getInstance().getContext();
        ErrorHandler errorHandler = null;
        if (context != null) {
            errorHandler = new ErrorHandler(context);
        }
        String errorMessage = null;
        if ((errorMessage = Utility.isValidUrl(fastSecureUrlEdittext.getText().toString(), context)) != null) {
            if (errorHandler != null) {
                errorHandler.setType(Constant.ERROR_TYPE_CLIENT_VALIDATION_SIDE);
                errorHandler.setMessage(errorMessage);
                errorHandler.showError();
            }
            return false;
        }

        if ((errorMessage = Utility.isValidPIN(pinEditText.getText().toString(), context)) != null) {
            if (errorHandler != null) {
                errorHandler.setType(Constant.ERROR_TYPE_CLIENT_VALIDATION_SIDE);
                errorHandler.setMessage(errorMessage);
                errorHandler.showError();
//                Utility.showAlertDialog(context, context.getResources().getString(R.string.pinMendatoryText), context.getResources().getString(R.string.okCapsText));

            }
            return false;
        }
        String clicksCount = numberOfClicksEditText.getText().toString();
        if (clicksCount != null && clicksCount.length() > 0) {
            try {
                if ((errorMessage = Utility.isValidClicks(Integer.parseInt(clicksCount), context)) != null) {
                    if (errorHandler != null) {
                        errorHandler.setType(Constant.ERROR_TYPE_CLIENT_VALIDATION_SIDE);
                        errorHandler.setMessage(errorMessage);
                        errorHandler.showError();
//                        Utility.showAlertDialog(context, context.getResources().getString(R.string.maxClicksText), context.getResources().getString(R.string.okCapsText));

                    }
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        String daysCount = expirationDaysEditText.getText().toString();
        if (daysCount != null && daysCount.length() > 0) {

            try {
                if ((errorMessage = Utility.isValidExpiryDays(Integer.parseInt(daysCount), context)) != null) {
                    if (errorHandler != null) {
                        errorHandler.setType(Constant.ERROR_TYPE_CLIENT_VALIDATION_SIDE);
                        errorHandler.setMessage(errorMessage);
                        errorHandler.showError();
//                        Utility.showAlertDialog(context, context.getResources().getString(R.string.maxExpirationDaysText), context.getResources().getString(R.string.okCapsText));

                    }
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * Requesting Server to create link.
     */
    private void reqToCreateLink(HashMap<String, Object> requestDetails) {
        APIServiceManager.getInstance().createLink(requestDetails, new Callback<HashMap<String, Object>>() {
            @Override
            public void success(HashMap<String, Object> responseData, Response response) {
                progressDialog.dismiss();
                if (responseData != null && responseData.containsKey("link")) {
                    System.out.println("Link: " + responseData.get("link"));
                    LinkedTreeMap<String, Object> linkData = (LinkedTreeMap<String, Object>) responseData.get("link");
                    if (linkData != null && linkData.containsKey("code")) {
                        String shortenUrl = getShortenUrl(APIConfig.getInstance().getCustomUrl(), (String) linkData.get("code"));
                        fastasSecuerUrlTextVIew.setText((String) shortenUrl);

                        responseData.put("shortenURL", shortenUrl);
                        showSecureShortenUrlResult();


                    }
                }
//                if (responseData.containsKey("code")) {
//                    String shortenUrl = getShortenUrl(customizeUrl, (String) responseData.get("code"));
//                    fastasSecuerUrlTextVIew.setText((String) shortenUrl);
//
//                    responseData.put("shortenURL", shortenUrl);
//                    showSecureShortenUrlResult();
//
//                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismiss();
                ErrorHandler errorHandler = new ErrorHandler();
                errorHandler.setType(Constant.ERROR_TYPE_NETWORK);
                errorHandler.setMessage(error.getMessage());
                errorHandler.showError();
            }
        });
    }

    /**
     * This method get called when we click on unlimited clicks
     */
    private void onUnlimitedClicksLayoutClick() {
        unlimitedClicksCheckBox.setChecked(!unlimitedClicksCheckBox.isChecked());
        if (unlimitedClicksCheckBox.isChecked()) {
            numberOfClicksEditText.setEnabled(false);
            numberOfClicksEditText.setText("");
        } else {
            numberOfClicksEditText.setEnabled(true);
        }
    }

    /**
     * this method is used to get the no of clicks
     *
     * @return
     */
    private String getNoOfClicks() {
        String clicks = "";
        if (unlimitedClicksCheckBox.isChecked()) {
            clicks = "";
        } else {
            clicks = numberOfClicksEditText.getText().toString();
        }

        return clicks;
    }

    /**
     * This method is used to collect the expiration date from its respective fields and after that combine it
     *
     * @return
     */
    private String getExpirationDate() {
//        String expirationDate = "";
        String expirationDays = expirationDaysEditText.getText().toString();
//        String year = yearEditText.getText().toString();
//        String month = monthEditText.getText().toString();
//        String date = dateEditText.getText().toString();
//        String hours = hourEditText.getText().toString();
//        String minutes = minuteEditText.getText().toString();
//        expirationDate = year + " " + month + " " + date + " " + hours + " " + minutes;
        return expirationDays;
    }

    /**
     * Shows the secure shorten result.
     */
    private void showSecureShortenUrlResult() {
        fastaSecureShortenUrlButton.setVisibility(View.GONE);
        fastasSecuerUrlLinearLayout.setVisibility(View.VISIBLE);
        fastaSecureCopyButton.setVisibility(View.VISIBLE);
        fastSecureUrlRelativeLayout.setVisibility(View.INVISIBLE);
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
    /*private void launchSendActivity() {
        Intent intent = new Intent(APIConfig.getInstance().getContext(), SendActivity.class);
        intent.putExtra(Constant.MESSAGE, (String) data.get(Constant.MESSAGE));
        APIConfig.getInstance().getContext().startActivity(intent);
    }*/
}

