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
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.project.tathanhson.fragmentpart2.MyApplication;
import com.project.tathanhson.fragmentpart2.R;
import com.project.tathanhson.fragmentpart2.databinding.ItemDetailBinding;
import com.project.tathanhson.fragmentpart2.model.ItemModel;
import com.project.tathanhson.fragmentpart2.view.fragment.DetailFragment;
import com.project.tathanhson.fragmentpart2.view.fragment.MainFragment;
import com.project.tathanhson.fragmentpart2.view.inteface.OnMainCallBack;
import com.project.tathanhson.fragmentpart2.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ItemHolder> {
    private Context context;
    private ArrayList<ItemModel> modelList;
    private OnMainCallBack callback;
    private MainViewModel viewModel;
    private LifecycleOwner lifecycleOwner;

    private ItemModel itemSelect;

    public ListItemAdapter(Context context, ArrayList<ItemModel> modelList, MainViewModel viewModel, LifecycleOwner lifecycleOwner) {
        this.context = context;
        this.modelList = modelList;
        this.lifecycleOwner = lifecycleOwner;
        this.viewModel = viewModel;
    }
    public void setCallBack(OnMainCallBack callBack) {
        this.callback = callBack;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDetailBinding binding = ItemDetailBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        ItemModel itemModel = modelList.get(position);
        Bitmap bitmap = itemModel.getResourceItem();
        String nameItem = itemModel.getNameItem();

        GlideHelper.loadBitmap(context, bitmap, holder.binding.ivIcon);
        holder.binding.tvIcon.setText(nameItem);
        holder.itemView.setTag(itemModel);

        viewModel.getmItemModel().observe(lifecycleOwner, itemSelected -> {
            if (itemSelected == null) return;
            if (itemModel.equals(itemSelected)){
                holder.itemView.setBackgroundResource(R.drawable.view_selected);
            }else{
                holder.itemView.setBackgroundResource(R.drawable.view_unselect);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder  {
        private final ItemDetailBinding binding;

        public ItemHolder(@NonNull ItemDetailBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
            itemView.setOnClickListener(view -> {
                itemSelect = (ItemModel) view.getTag();
                viewModel.setmItemModel(itemSelect);
                callback.showFragment(DetailFragment.TAG, itemSelect);
                notifyItemRangeChanged(0, modelList.size());
            });

        }
    }

    public void updateData(ArrayList<ItemModel> newList) {
        ArrayList<ItemModel> clonedList = new ArrayList<>(newList);
        modelList.clear();
        modelList.addAll(clonedList);
        notifyDataSetChanged();
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

