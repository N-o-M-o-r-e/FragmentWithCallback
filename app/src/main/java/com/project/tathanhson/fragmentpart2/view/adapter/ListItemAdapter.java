package com.project.tathanhson.fragmentpart2.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.project.tathanhson.fragmentpart2.MyApplication;
import com.project.tathanhson.fragmentpart2.R;
import com.project.tathanhson.fragmentpart2.databinding.ItemDetailBinding;
import com.project.tathanhson.fragmentpart2.model.ItemModel;
import com.project.tathanhson.fragmentpart2.view.fragment.DetailFragment;
import com.project.tathanhson.fragmentpart2.view.inteface.OnMainCallBack;

import java.util.List;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ItemHolder> {
    private Context context;
    List<ItemModel> modelList;

    private int selectedPosition = -1;
    private ItemDetailBinding binding;

    ItemModel itemSelected;
    private OnMainCallBack callback;
    public void setCallBack(OnMainCallBack callBack) {
        this.callback = callBack;
    }

    MutableLiveData<ItemModel> itemSelectLiveData = new MutableLiveData<>();

    public MutableLiveData<ItemModel> getItemSelectLiveData() {
        return itemSelectLiveData;
    }

    public ListItemAdapter(Context context, List<ItemModel> modelList) {
        this.context = context;
        this.modelList = modelList;

    }


    /**
     * Gán view item detail (ánh xạ view)
     */
    @NonNull
    @Override
    public ListItemAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Sử dụng ViewBinding để ánh xạ layout
        binding = ItemDetailBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemHolder(binding.getRoot());
    }

    /**
     * gán dữ liệu cho từng item
     */
    @Override
    public void onBindViewHolder(@NonNull ListItemAdapter.ItemHolder holder, int position) {
        ItemModel itemModel = modelList.get(position);
        Bitmap bitmap = itemModel.getResourceItem();
        String nameItem = itemModel.getNameItem();

        GlideHelper.loadBitmap(context, bitmap, binding.ivIcon);
        binding.tvIcon.setText(nameItem);
//        holder.itemView.setTag(itemModel);
        holder.itemView.setTag(position);

        if (itemModel==MyApplication.getINSTANCE().getStorage().itemModel){
            holder.itemView.setBackgroundResource(R.drawable.view_selected);
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.view_unselect);
        }




    }

    /*
     * Số lượng phần tử có trong recyclerView
     */
    @Override
    public int getItemCount() {
        return modelList.size();
    }

    /*
     * ItemHolder sẽ kế thừa RecyclerView.ViewHolder
     */
    public class ItemHolder extends RecyclerView.ViewHolder {

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            /*
                sử dụng callback và set sự thay đổi thông qua một đối tượng bộ nhớ trung gian lưu trữ
             */

            /*
            itemView.setOnClickListener(view -> {
                ItemModel itemSelect = (ItemModel) view.getTag();
                callback.showFragment(DetailFragment.TAG, itemSelect);
                Log.e("AAAAAAAAAAAAA", "ItemHolder: "+itemSelect);

                MyApplication.getINSTANCE().getStorage().itemModel = itemSelect;
                MyApplication.getINSTANCE().getStorage().itemModel.setIsSelect(true);
                notifyItemRangeChanged(0, modelList.size());
            });
             */

            /*
              sủ dụng livedata để gửi đối tượng được chọn qua FragamentMain xử lý callback
             */
            itemView.setOnClickListener(view -> {
                int position = (int) view.getTag();
                modelList.get(position).setIsSelect(true);
                itemSelected = modelList.get(position);
                itemSelectLiveData.setValue(itemSelected);
                MyApplication.getINSTANCE().getStorage().itemModel = itemSelected;

                Log.e("AAAAAAAAAAAAA", "ItemHolder: "+ itemSelected.getNameItem());
                notifyItemRangeChanged(0, modelList.size());
            });
        }
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

