package com.project.tathanhson.fragmentpart2.viewmodel;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.tathanhson.fragmentpart2.MyApplication;
import com.project.tathanhson.fragmentpart2.Storage;
import com.project.tathanhson.fragmentpart2.model.ItemModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private MutableLiveData<ItemModel> mItemModel = new MutableLiveData<>(null);
    private MutableLiveData<ArrayList<ItemModel>> mListItem = new MutableLiveData<>(null);
    public MutableLiveData<ItemModel> getmItemModel() {
        return mItemModel;
    }
    public void setmItemModel(ItemModel mItemModel) {
        this.mItemModel.setValue(mItemModel);
    }
    public MutableLiveData<ArrayList<ItemModel>> getmListItem() {
        return mListItem;
    }
    public void setmListItem(ArrayList<ItemModel> mListItem) {
        this.mListItem.setValue(mListItem);
    }


    public void readStoryFile(@Nullable AssetManager assetManager) {

        ArrayList<ItemModel> listItem = new ArrayList<>();
        try {
            String[] listFileName = assetManager.list("photo/");
            for (String fileName : listFileName) {
                String imagePath = "photo" + "/" + fileName;
                Bitmap bitmap = getBitmapFromAsset(assetManager, imagePath);
                String name = fileName.replace(".png", "");
                ItemModel itemModel = new ItemModel(bitmap, name);
                listItem.add(itemModel);
            }
//            mListItem.setValue(listItem);
            setmListItem(listItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap getBitmapFromAsset(AssetManager assetManager, String filePath) {
        InputStream inputStream = null;
        Bitmap bitmap = null;
        try {
            inputStream = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }


}
