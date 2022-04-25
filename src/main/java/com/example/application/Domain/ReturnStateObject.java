package com.example.application.Domain;

public class ReturnStateObject {
    Object attribute;
    String entity_id;
    String last_changed;
    String last_updated;
    String state;

    public ReturnStateObject() {
    }

    public ReturnStateObject(Object attribute, String entity_id, String last_changed, String last_updated, String state) {
        this.attribute = attribute;
        this.entity_id = entity_id;
        this.last_changed = last_changed;
        this.last_updated = last_updated;
        this.state = state;
    }

    public Object getAttribute() {
        return attribute;
    }

    public void setAttribute(Object attribute) {
        this.attribute = attribute;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getLast_changed() {
        return last_changed;
    }

    public void setLast_changed(String last_changed) {
        this.last_changed = last_changed;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
