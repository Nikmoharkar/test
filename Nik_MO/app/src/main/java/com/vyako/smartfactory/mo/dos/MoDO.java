package com.vyako.smartfactory.mo.dos;


import com.vyako.smartfactory.main.network.responsedos.CustomerDO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kaushik on 12-Jan-17.
 */

public class MoDO {
    private int id;
    private String number;
    private String opening_date;
    private int validity;
    private String finish_date;
    private String prepared_by;
    private int quantity;
    private float rm_req_qty;
    private float rm_for_mo_qty;
    private int stammped_qty;
    private int customer_id;
    private float total_component_weight;
    private int part_id;
    private int rm_id;
    private int zone_id;
    private int type;
    private String created;
    private String updated;
    private int status;
    private int estimated_production;
    private PartDO part;
    private CustomerDO customer;
    private RawMaterialDO raw_material;
    private boolean allow_zone;
    private ArrayList<ToolsDO> tools;
    public float rm_used_in_kg;
    public int production_machine_id;
    public int no_of_strokes;
    public int production_count;
    public int wastage;
    public int good_products;
    private String logCat = "Logs MoDO---";


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getOpeningDate() {
        return this.opening_date;
    }

    public void setOpeningDate(String opening_date) {
        this.opening_date = opening_date;
    }


    public int getValidity() {
        return this.validity;
    }

    public void setValidity(int validity) {
        this.validity = validity;
    }


    public String getFinishDate() {
        return this.finish_date;
    }

    public void setFinishDate(String finish_date) {
        this.finish_date = finish_date;
    }


    public String getPreparedBy() {
        return this.prepared_by;
    }

    public void setPreparedBy(String prepared_by) {
        this.prepared_by = prepared_by;
    }


    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public int getEstimatedProduction() {
        return this.estimated_production;
    }

    public void setEstimatedProduction(int estimated_production) {
        this.estimated_production = estimated_production;
    }


    public float getRmReqQty() {
        return this.rm_req_qty;
    }

    public void setRmReqQty(float rm_req_qty) {
        this.rm_req_qty = rm_req_qty;
    }


    public float getRmForMoQty() {
        return this.rm_for_mo_qty;
    }

    public void setRmForMoQty(float rm_for_mo_qty) {
        this.rm_for_mo_qty = rm_for_mo_qty;
    }


    public int getStammpedQty() {
        return this.stammped_qty;
    }

    public void setStammpedQty(int stammped_qty) {
        this.stammped_qty = stammped_qty;
    }


    public float getTotalComponentWeight() {
        return this.total_component_weight;
    }

    public void setTotalComponentWeight(float total_component_weight) {
        this.total_component_weight = total_component_weight;
    }


    public int getCustomerId() {
        return this.customer_id;
    }

    public void setCustomerId(int customer_id) {
        this.customer_id = customer_id;
    }


    public int getPartId() {
        return this.part_id;
    }

    public void setPartId(int part_id) {
        this.part_id = part_id;
    }


    public int getRmId() {
        return this.rm_id;
    }

    public void setRmId(int rm_id) {
        this.rm_id = rm_id;
    }


    public int getZoneId() {
        return this.zone_id;
    }

    public void setZoneId(int zone_id) {
        this.zone_id = zone_id;
    }


    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getCreated() {
        return this.created;
    }

    public void setCreated(String created) {
        this.created = created;
    }


    public String getUpdated() {
        return this.updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }


    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public RawMaterialDO getRawMaterial() {
        return this.raw_material;
    }

    public void setRawMaterial(RawMaterialDO raw_material) {
        this.raw_material = raw_material;
    }


    public PartDO getPart() {
        return this.part;
    }

    public void setPart(PartDO part) {
        this.part = part;
    }


    public ArrayList<ToolsDO> getTools() {
        return this.tools;
    }

    public void setTools(ArrayList<ToolsDO> tools) {
        this.tools = tools;
    }


    public CustomerDO getCustomer() {
        return this.customer;
    }

    public void setCustomer(CustomerDO customer) {
        this.customer = customer;
    }


    public boolean getAllowZone() {
        return this.allow_zone;
    }

    public void setAllowZone(boolean allow_zone) {
        this.allow_zone = allow_zone;
    }


    public int getProduction_machine_id() {
        return production_machine_id;
    }

    public void setProduction_machine_id(int production_machine_id) {
        this.production_machine_id = production_machine_id;
    }


    public float getRmUsedInKg() {
        return rm_used_in_kg;
    }

    public void setRmUsedInKg(float rm_used_in_kg) {
        this.rm_used_in_kg = rm_used_in_kg;
    }


    public int getNoOfStrokes() {
        return no_of_strokes;
    }

    public void setNoOfStrokes(int no_of_strokes) {
        this.no_of_strokes = no_of_strokes;
    }


    public int getProductionCount() {
        return production_count;
    }

    public void setProductionCount(int production_count) {
        this.production_count = production_count;
    }


    public int getGoodProducts() {
        return good_products;
    }

    public void setGoodProducts(int good_products) {
        this.good_products = good_products;
    }


    public int getWastage() {
        return wastage;
    }

    public void setWastage(int wastage) {
        this.wastage = wastage;
    }


    /**
     * Format the MediaDO JSON format
     *
     * @return
     */
    public JSONObject getJSONFormatForStatusUpdateWithMachineId() {
        JSONObject mediaJSON = new JSONObject();
        try {
            mediaJSON.put("id", getId());
            mediaJSON.put("status", getStatus());
            mediaJSON.put("production_machine_id", getProduction_machine_id());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mediaJSON;
    }


    /**
     * Format the MediaDO JSON format
     *
     * @return
     */
    public JSONObject getJSONFormatForStatusUpdate() {
        JSONObject mediaJSON = new JSONObject();
        try {
            mediaJSON.put("id", getId());
            mediaJSON.put("status", getStatus());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mediaJSON;
    }


    /***
     * Method that return the json object which has Mo status list.
     *
     * @return
     */
//    public JSONObject getJsonFormatMoStatusRequestBody() {
//
//        String updatedMoListDateTime = MoManager.getInstance().getUpdatedMoListDateTime();
//        JSONObject moStatusRequestBody = new JSONObject();
//        try {
//            if (updatedMoListDateTime == null) {
//                Log.d(logCat, "updatedMoListDateTime-- " + updatedMoListDateTime + " ");
//                JSONArray array = new JSONArray();
//                array.put(com.vyako.smartfactory.ui.constants.IConstants.MO_STATUS.IN_PENDING);
//                array.put(com.vyako.smartfactory.ui.constants.IConstants.MO_STATUS.IN_PROGRESS);
//                array.put(com.vyako.smartfactory.ui.constants.IConstants.MO_STATUS.IN_HOLD);
//
//                moStatusRequestBody.put(com.vyako.smartfactory.ui.constants.IConstants.MO_STATUS.LIST, array);
//                moStatusRequestBody.put(com.vyako.smartfactory.ui.constants.IConstants.MO_STATUS.UPDATE_DATE_TIME, updatedMoListDateTime);
//            } else {
//                Log.d(logCat, "updatedMoListDateTime-- " + updatedMoListDateTime);
//                moStatusRequestBody.put(com.vyako.smartfactory.ui.constants.IConstants.MO_STATUS.LIST, null);
//                moStatusRequestBody.put(com.vyako.smartfactory.ui.constants.IConstants.MO_STATUS.UPDATE_DATE_TIME, updatedMoListDateTime);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        Log.d(logCat, "moStatusRequestBody-- " + moStatusRequestBody);
//
//        return moStatusRequestBody;
//    }


}
