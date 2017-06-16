package com.fastacash.ui;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.fastacash.opensdk.R;
import com.fastacash.opensdk.utils.QLog;


public class BaseActivity extends AppCompatActivity {
    protected static int colorTitle;
    protected static int colorTitleText;
    protected ProgressDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        QLog.v("Class:" + ((Object) this).getClass().getSimpleName());
        super.onCreate(savedInstanceState);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(colorTitle));
        Toolbar actionBarToolbar = (Toolbar) findViewById(R.id.action_bar);
        actionBarToolbar.setTitleTextColor(colorTitleText);

        loadingDialog = new ProgressDialog(BaseActivity.this);
        loadingDialog.setIndeterminate(true);
        loadingDialog.setMessage("Loading");
        loadingDialog.setCancelable(false);
        loadingDialog.setCanceledOnTouchOutside(false);
    }


    @Override
    protected void onStart() {
        QLog.v("Class:" + ((Object) this).getClass().getSimpleName());
        super.onStart();
    }

    @Override
    protected void onRestart() {
        QLog.v("Class:" + ((Object) this).getClass().getSimpleName());
        super.onRestart();
    }

    @Override
    protected void onResume() {
        QLog.v("Class:" + ((Object) this).getClass().getSimpleName());
        super.onResume();
    }

    @Override
    protected void onPause() {
        QLog.v("Class:" + ((Object) this).getClass().getSimpleName());
        super.onPause();
    }

    @Override
    protected void onStop() {
        QLog.v("Class:" + ((Object) this).getClass().getSimpleName());
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        QLog.v("Class:" + ((Object) this).getClass().getSimpleName());
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        QLog.v("Class:" + ((Object) this).getClass().getSimpleName());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        QLog.v("Class:" + ((Object) this).getClass().getSimpleName());
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


}
