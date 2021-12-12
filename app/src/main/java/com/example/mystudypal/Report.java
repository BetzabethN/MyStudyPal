package com.example.mystudypal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mystudypal.databinding.ActivityReportBinding;

public class Report extends AppCompatActivity {

    private ActivityReportBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}