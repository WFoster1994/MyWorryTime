package will.example.myworrytime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Button current_worries_button = (Button) findViewById(R.id.launch_list_button);
        current_worries_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorryActivity();
            }
        });

        Button worry_time_button = (Button) findViewById(R.id.start_worrytime_button);
        worry_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorryTime();
            }
        });
    }

    public void openWorryActivity() {
        Intent intent = new Intent(this, WorryActivity.class);
        startActivity(intent);
    }

    public void openWorryTime() {
        Intent intent = new Intent(this, WorryTimeActivity.class);
        startActivity(intent);
    }
}

