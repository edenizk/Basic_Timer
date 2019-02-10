package com.example.me.timer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtTime;
    private ProgressBar pbTime;
    private EditText edtTime;
    private Button btnStartStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTime = findViewById(R.id.txtTime);

        btnStartStop = findViewById(R.id.btnStartStop);
        btnStartStop.setText("START");

        pbTime = findViewById(R.id.pbTime);

        edtTime = findViewById(R.id.edtTime);

        final Timer myTimer = new Timer(getLongFromText(), txtTime, pbTime, btnStartStop);

        edtTime.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    myTimer.stopTimer();
                    txtTime.setText(edtTime.getText());
                    myTimer.setTimeLeftMillSec(getLongFromText());
                    return true;
                }
                return false;
            }
        });

        btnStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnStartStop.getText() == "START")
                    myTimer.startTimer();
                else
                    myTimer.stopTimer();
            }
        });
    }

    public long getLongFromText()
    {
        long timeLong;
        String[] minAndSec = txtTime.getText().toString().split(":");

        timeLong = Integer.parseInt(minAndSec[0]) * 60000;
        timeLong += Integer.parseInt(minAndSec[1]) * 1000;

        return timeLong;
    }
}
