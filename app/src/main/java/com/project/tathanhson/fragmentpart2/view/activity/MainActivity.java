package com.project.tathanhson.fragmentpart2.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.project.tathanhson.fragmentpart2.MyApplication;
import com.project.tathanhson.fragmentpart2.R;
import com.project.tathanhson.fragmentpart2.databinding.ActivityMainBinding;
import com.project.tathanhson.fragmentpart2.view.fragment.DetailFragment;
import com.project.tathanhson.fragmentpart2.view.fragment.MainFragment;
import com.project.tathanhson.fragmentpart2.view.inteface.OnMainCallBack;
import com.project.tathanhson.fragmentpart2.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements OnMainCallBack {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private DetailFragment frgDetail;
    private MainFragment frgMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        getDataViewModel();
        frgMain = new MainFragment();
        frgMain.setCallBack(this);
        frgDetail = new DetailFragment();
        frgDetail.setCallBack(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, frgMain).commit();
    }

    private void getDataViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.readStoryFile(getAssets());

    }

    @Override
    public void showFragment(String tag, Object data) {
        if (tag.equals(MainFragment.TAG)) {
            showMainFragment(data);
        }
        else if (tag.equals(DetailFragment.TAG)) {
            showDetailFragment(data);
        }
    }

    private void showDetailFragment(Object data) {
        frgDetail = new DetailFragment();
        frgDetail.setCallBack(this);
        frgDetail.setData(data);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, frgDetail).addToBackStack(null).commit();

    }

    private void showMainFragment(Object data) {
        frgMain = new MainFragment();
        frgMain.setCallBack(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, frgMain).addToBackStack(null).commit();
    }
}