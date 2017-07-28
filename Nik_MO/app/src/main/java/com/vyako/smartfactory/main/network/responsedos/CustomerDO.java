package com.vyako.smartfactory.main.network.responsedos;

/**
 * Created by kaushik on 12-Jan-17.
 */

public class CustomerDO {

    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    private int status;

    public int getStatus() { return this.status; }

    public void setStatus(int status) { this.status = status; }

    private String created;

    public String getCreated() { return this.created; }

    public void setCreated(String created) { this.created = created; }

    private String updated;

    public String getUpdated() { return this.updated; }

    public void setUpdated(String updated) { this.updated = updated; }
}
