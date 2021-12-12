package com.example.mystudypal;

public class InfoModel {
    private int pomoTime;
    private int shortBreakTime;
    private int longBreakTime;
    private boolean autoBreak;
    private boolean autoPomo;
    private int interval;
    private boolean running;
    private int finalInterval;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getFinalInterval() {
        return finalInterval;
    }

    public void setFinalInterval(int finalInterval) {
        this.finalInterval = finalInterval;
    }

    public int getPomoTime() {
        return pomoTime;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public boolean isAutoBreak() {
        return autoBreak;
    }

    public void setAutoBreak(boolean autoBreak) {
        this.autoBreak = autoBreak;
    }

    public boolean isAutoPomo() {
        return autoPomo;
    }

    public void setAutoPomo(boolean autoPomo) {
        this.autoPomo = autoPomo;
    }

    public void setPomoTime(int pomoTime) {
        this.pomoTime = pomoTime;
    }

    public int getShortBreakTime() {
        return shortBreakTime;
    }

    public void setShortBreakTime(int shortBreakTime) {
        this.shortBreakTime = shortBreakTime;
    }

    public int getLongBreakTime() {
        return longBreakTime;
    }

    public void setLongBreakTime(int longBreakTime) {
        this.longBreakTime = longBreakTime;
    }
}
