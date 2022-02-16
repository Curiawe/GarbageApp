package dk.itu.garbageapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Add_Item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        ItemsDB.initialize(Add_Item.this);
        ItemsDB sorter = ItemsDB.get();

        TextView description = findViewById(R.id.task_description); // we're not really doing anything with it, do we even need to instantiate it?

        // EditText by id
        EditText itemInput = findViewById(R.id.add_item_name);
        EditText typeInput = findViewById(R.id.add_item_type);

        // Note: Here, I'd really would have liked a dropdown for consistency
        //       And I started setting up a Spinner, but since it's not required it'll wait.
        // https://developer.android.com/guide/topics/ui/controls/spinner

        //Spinner select = findViewById(R.id.category_select);

        Button addBtn = findViewById(R.id.add_button);

        // this was not required but it's easier for testing ;)
        Button returnBtn = findViewById(R.id.return_main_button);

        addBtn.setOnClickListener(view -> {
            String itemName = itemInput.getText().toString().toLowerCase().trim();
            String itemType = typeInput.getText().toString().toLowerCase().trim();
            sorter.add(itemName, itemType);

            // ideally, there would be an alert here
            // but I don't understand the difference between Snackbar and Toast
            // so we're just clearing the input so the user sees that something happened.

            itemInput.setText("");
            typeInput.setText("");

        });

        returnBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Add_Item.this, MainActivity.class);
            startActivity(intent);
        });

    }

}