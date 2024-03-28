package com.project.tathanhson.fragmentpart2.model;

import android.graphics.Bitmap;

import androidx.annotation.Nullable;

public class ItemModel {
    Bitmap resourceItem;
    String nameItem;

    public ItemModel(@Nullable Bitmap resourceItem, @Nullable String nameItem) {
        this.resourceItem = resourceItem;
        this.nameItem = nameItem;
    }

    @Nullable
    public Bitmap getResourceItem() {
        return resourceItem;
    }

    public void setResourceItem(@Nullable Bitmap resourceItem) {
        this.resourceItem = resourceItem;
    }

    @Nullable
    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(@Nullable String nameItem) {
        this.nameItem = nameItem;
    }


}
