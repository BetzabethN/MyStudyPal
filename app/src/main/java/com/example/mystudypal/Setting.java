package com.example.mystudypal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mystudypal.databinding.ActivitySettingBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;

public class Setting extends AppCompatActivity {

    private ActivitySettingBinding binding;
    private final String saveFileName = "info.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.doneBtn.setOnClickListener(v -> goToMain());
    }

    private  void goToMain() {
        String pomoTime = binding.pomoTime.getText().toString() ;
        String shortBreakTime = binding.shortbreakTime.getText().toString();
        String longBreakTime = binding.longbreakTime.getText().toString();
        boolean autoBreak = binding.switchBreak.isChecked();
        boolean autoPomo = binding.switchPomo.isChecked();
        String intervalTime = binding.intervalTime.getText().toString();


        String filename = "info.json";
        String fileContents = String.format("{\n" +
                "  \"pomoTime\": %s,\n" +
                "  \"shortBreakTime\": %s,\n" +
                "  \"longBreakTime\": %s,\n" +
                "  \"autoBreak\": %b,\n" +
                "  \"autoPomo\": %b,\n" +
                "  \"interval\": %s,\n" +
                "  \"finalInterval\": 1,\n" +
                "  \"running\": false\n" +
                "}", pomoTime, shortBreakTime, longBreakTime, autoBreak, autoPomo, intervalTime);
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, this.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}