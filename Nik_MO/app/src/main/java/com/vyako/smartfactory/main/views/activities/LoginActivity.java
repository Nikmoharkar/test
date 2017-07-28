package com.vyako.smartfactory.main.views.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vyako.smartfactory.main.R;
import com.vyako.smartfactory.main.app.constants.AppConstants;
import com.vyako.smartfactory.main.contracts.LoginScreenContract;
import com.vyako.smartfactory.main.presenters.LoginActivityPresenterImpl;
import com.vyako.smartfactory.main.views.fragments.BaseAbstractFragment;


/**
 * Created by hp on 31-05-2017.
 */

public class LoginActivity extends BaseAbstractActivity<LoginActivityPresenterImpl> implements LoginScreenContract
        .IView, View.OnClickListener {

    private EditText userNameEditText;
    private EditText passwordEditText;
    private Button signUpButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login_screen);
        initializeInstances();
        presenter.create();
        setListenerToViews();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        initViews();
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.main_activity_login_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {
        presenter = new LoginActivityPresenterImpl(this, this);
    }

    private void initViews() {
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        signUpButton = (Button) findViewById(R.id.signUpButton);
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        signUpButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signUpButton:
                onSignInButtonClick();
                break;
        }
    }

    private void onSignInButtonClick() {
        if (areValidFields()) {
            presenter.dispatch(LoginScreenContract.IPermissionIds.LOGIN, false);
        }
    }

    /**
     * Method which return the login credentials
     */
    @Override
    public String getLoginCredentials() {
        String username = userNameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String credentials = username + ":" + password;
        Log.d(AppConstants.LOG_CAT, "credentials: " + credentials);
        return credentials;
    }

    @Override
    public void showLoading() {
//        com.vyako.smartfactory.main.network.utils.Utils.showErrorDialog();
    }

    @Override
    public void stopLoading() {
        //todo
    }

    @Override
    public void setData() {

    }

    @Override
    public boolean areValidFields() {

        userNameEditText.requestFocus();
        if (userNameEditText.getText() == null || userNameEditText.getText().length() == 0) {
            userNameEditText.setError(getResources().getString(R.string.username_empty));
            return false;
        } else if (userNameEditText.getText().toString().trim().isEmpty() || userNameEditText.getText().toString().trim().length() == 0) {
            userNameEditText.setError(getResources().getString(R.string.username_invalid));
            return false;
        } else if (userNameEditText.getText().toString().trim().contains(" ")) {
            userNameEditText.setError(getResources().getString(R.string.username_contain_space));
            return false;
        } else {
            userNameEditText.setError(null);
        }

        passwordEditText.requestFocus();
        if (passwordEditText.getText().length() < 6) {
            passwordEditText.setError(getResources().getString(R.string.password_empty));
            return false;
        } else if (passwordEditText.getText().toString().trim().isEmpty() || passwordEditText.getText().toString().trim().length() == 0) {
            passwordEditText.setError(getResources().getString(R.string.password_invalid));
            return false;
        } else if (passwordEditText.getText().toString().trim().contains(" ")) {
            passwordEditText.setError(getResources().getString(R.string.password_contain_space));
            return false;
        } else {
            passwordEditText.setError(null);
        }
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        hideToolbar();
        lockDrawer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        showToolbar();
        releaseDrawerLock();
    }

    @Override
    public void setRootFragment(BaseAbstractFragment rootFragment) {

    }

    @Override
    public void addFragment(@NonNull BaseAbstractFragment fragment, @Nullable String tag) {

    }

    @Override
    public void removeTopFragment(@Nullable String tag) {

    }
}
