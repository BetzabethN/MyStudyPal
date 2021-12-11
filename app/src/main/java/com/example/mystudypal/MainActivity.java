package com.example.mystudypal;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mystudypal.databinding.ActivityMainBinding;

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

        binding.startBtn.setOnClickListener(v -> timer(25));
        binding.shortbrkBtn.setOnClickListener(v -> goToShortBreak());
        binding.longbrkBtn.setOnClickListener(v -> goToLongBreak());
        binding.reportBtn.setOnClickListener(v -> goToToDo());
        binding.settingsBtn.setOnClickListener(v -> goToSetting());

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

    private void goToLongBreak() {
        Intent intent = new Intent(this, LongBreak.class);
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