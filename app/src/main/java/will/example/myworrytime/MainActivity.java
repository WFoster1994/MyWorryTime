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

        Button currentWorriesButton = findViewById(R.id.launch_list_button);
        currentWorriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorryActivity();
            }
        });

        Button worryTimeButton = findViewById(R.id.start_worrytime_button);
        worryTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorryTime();
            }
        });
    }

    public void openWorryActivity() {
        Intent intent = new Intent(this, WorryListActivity.class);
        startActivity(intent);
    }

    public void openWorryTime() {
        Intent intent = new Intent(this, WorryTimeActivity.class);
        startActivity(intent);
    }
}

