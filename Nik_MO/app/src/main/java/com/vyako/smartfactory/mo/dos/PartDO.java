package com.vyako.smartfactory.mo.dos;

/**
 * Created by kaushik on 12-Jan-17.
 */

public class PartDO {
    private float component_area;

    private String title;

    private String updated;

    private String created;

    private int status;

    private int id;

    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getComponent_area() {
        return component_area;
    }

    public void setComponent_area(float component_area) {
        this.component_area = component_area;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", component_area = " + component_area + ", title = " + title + ", updated = " + updated + ", created = " + created + ", status = " + status + ", description = " + description + "]";
    }
}
