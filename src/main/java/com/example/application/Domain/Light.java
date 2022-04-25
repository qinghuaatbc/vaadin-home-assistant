package com.example.application.Domain;

public class Light {

    String name;
    String entity;
    int left;
    int top;
   boolean sw;

    public Light() {
    }

    public Light(String name, String entity, int left, int top, boolean sw) {
        this.name = name;
        this.entity = entity;
        this.left = left;
        this.top = top;
        this.sw = sw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public boolean isSw() {
        return sw;
    }

    public void setSw(boolean sw) {
        this.sw = sw;
    }
}
