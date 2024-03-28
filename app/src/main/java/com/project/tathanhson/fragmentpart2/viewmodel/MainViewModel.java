package com.project.tathanhson.fragmentpart2.viewmodel;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.project.tathanhson.fragmentpart2.MyApplication;
import com.project.tathanhson.fragmentpart2.model.ItemModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private ArrayList<ItemModel> itemList;

    public void readStoryFile(AssetManager assetManager) {
        itemList = new ArrayList<>();
        try {
            String[] listFileName = assetManager.list("photo/");
            for (String fileName : listFileName) {
                String imagePath = "photo" + "/" + fileName;
                Bitmap bitmap = getBitmapFromAsset(assetManager, imagePath);
                String name = fileName.replace(".png", "");
                ItemModel item = new ItemModel(bitmap, name);
                itemList.add(item);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("AAAAAAAAA", "readStoryFile: "+ itemList );

        MyApplication.getINSTANCE().getStorage().listItemStorage = itemList;
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
