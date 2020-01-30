package will.example.myworrytime;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

    public class WorryListActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

        //Non-public/static field names start with m. They are a member
        private ListView mListView;
        private EditText mEditText;
        private Button mAddWorryButton;
        private Button mSetupButton;
        private Button mHomeButton;

        private ArrayList<String> mArrayList;
        private ArrayAdapter<String> mAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.worry_list);

            mEditText = findViewById(R.id.edit_worry);
            mAddWorryButton = findViewById(R.id.add_worry_button);
            mSetupButton = findViewById(R.id.setup_time_button);

            mHomeButton = findViewById(R.id.home_button);
            mHomeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    returnToHome();
                }
            });

            mListView = findViewById(R.id.worries_list_view);

            mArrayList = WorryListHelper.readData(this);
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mArrayList);
            mListView.setAdapter(mAdapter);

            mAddWorryButton.setOnClickListener(this);
            mListView.setOnItemClickListener(this);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @Override
        /* Once something is typed into the edit text field
         * it will be saved into the items list */
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.add_worry_button:
                    String textEntered = mEditText.getText().toString();
                    mAdapter.add(textEntered);
                    //Ensure the edit text field reverts to be empty
                    mEditText.setText("");

                    WorryListHelper.writeData(mArrayList, this);

                    Toast.makeText(this, "Worry Added", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            //AlertDialog.Builder builder = new AlertDialog.Builder(WorryListActivity.this);
          //  builder.setMessage("Would you like to remove this worry?")
                   // .etPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        //@Override
                        //public void onClick(DialogInterface dialog, int which) {
                            mArrayList.remove(position);
                            mAdapter.notifyDataSetChanged();
                            Toast.makeText(this, "Worry Removed", Toast.LENGTH_SHORT).show();
                        //}
                   // }).setNegativeButton("No", null);

        }

        public void returnToHome(){
            Intent intent = new Intent (this, MainActivity.class);
            startActivity(intent);
        }
    }
