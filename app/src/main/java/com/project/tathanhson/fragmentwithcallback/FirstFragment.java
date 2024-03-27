package com.project.tathanhson.fragmentwithcallback;

import android.view.LayoutInflater;

import com.project.tathanhson.fragmentwithcallback.databinding.FragmentFirstBinding;


public class FirstFragment extends BaseFragment<FragmentFirstBinding> {

    public static final String TAG = FirstFragment.class.getName();

    @Override
    protected void initView() {
        int resourceImage = R.drawable.ic_cat_00;
        binding.img1.setImageResource(resourceImage);
        binding.img1.setOnClickListener(view -> {
            callBack.showFragment(SecondFragment.TAG, resourceImage);
        });
    }



    @Override
    protected FragmentFirstBinding initViewBinding(LayoutInflater inflater) {
        return FragmentFirstBinding.inflate(inflater);
    }
}