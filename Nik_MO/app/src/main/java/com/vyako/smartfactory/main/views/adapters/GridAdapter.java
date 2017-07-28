package com.vyako.smartfactory.main.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vyako.smartfactory.main.R;


/**
 * Created by Namrata on 5/26/2017.
 */

public class GridAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[] menus;

    public GridAdapter(Context context, String[] menus) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.menus = menus;
    }

    @Override
    public int getCount() {
        if (menus != null && menus.length > 0) {
            return menus.length;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.main_item_menu_grid_layout, null);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.menuName);
        nameTextView.setText(menus[position]);
        return convertView;
    }
}
