package com.project.tathanhson.fragmentpart2.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.project.tathanhson.fragmentpart2.view.inteface.OnMainCallBack;
import com.project.tathanhson.fragmentpart2.viewmodel.MainViewModel;

public abstract class BaseFragment<V extends ViewBinding> extends Fragment implements View.OnClickListener {
    protected Context context;
    protected Object data;
    protected V binding;

    protected MainViewModel viewModel;
    protected OnMainCallBack callBack;

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setCallBack(OnMainCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public final View onCreateView(@Nullable LayoutInflater inflater, @Nullable
    ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = initViewBinding(inflater);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        return binding.getRoot();
    }

    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected abstract void initView();

    protected abstract V initViewBinding(LayoutInflater inflater);

    @Override
    public final void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        clickView(view);
    }

    protected void clickView(View view) {

    }
}
