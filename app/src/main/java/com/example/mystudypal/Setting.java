package com.example.mystudypal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mystudypal.databinding.ActivitySettingBinding;

public class Setting extends AppCompatActivity {

    private ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}