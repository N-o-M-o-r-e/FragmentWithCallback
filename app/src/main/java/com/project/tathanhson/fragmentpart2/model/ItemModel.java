package com.project.tathanhson.fragmentpart2.model;

import android.graphics.Bitmap;

public class ItemModel {
    Bitmap resourceItem;
    String nameItem;
    boolean isSelect = false;

    public ItemModel(Bitmap resourceItem, String nameItem) {
        this.resourceItem = resourceItem;
        this.nameItem = nameItem;
    }

    public Bitmap getResourceItem() {
        return resourceItem;
    }

    public void setResourceItem(Bitmap resourceItem) {
        this.resourceItem = resourceItem;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public boolean getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }
}
