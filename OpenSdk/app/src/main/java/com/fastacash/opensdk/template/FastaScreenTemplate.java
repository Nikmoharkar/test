package com.fastacash.opensdk.template;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.fastacash.Constant;
import com.fastacash.opensdk.R;
import com.fastacash.opensdk.controller.APIConfig;
import com.fastacash.ui.SendActivity;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nikhil on 10/26/2015.
 */
public class FastaScreenTemplate extends Template {
    private Button screenShotButton;

    public FastaScreenTemplate() {
        init(R.layout.fasta_screen_shot);
    }

    @Override
    protected void init(int resId) {
        super.init(resId);
    }

    @Override
    protected void initViews() {
        super.initViews();
        screenShotButton = (Button) templateView.findViewById(R.id.screenShotButton);
    }

    @Override
    protected void setOnClickListeners() {
        super.setOnClickListeners();
        screenShotButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
        if (i == R.id.screenShotButton) {
//            System.out.println("SHARE BUTTON CLICKED");
//                Toast.makeText(APIConfig.getInstance().getContext(), "SHARE BUTTON CLICKED", Toast.LENGTH_SHORT).show();
            launchSendActivity();
        }
    }

    private void launchSendActivity() {
        try {
            Activity activity = (Activity) APIConfig.getInstance().getContext();
            View v = activity.getWindow().getDecorView().getRootView();

            Intent intent = new Intent(APIConfig.getInstance().getContext(), SendActivity.class);
            intent.putExtra(Constant.PATH, takeScreenshot(APIConfig.getInstance().getContext(), v));
            APIConfig.getInstance().getContext().startActivity(intent);
        } catch (Exception e) {

        }
    }

    private static String takeScreenshot(Context context, View v) {
        // create bitmap screen capture
        v.setDrawingCacheEnabled(true);
        Bitmap qrBitmap = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);

        return getPath(context, qrBitmap);
    }

    private static String getPath(Context context, Bitmap qrBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        qrBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), qrBitmap, new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()), null);
        return path;
    }
}
