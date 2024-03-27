package com.project.tathanhson.fragmentpart2.model;

import android.graphics.Bitmap;

public class ItemModel {
    Bitmap resourceItem;
    String nameItem;

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
}
