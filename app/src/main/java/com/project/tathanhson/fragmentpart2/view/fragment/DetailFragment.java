package com.project.tathanhson.fragmentpart2.view.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.project.tathanhson.fragmentpart2.MyApplication;
import com.project.tathanhson.fragmentpart2.databinding.FragmentDetailBinding;
import com.project.tathanhson.fragmentpart2.model.ItemModel;

import java.util.ArrayList;


public class DetailFragment extends BaseFragment<FragmentDetailBinding> {
    public static final String TAG = DetailFragment.class.getName();

    @Override
    protected void initView() {
        if (data == null) return;
        ItemModel itemSelect = (ItemModel) getData();
        GlideHelper.loadBitmap(context, itemSelect.getResourceItem(), binding.ivIcon);
        binding.tvIcon.setHint(itemSelect.getNameItem());

        binding.tvIcon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                itemSelect.setNameItem(editable.toString());
            }
        });

        binding.btnDelete.setOnClickListener(view -> {
            deleteItem((ItemModel)data);
        });
    }

    private void deleteItem(ItemModel data) {

        boolean isDeleted = MyApplication.getINSTANCE().getStorage().listItemStorage.remove(data);

        ArrayList<ItemModel> listNewItem = MyApplication.getINSTANCE().getStorage().listItemStorage;

        if (isDeleted){
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
            callBack.showFragment(MainFragment.TAG,listNewItem);
        }else {
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    protected FragmentDetailBinding initViewBinding(LayoutInflater inflater) {
        return FragmentDetailBinding.inflate(inflater);
    }


    public static class GlideHelper {
        public static void loadBitmap(Context context, Bitmap bitmap, ImageView imageView) {
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true);

            Glide.with(context).asBitmap().load(bitmap).apply(requestOptions).into(imageView);
        }
    }
}