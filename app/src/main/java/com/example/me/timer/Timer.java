package com.example.me.timer;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Timer
{
    private CountDownTimer countDownTimer;
    private long timeLeftMillSec;
    private long totalTime;
    private TextView txtTime;
    private ProgressBar pbTime;
    private Button btnStartStop;

    public Timer(final long timeLeftMillSec, TextView txtTime, ProgressBar pbTime, Button btnStartStop)
    {
        this.timeLeftMillSec = timeLeftMillSec;
        totalTime = timeLeftMillSec;
        this.txtTime = txtTime;
        this.pbTime = pbTime;
        this.btnStartStop = btnStartStop;
    }

    public void setTimeLeftMillSec(long timeLeftMillSec)
    {
        totalTime = timeLeftMillSec;
        this.timeLeftMillSec = timeLeftMillSec;
    }

    public void startTimer()
    {
        pbTime.setMax((int)totalTime);

        countDownTimer = new CountDownTimer(timeLeftMillSec, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                timeLeftMillSec = millisUntilFinished;
                pbTime.setProgress(pbTime.getMax() - (int)timeLeftMillSec);
                updateTimer();
            }

            @Override
            public void onFinish()
            {
                pbTime.setProgress(pbTime.getMax());
                btnStartStop.setText("STOP");
            }
        }.start();

        btnStartStop.setText("STOP");
    }

    public void stopTimer()
    {
       countDownTimer.cancel();
        btnStartStop.setText("START");
    }

    private void updateTimer()
    {
        int min = (int) timeLeftMillSec / 60000;
        int sec = (int) timeLeftMillSec % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + min;
        timeLeftText += ":";

        if(sec < 10)
            timeLeftText += "0";

        timeLeftText += sec;
        txtTime.setText(timeLeftText);
     }
}
