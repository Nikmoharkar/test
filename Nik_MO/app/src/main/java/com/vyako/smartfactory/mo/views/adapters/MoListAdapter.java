package com.vyako.smartfactory.mo.views.adapters;

;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vyako.smartfactory.main.R;
import com.vyako.smartfactory.mo.dos.MoDO;
import com.vyako.smartfactory.mo.presenters.MoListFragmentPresenterImpl;
import com.vyako.smartfactory.mo.views.fragments.MoListFragment;

import java.util.ArrayList;

/**
 * Created by ranjeetd on 6/8/2017.
 */

public class MoListAdapter extends RecyclerView.Adapter<MoListAdapter.DataViewHolder> implements View.OnClickListener {
    private final MoListFragmentPresenterImpl presenter;
    private Button moEditButton;
    private Button moDeleteButton;
    private Button moZoneChangeButton;
    private FragmentTransaction fragmentTransaction;
    private ArrayList<MoDO> moDOs;
    private MoListFragment context;


    public MoListAdapter(MoListFragment context, ArrayList<MoDO> moDOs, MoListFragmentPresenterImpl presenter) {
        this.context = context;
        this.moDOs = moDOs;
        this.presenter = presenter;
    }

    @Override
    public void onClick(View v) {
        presenter.launchMODetails(null);
    }

    /**
     * class that holds the view for smooth scrolling.
     */
    public static class DataViewHolder extends RecyclerView.ViewHolder {
        private TextView finishedDateValue;
        private TextView stamppedQuantityValue;
        private TextView zone;
        private TextView quantityValue;
        private TextView partTitle;
        private TextView moNumber;
        private TextView status;
        private RelativeLayout linearLayout;

        public DataViewHolder(View itemView) {
            super(itemView);
            moNumber = (TextView) itemView.findViewById(R.id.moNumber);
            partTitle = (TextView) itemView.findViewById(R.id.partTitle);
            quantityValue = (TextView) itemView.findViewById(R.id.quantityValue);
            zone = (TextView) itemView.findViewById(R.id.zone);
            stamppedQuantityValue = (TextView) itemView.findViewById(R.id.stamppedQuantityValue);
            finishedDateValue = (TextView) itemView.findViewById(R.id.finishedDateValue);
            status = (TextView) itemView.findViewById(R.id.status);
            linearLayout = (RelativeLayout) itemView.findViewById(R.id.parentLayout);
        }
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mo_list_item_card_layout, parent, false);
        view.setOnClickListener(this);
        DataViewHolder dataViewHolder = new DataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        if (moDOs != null) {
            MoDO moDO = moDOs.get(position);
            if (moDO != null) {

                //sets part title
                String partTitle = "NA";
                if (moDO.getPart() != null) {
                    partTitle = moDO.getPart().getTitle();
                }

                holder.partTitle.setText(String.valueOf(partTitle));
                holder.moNumber.setText(String.valueOf(moDO.getNumber()));
                holder.quantityValue.setText(String.valueOf(moDO.getQuantity()));
                holder.zone.setText("Zone " + String.valueOf(moDO.getZoneId()));
                holder.finishedDateValue.setText(String.valueOf(moDO.getFinishDate()));
                holder.stamppedQuantityValue.setText(String.valueOf(moDO.getStammpedQty()));
//                holder.status.setText(String.valueOf(moDO.getStatus()));

            }
        }
        //  holder.linearLayout
    }

    @Override
    public int getItemCount() {
        if (moDOs != null) {
            return moDOs.size();
        }
        return 0;
    }

    /**
     * Refreshes the mo list.
     *
     * @param moDOs New Mo doas
     */
    public void refreshList(ArrayList<MoDO> moDOs) {
        this.moDOs = moDOs;
        this.notifyDataSetChanged();
    }


}
