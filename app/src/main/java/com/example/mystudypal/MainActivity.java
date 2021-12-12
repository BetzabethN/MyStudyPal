package com.example.mystudypal;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceGroup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mystudypal.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private long timeLeft;
    private CountDownTimer pomoTimer;
    private boolean timerOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.shortbrkBtn.setOnClickListener(v -> goToShortBreak());
        binding.longbrkBtn.setOnClickListener(v -> goToLongBreak());
        binding.reportBtn.setOnClickListener(v -> goToReport());
        binding.settingsBtn.setOnClickListener(v -> goToSetting());



        try {
            String info = FileUtils.readFile(this, "info.json");
            JSONObject infoItem = new JSONObject(info);
            int pomoTime = infoItem.getInt("pomoTime");
            int interval = infoItem.getInt("interval");
            int finalInterval = infoItem.getInt("finalInterval");

            if (finalInterval == interval) {
                infoItem.put("finalInterval", 1);
                FileUtils.writeFile(this,"info.json", infoItem.toString());

            } else {
                infoItem.put("finalInterval", finalInterval+1);
                FileUtils.writeFile(this,"info.json", infoItem.toString());}


            binding.timer.setText(pomoTime +":00");
            binding.startBtn.setOnClickListener(v -> {
                try {
                    infoItem.put("running", true);
                    System.out.println();
                    FileUtils.writeFile(this,"info.json", infoItem.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                timer(pomoTime, finalInterval, interval);});


            boolean autoPomo = infoItem.getBoolean("autoPomo");
            boolean running = infoItem.getBoolean("running");
            if (autoPomo && running) {timer(pomoTime, finalInterval, interval);}

        } catch (IOException | JSONException e){
            Log.i("DEBUG", "Can't read file");
        }

    }

    private void timer(int timerMins, int finalInterval, int interval) {

        timeLeft = timerMins * 60000; // have to use milliseconds so multiply time by 60000
        pomoTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                updateTimeDisplay();
            }

            @Override
            public void onFinish() {
                if (finalInterval==interval) {goToLongBreak();}
                else {goToShortBreak();}
            }
        }.start();
    }



    private void updateTimeDisplay() {
        int displayMins = (int) (timeLeft / 60000);
        int displaySeconds = (int) ((timeLeft % 60000)/ 1000);

        String displayTime;

        displayTime = "";
        if (displayMins < 10) { displayTime += "0"; }
        displayTime += displayMins;
        displayTime += ":";
        if (displaySeconds < 10) { displayTime += "0"; }
        displayTime += displaySeconds;

        binding.timer.setText(displayTime);
    }

    private void goToShortBreak() {
        Intent intent = new Intent(this, ShortBreak.class);

        if (getIntent().hasExtra("Shortbreak_time")) {
            String shortBreakTime = getIntent().getStringExtra("Shortbreak_time") ;
            intent.putExtra("Shortbreak_time", shortBreakTime); }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goToLongBreak() {
        Intent intent = new Intent(this, LongBreak.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goToReport() {
        Intent intent = new Intent(this, Report.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private  void goToSetting() {
        Intent intent = new Intent(this, Setting.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}