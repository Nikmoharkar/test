package com.fastacash.opensdk.template;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fastacash.Constant;
import com.fastacash.opensdk.R;
import com.fastacash.ui.SendActivity;
import com.fastacash.opensdk.controller.APIConfig;

/**
 * Created by nikhil on 10/26/2015.
 */
public class FastaShareTemplate extends Template {

    private Button shareButton;

    public FastaShareTemplate() {
        init(R.layout.fasta_share);
    }

    @Override
    protected void init(int resId) {
        super.init(resId);
    }

    @Override
    protected void initViews() {
        super.initViews();
        shareButton = (Button) templateView.findViewById(R.id.shareButton);
    }

    @Override
    protected void setOnClickListeners() {
        super.setOnClickListeners();
        shareButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
        if (i == R.id.shareButton) {
            System.out.println("SHARE BUTTON CLICKED");
//            Toast.makeText(APIConfig.getInstance().getContext(), "SHARE BUTTON CLICKED", Toast.LENGTH_SHORT).show();
            launchSendActivity();
        }
    }

    private void launchSendActivity() {
        Intent intent = new Intent(APIConfig.getInstance().getContext(), SendActivity.class);
        APIConfig apiConfig = APIConfig.getInstance();
            Context context = apiConfig.getContext();
            if (context != null) {
                TextView textView = (TextView) ((Activity) context).findViewById(apiConfig.getResId());
                String shareText = textView.getText().toString();
                if (shareText != null) {
                    intent.putExtra(Constant.MESSAGE, shareText);

                }

        }
        APIConfig.getInstance().getContext().startActivity(intent);
    }

}
