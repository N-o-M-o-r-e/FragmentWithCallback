package com.project.tathanhson.fragmentpart2.view.fragment;

import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.project.tathanhson.fragmentpart2.MyApplication;
import com.project.tathanhson.fragmentpart2.databinding.FragmentMainBinding;
import com.project.tathanhson.fragmentpart2.view.adapter.ListItemAdapter;


public class MainFragment extends BaseFragment<FragmentMainBinding> {
    public static final String TAG = MainFragment.class.getName();

    @Override
    protected void initView() {
        ListItemAdapter adapter = new ListItemAdapter(MyApplication.getINSTANCE(), MyApplication.getINSTANCE().getStorage().listItemStorage);
//        adapter.setCallBack(this);
        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcv.setAdapter(adapter);

        adapter.getItemSelectLiveData().observe(this, itemModel -> {
            if (itemModel == null) return;
            callBack.showFragment(DetailFragment.TAG, itemModel);
        });

    }

    //    @Override
//    public void showFragment(String tag, Object data) {
//        callBack.showFragment(DetailFragment.TAG, data);
//    }
//
    @Nullable
    @Override
    protected FragmentMainBinding initViewBinding(@NonNull LayoutInflater inflater) {
        return FragmentMainBinding.inflate(inflater);
    }
}