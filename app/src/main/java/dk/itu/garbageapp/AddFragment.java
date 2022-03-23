package dk.itu.garbageapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Observable;
import java.util.Observer;

public class AddFragment extends Fragment {

    private ItemsViewModel sorter = new ViewModelProvider(this).get(ItemsViewModel.class);

    // EditText by id
    private EditText itemInput;
    private EditText typeInput;

    private Button addBtn;


    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.add_fragment, container, false);

        // get UI elements
        itemInput = itemInput.findViewById(R.id.add_item_name);
        typeInput = typeInput.findViewById(R.id.add_item_type);
        addBtn = addBtn.findViewById(R.id.add_button);

        addBtn.setOnClickListener(view -> {
            String itemName = itemInput.getText().toString().toLowerCase().trim();
            String itemType = typeInput.getText().toString().toLowerCase().trim();
            sorter.addItem(itemName, itemType);

            // ideally, there would be an alert here
            // but I don't understand the difference between Snackbar and Toast
            // so we're just clearing the input so the user sees that something happened.

            itemInput.setText("");
            typeInput.setText("");

        });

        return v;
    }





}