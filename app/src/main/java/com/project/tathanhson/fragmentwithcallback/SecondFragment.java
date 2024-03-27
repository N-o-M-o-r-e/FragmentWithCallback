package com.project.tathanhson.fragmentwithcallback;

import android.view.LayoutInflater;

import com.project.tathanhson.fragmentwithcallback.databinding.FragmentSecondBinding;

public class SecondFragment extends BaseFragment<FragmentSecondBinding> {
    public static final String TAG = SecondFragment.class.getName();
    @Override
    protected void initView() {
        if (data == null) return;
        binding.img2.setImageResource((Integer) getData());
    }

    @Override
    protected FragmentSecondBinding initViewBinding(LayoutInflater inflater) {
        return FragmentSecondBinding.inflate(inflater);
    }
}