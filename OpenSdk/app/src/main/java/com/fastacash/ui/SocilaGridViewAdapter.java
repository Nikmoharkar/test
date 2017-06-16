package com.fastacash.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.fastacash.opensdk.R;
import com.fastacash.opensdk.controller.APIConfig;
import com.fastacash.opensdk.plugin.Plugin;
import com.fastacash.opensdk.plugin.PluginManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vickram on 11/2/2015.
 */
public class SocilaGridViewAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    /**
     * Holds the refrence of the current context
     */
    private Context context;
    /**
     * Contains the social sharing app data
     */
    private HashMap<Integer, HashMap<String, Object>> socialChannels;
    private LayoutInflater inflater;

    public SocilaGridViewAdapter(Context context) {

        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        socialChannels = APIConfig.getInstance().getSocialChannels();
    }

    @Override
    public int getCount() {
        if (socialChannels != null) {
            return socialChannels.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (socialChannels != null) {
            return socialChannels.get(position + 1);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.send_grid_view_item_layout, parent, false);
            holder = new Holder();
            holder.socilaChannelsImageView = (ImageView) convertView.findViewById(R.id.socilaChannelsImageView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        setSocialAppIconImageResources(holder, position);
        return convertView;
    }

    /**
     * Sets the social app icon
     *
     * @param holder
     * @param position
     */
    private void setSocialAppIconImageResources(Holder holder, int position) {

        if (socialChannels != null && socialChannels.containsKey(position + 1)) {
            HashMap<String, Object> socialAppData = socialChannels.get(position + 1);
            if (socialAppData != null && socialAppData.containsKey("img")) {
                Object object = socialAppData.get("img");
                if (object instanceof Integer) {
                    Integer resId = (Integer) object;
                    if (resId != -1) {
                        holder.socilaChannelsImageView.setImageResource(resId);
                    } else {
                        holder.socilaChannelsImageView.setImageResource(R.mipmap.ic_launcher);
                    }
                }
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (socialChannels != null && socialChannels.containsKey(position + 1)) {
            HashMap<String, Object> socialAppData = socialChannels.get(position + 1);
            if (socialAppData != null && socialAppData.containsKey("appname")) {
                Object object = socialAppData.get("appname");
                if (object instanceof String) {
                    String socialAppName = (String) object;
                    if (socialAppName != null && context != null) {
                        if (context instanceof SendActivity) {
                            ((SendActivity) context).onSocialAppIconClick(socialAppName, (String) socialAppData.get("name"));
                        }
                    }
                }
            }
        }
    }

    /**
     * Holds the refrence of created views
     */
    static class Holder {
        ImageView socilaChannelsImageView;
    }
}
