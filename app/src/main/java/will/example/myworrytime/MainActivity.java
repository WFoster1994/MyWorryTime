package will.example.myworrytime;

import android.content.Context;
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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    //Non-public/static field names start with m. They are a member
    private ListView mListView;
    private EditText mEditText;
    private Button mButton;

    private ArrayList<String> mArrayList;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worry_list);

        mEditText = findViewById(R.id.edit_worry);
        mButton = findViewById(R.id.add_worry_button);
        mListView = findViewById(R.id.worries_list_view);

        mArrayList = WorryHelper.readData(this);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mArrayList);
        mListView.setAdapter(mAdapter);

        /*The ArrayAdapter acts as a controller in this MVC relationship
          The code looks up the ListView by using the id properties defined
          in the worry_row and row_text layouts*/
        /*ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.worry_row,
                R.id.row_text,
                new String[]{"first record", "second record", "third record"}
        );
        mListView.setAdapter(arrayAdapter);*/

        mButton.setOnClickListener(this);
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

                WorryHelper.writeData(mArrayList, this);

                Toast.makeText(this, "Worry Added", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mArrayList.remove(position);
        mAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Worry Deleted", Toast.LENGTH_SHORT).show();
    }
}
