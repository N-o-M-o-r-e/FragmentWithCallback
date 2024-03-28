package com.project.tathanhson.fragmentpart2;

import com.project.tathanhson.fragmentpart2.model.ItemModel;

import java.util.ArrayList;

public class Storage {
    public ArrayList<ItemModel> listItemStorage;

    public ItemModel itemSelected;


    public Storage(ArrayList<ItemModel> listItemStorage, ItemModel itemSelected) {
        this.listItemStorage = listItemStorage;
        this.itemSelected = itemSelected;
    }
}
