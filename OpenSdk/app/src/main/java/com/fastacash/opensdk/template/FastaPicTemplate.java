package com.fastacash.opensdk.template;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.fastacash.Constant;
import com.fastacash.opensdk.R;
import com.fastacash.ui.SendActivity;
import com.fastacash.opensdk.controller.APIConfig;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nikhil on 10/26/2015.
 */
public class FastaPicTemplate extends Template {
    private Button shotButton;
    private View view;
    private ImageView fastaShotImageView;

    public FastaPicTemplate() {
        init(R.layout.fasta_shot);
    }

    @Override
    protected void init(int resId) {
        super.init(resId);
    }

    @Override
    protected void initViews() {
        super.initViews();
        APIConfig apiConfig = APIConfig.getInstance();

        Context context = apiConfig.getContext();
        if (context != null) {
            view = ((Activity) context).findViewById(apiConfig.getResId());
            fastaShotImageView = (ImageView) templateView.findViewById(R.id.fastaShotImageView);
        }
    }

    @Override
    protected void setOnClickListeners() {
        super.setOnClickListeners();
        fastaShotImageView.setOnClickListener(this);
    }

    @Override
    protected void setOnLongClickListeners() {
        super.setOnLongClickListeners();
        if (view != null) {
            view.setOnLongClickListener(this);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        super.onLongClick(view);
        int resId = APIConfig.getInstance().getResId();
        if (resId == view.getId()) {
            System.out.println("LONG CLICKED");
            launchSendActivity();
        }

        return true;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
        if (i == R.id.fastaShotImageView) {
            launchSendActivity();
//                System.out.println("SHARE BUTTON CLICKED");
//                Toast.makeText(APIConfig.getInstance().getContext(), "SHARE BUTTON CLICKED", Toast.LENGTH_SHORT).show();
//                launchSendActivity();
        }

    }

    private void launchSendActivity() {
        Intent intent = new Intent(APIConfig.getInstance().getContext(), SendActivity.class);
        APIConfig apiConfig = APIConfig.getInstance();
        Context context = apiConfig.getContext();
        if (context != null) {
            View view = ((Activity) context).findViewById(apiConfig.getResId());
            if (view != null) {
                String path = takeScreenshot(context, view);
                if (path != null) {
                    intent.putExtra(Constant.PATH, path);
                    APIConfig.getInstance().getContext().startActivity(intent);
                } else {
                    Toast.makeText(APIConfig.getInstance().getContext(), "Unable to share!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public static String takeScreenshot(Context context, View v) {
        // create bitmap screen capture
        try {
            v.setDrawingCacheEnabled(true);
            Bitmap qrBitmap = Bitmap.createBitmap(v.getDrawingCache());
            v.setDrawingCacheEnabled(false);
            return getPath(context, qrBitmap);
        } catch (Exception e) {

        }
        return null;
    }

    private static String getPath(Context context, Bitmap qrBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        qrBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), qrBitmap, new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()), null);
        return path;
    }
}
