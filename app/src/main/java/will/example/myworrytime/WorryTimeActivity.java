package will.example.myworrytime;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WorryTimeActivity extends AppCompatActivity {

   private Button startButton;
   private Button completeButton;
   private Button cancelButton;

    private Thread animationThread;
    private ClockView mClockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worry_time);

        startButton = findViewById(R.id.begin_time_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClockView();
            }
        });

        completeButton = findViewById(R.id.complete_time_button);
        //Ensure that the user cannot complete immediately.
        completeButton.setEnabled(false);

        cancelButton = findViewById(R.id.cancel_time_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
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

    }


    public void startClockView() {
        Intent intent = new Intent(this, ClockView.class);
        startActivity(intent);
    }

    public void returnToHome() {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }
}
