package will.example.myworrytime;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class WorryTimeActivity extends AppCompatActivity {

    /*The start time is measured in milliseconds.
    900000 milliseconds is 15 minutes. */
    private static final long START_TIME = 900000;

    private TextView mCountDownTextView;
    private Button mStartPauseButton;
    private Button mCompleteButton;
    private Button mCancelButton;
    private Button mResetButton;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeft = START_TIME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worry_time);

        mCountDownTextView = findViewById(R.id.count_down_text);
        mStartPauseButton = findViewById(R.id.start_pause_button);
        mResetButton = findViewById(R.id.reset_time_button);

        mCompleteButton = findViewById(R.id.complete_time_button);
        //Ensure that the user cannot complete immediately.
        mCompleteButton.setEnabled(false);
        mCancelButton = findViewById(R.id.cancel_time_button);

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Using the builder design pattern.
                AlertDialog.Builder builder =  new AlertDialog.Builder(WorryTimeActivity.this);
                builder.setMessage("Are you sure you want to cancel this session?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Once "Yes" is clicked, the user will be taken to the home screen.
                                returnToHome();
                            }
                        }).setNegativeButton("No", null);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        mStartPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else
                    startTimer();
            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning){
                    resetTimer();
                }
            }
        });

        mCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToHome();
            }
        });

        /*When the view is created, the timer will display
        15:00 rather than 00:00*/
        updateCountDown();
    }

    public void returnToHome() {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

    private void startTimer() {
        //The timer will count down from 15 minutes and the interval will be every second
        mCountDownTimer = new CountDownTimer(mTimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft = millisUntilFinished;
                updateCountDown();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mStartPauseButton.setText("Start");
                mStartPauseButton.setVisibility(View.INVISIBLE);
                mResetButton.setVisibility(View.VISIBLE);
                mCompleteButton.setEnabled(true);
            }
        }.start();

        //Now the timer is running
        mTimerRunning = true;

        /*Now that the timer has begun, the start button changes to pause so the user
        is aware they can stop the countdown if needed*/
        mStartPauseButton.setText("Pause");

        //The user may not reset the time if needed once the timer begins
        mResetButton.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mStartPauseButton.setText("Begin");
        //Now that the timer is paused, the user may reset the time if needed
        mResetButton.setVisibility(View.VISIBLE);
    }

    private void resetTimer(){
        mTimeLeft = START_TIME;
        updateCountDown();
        mResetButton.setVisibility(View.INVISIBLE);
        mStartPauseButton.setVisibility(View.VISIBLE);

    }

    private void updateCountDown(){
        //This will turn the milliseconds into seconds and then into minutes
        int minutes = (int) (mTimeLeft / 1000) / 60;
        //Modus will return what remains after dividing by sixty.
        int seconds = (int) (mTimeLeft / 1000) % 60;

        String timeFormat = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        mCountDownTextView.setText(timeFormat);
    }
}
