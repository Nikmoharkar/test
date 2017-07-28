package com.vyako.smartfactory.main.network.responsedos;

import com.vyako.smartfactory.mo.dos.MoDO;

import java.util.ArrayList;

/**
 * Created by kaushik on 12-Jan-17.
 */

public class Response {

    private int status_code;
    private String message;
    private String date_time;

    //    private ArrayList<MoDO> manufacturing_orders = new ArrayList<>();
    private ArrayList<MoDO> response = new ArrayList<>();
   // private ArrayList<MachineDo> machines = new ArrayList<MachineDo>();

//    public ArrayList<MoDO> getManufacturing_orders() {
//        return manufacturing_orders;
//    }
//
//    public void setManufacturing_orders(ArrayList<MoDO> manufacturing_orders) {
//        this.manufacturing_orders = manufacturing_orders;
//    }


    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

//    public ArrayList<MachineDo> getMachines() {
//        return machines;
//    }
//
//    public void setMachines(ArrayList<MachineDo> machines) {
//        this.machines = machines;
//    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<MoDO> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<MoDO> response) {
        this.response = response;
    }



}
