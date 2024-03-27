package com.project.tathanhson.fragmentwithcallback;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.project.tathanhson.fragmentwithcallback.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements OnMainCallBack {
    private ActivityMainBinding binding;

    private FirstFragment frg1;
    private SecondFragment frg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        frg1 = new FirstFragment();
        frg1.setCallBack(this);
        frg2 = new SecondFragment();
        frg2.setCallBack(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frg, frg1).addToBackStack(null).commit();

    }

    @Override
    public void showFragment(String tag, Object data) {
        if (tag.equals(FirstFragment.TAG)) {
            showFirstFragment(data);
        }
        else if (tag.equals(SecondFragment.TAG)) {
            showSecondFragment(data);
        }
    }

    private void showSecondFragment(Object data) {
        frg2 = new SecondFragment();
        frg2.setData(data);
        getSupportFragmentManager().beginTransaction().replace(R.id.frg, frg2).addToBackStack(null).commit();
    }

    private void showFirstFragment(Object data) {
        frg1 = new FirstFragment();
        frg1.setCallBack(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frg, frg1).addToBackStack(null).commit();
    }
}