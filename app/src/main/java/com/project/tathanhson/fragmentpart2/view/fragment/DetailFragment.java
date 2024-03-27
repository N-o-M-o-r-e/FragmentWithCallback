package com.project.tathanhson.fragmentpart2.view.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.project.tathanhson.fragmentpart2.databinding.FragmentDetailBinding;
import com.project.tathanhson.fragmentpart2.model.ItemModel;


public class DetailFragment extends BaseFragment<FragmentDetailBinding> {
    public static final String TAG = DetailFragment.class.getName();

    @Override
    protected void initView() {
        if (data == null) return;
        ItemModel itemSelect = (ItemModel) getData();
        GlideHelper.loadBitmap(context, itemSelect.getResourceItem(), binding.ivIcon);
        binding.tvIcon.setText(itemSelect.getNameItem());
        Log.e("AAAAAAAAAAA", "item : "+itemSelect.getNameItem() );
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