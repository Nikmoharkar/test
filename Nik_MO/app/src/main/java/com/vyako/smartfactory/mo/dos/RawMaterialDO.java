package com.vyako.smartfactory.mo.dos;

/**
 * Created by kaushik on 12-Jan-17.
 */

public class RawMaterialDO {
    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    private String specification;

    public String getSpecification() { return this.specification; }

    public void setSpecification(String specification) { this.specification = specification; }

    private String supplier;

    public String getSupplier() { return this.supplier; }

    public void setSupplier(String supplier) { this.supplier = supplier; }

    private String location;

    public String getLocation() { return this.location; }

    public void setLocation(String location) { this.location = location; }

    private String row_codes;

    public String getRowCodes() { return this.row_codes; }

    public void setRowCodes(String row_codes) { this.row_codes = row_codes; }

    private String coil_nos;

    public String getCoilNos() { return this.coil_nos; }

    public void setCoilNos(String coil_nos) { this.coil_nos = coil_nos; }

    private String coil_location;

    public String getCoilLocation() { return this.coil_location; }

    public void setCoilLocation(String coil_location) { this.coil_location = coil_location; }

    private String rcia_no;

    public String getRciaNo() { return this.rcia_no; }

    public void setRciaNo(String rcia_no) { this.rcia_no = rcia_no; }

    private String rcia_date;

    public String getRciaDate() { return this.rcia_date; }

    public void setRciaDate(String rcia_date) { this.rcia_date = rcia_date; }

    private String grade;

    public String getGrade() { return this.grade; }

    public void setGrade(String grade) { this.grade = grade; }

    private String batch_no;

    public String getBatchNo() { return this.batch_no; }

    public void setBatchNo(String batch_no) { this.batch_no = batch_no; }

    private float comp_wt_per_k_pcs;

    public float getCompWtPerKPcs() { return this.comp_wt_per_k_pcs; }

    public void setCompWtPerKPcs(float comp_wt_per_k_pcs) { this.comp_wt_per_k_pcs = comp_wt_per_k_pcs; }

    private float rm_wt_per_k_pcs;

    public float getRmWtPerKPcs() { return this.rm_wt_per_k_pcs; }

    public void setRmWtPerKPcs(float rm_wt_per_k_pcs) { this.rm_wt_per_k_pcs = rm_wt_per_k_pcs; }

    private int comp_qty_per_kg;

    public int getCompQtyPerKg() { return this.comp_qty_per_kg; }

    public void setCompQtyPerKg(int comp_qty_per_kg) { this.comp_qty_per_kg = comp_qty_per_kg; }

    private String created;

    public String getCreated() { return this.created; }

    public void setCreated(String created) { this.created = created; }

    private String updated;

    public String getUpdated() { return this.updated; }

    public void setUpdated(String updated) { this.updated = updated; }

    private int status;

    public int getStatus() { return this.status; }

    public void setStatus(int status) { this.status = status; }
}
