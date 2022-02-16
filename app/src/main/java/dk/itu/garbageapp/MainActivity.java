package dk.itu.garbageapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Garbage V1_a

    // Instantiate Map

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate Sorter
        // have to do that first because we want to call a function from the sorter in the GUI

        ItemsDB.initialize(MainActivity.this);
        ItemsDB sorter = ItemsDB.get();

        //TextView description = findViewById(R.id.task_description);
        // do we really need this if we don't interact with it?

        // EditText by id
        EditText prompt = findViewById(R.id.main_input);
        prompt.setHint("What do you want to recycle?");

        Button where = findViewById(R.id.where_button);
        Button navBtnAdd = findViewById(R.id.nav_add_button);
        //@Override
        where.setOnClickListener(view -> {
            // let's get the input and ensure that it has no weird capitalization and no trailing white spaces
            String input = prompt.getText().toString().toLowerCase().trim();
            // look up the input in our ItemsDB
            String result = sorter.lookUp(input);
            prompt.setText(result);
        });

        navBtnAdd.setOnClickListener(view -> {
            // create intent and start new activity!
            Intent intent = new Intent(MainActivity.this, Add_Item.class);
            startActivity(intent);
        });
    }
}