package com.vyako.smartfactory.mo.dos;

import com.vyako.smartfactory.main.network.responsedos.Pagination;

import java.util.ArrayList;

/**
 * Created by nikhilm on 26-Jun-17.
 */

public class MOListDO {
    /**
     * Contains the response in the form of MO list dos
     */
    private ArrayList<MoDO> manufacturing_orders;

    private Pagination pagination;

    public Pagination getPagination() {
        return pagination;
    }

    public ArrayList<MoDO> getManufacturing_orders() {
        return manufacturing_orders;
    }
}
