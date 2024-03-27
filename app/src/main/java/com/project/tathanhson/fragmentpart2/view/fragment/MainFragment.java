package com.project.tathanhson.fragmentpart2.view.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.project.tathanhson.fragmentpart2.MyApplication;
import com.project.tathanhson.fragmentpart2.databinding.FragmentMainBinding;
import com.project.tathanhson.fragmentpart2.view.adapter.ListItemAdapter;
import com.project.tathanhson.fragmentpart2.view.inteface.OnMainCallBack;


public class MainFragment extends BaseFragment<FragmentMainBinding> implements OnMainCallBack {
    public static final String TAG = MainFragment.class.getName();

    @Override
    protected void initView() {
        ListItemAdapter adapter = new ListItemAdapter(MyApplication.getINSTANCE(), MyApplication.getINSTANCE().getStorage().listItemStorage);
        adapter.setCallBack(this);
        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcv.setAdapter(adapter);

    }

    @Override
    public void showFragment(String tag, Object data) {
        callBack.showFragment(DetailFragment.TAG, data);
    }

    @Override
    protected FragmentMainBinding initViewBinding(LayoutInflater inflater) {
        return FragmentMainBinding.inflate(inflater);
    }
}