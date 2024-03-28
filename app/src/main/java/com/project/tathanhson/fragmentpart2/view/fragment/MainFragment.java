package com.project.tathanhson.fragmentpart2.view.fragment;

import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.project.tathanhson.fragmentpart2.MyApplication;
import com.project.tathanhson.fragmentpart2.Storage;
import com.project.tathanhson.fragmentpart2.databinding.FragmentMainBinding;
import com.project.tathanhson.fragmentpart2.model.ItemModel;
import com.project.tathanhson.fragmentpart2.view.adapter.ListItemAdapter;
import com.project.tathanhson.fragmentpart2.view.inteface.OnMainCallBack;

import java.util.ArrayList;


public class MainFragment extends BaseFragment<FragmentMainBinding> implements OnMainCallBack {
    public static final String TAG = MainFragment.class.getName();
    private ListItemAdapter adapter;
    private Storage storage;
    private final ArrayList<ItemModel> listDefault = new ArrayList<>();

    @Override
    protected void initView() {
        getDataViewModel();

        ArrayList<ItemModel> listStorage = MyApplication.getINSTANCE().getStorage().listItemStorage;
        ItemModel itemSelected = MyApplication.getINSTANCE().getStorage().itemSelected;


        adapter = new ListItemAdapter(context, listDefault, viewModel, getViewLifecycleOwner());
        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcv.setAdapter(adapter);
        adapter.setCallBack(this);

        if (listStorage == null && itemSelected == null) {
            viewModel.getmListItem().observe(getViewLifecycleOwner(), itemListModels -> {
                if (itemListModels == null || itemListModels.isEmpty()) return;
                adapter.updateData(itemListModels);
                viewModel.getmItemModel().observe(getViewLifecycleOwner(), item -> {
                    if (item != null) {
                        storage = new Storage(itemListModels, item);
                        MyApplication.getINSTANCE().setStorage(storage);
                    }
                });
            });
        } else {
            adapter.updateData(listStorage);
            viewModel.setmItemModel(itemSelected);
            viewModel.getmItemModel().observe(getViewLifecycleOwner(), item -> {
                if (itemSelected != null) {
                    storage = new Storage(listStorage, item);
                    MyApplication.getINSTANCE().setStorage(storage);
                }
            });
        }

    }

    private void getDataViewModel() {
        viewModel.readStoryFile(context.getAssets());

    }

    @Override
    public void showFragment(String tag, Object data) {
        callBack.showFragment(DetailFragment.TAG, data);
    }

    @Nullable
    @Override
    protected FragmentMainBinding initViewBinding(@NonNull LayoutInflater inflater) {
        return FragmentMainBinding.inflate(inflater);
    }
}