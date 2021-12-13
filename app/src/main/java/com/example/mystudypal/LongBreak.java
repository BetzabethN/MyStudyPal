package com.example.mystudypal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import com.example.mystudypal.databinding.ActivityLongBreakBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LongBreak extends AppCompatActivity {

    private ActivityLongBreakBinding binding;
    private long timeLeft;
    private CountDownTimer pomoTimer;
    private boolean timerOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLongBreakBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.pomodoroBtn.setOnClickListener(v -> goToMain());
        binding.shortbrkBtn.setOnClickListener(v -> goToShortBreak());
        binding.reportBtn.setOnClickListener(v -> goToToDo());
        binding.settingsBtn.setOnClickListener(v -> goToSetting());

        try {
            String info = FileUtils.readFile(this, "info.json");
            JSONObject infoItem = new JSONObject(info);
            int longBreakTime = infoItem.getInt("longBreakTime");

            binding.startBtn.setOnClickListener(v -> timer(longBreakTime));
            binding.timer.setText(longBreakTime +":00");

            boolean autoBreak = infoItem.getBoolean("autoBreak");
            boolean running = infoItem.getBoolean("running");
            if (autoBreak && running) {timer(longBreakTime);}


        } catch (IOException | JSONException e){
            Log.i("DEBUG", "Can't read file");
        }

    }

    private void timer(int timerMins) {
        timeLeft = timerMins * 60000; // have to use milliseconds so multiply time by 60000
        pomoTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                updateTimeDisplay();
            }

            @Override
            public void onFinish() {
                goToMain();
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void goToToDo() {
        Intent intent = new Intent(this, ToDo.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private  void goToSetting() {
        Intent intent = new Intent(this, Setting.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}