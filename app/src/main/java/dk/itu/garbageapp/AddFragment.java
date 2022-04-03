package dk.itu.garbageapp;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

public class AddFragment extends Fragment {

    private EditText item;
    private EditText type;
    private Button addbtn;

    // Model: Database of items
    private ItemsViewModel sorter;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sorter = new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.add_fragment, container, false);

        // get UI elements
        item = v.findViewById(R.id.add_item_name);
        type = v.findViewById(R.id.add_item_type);
        addbtn = v.findViewById(R.id.add_button);

        addbtn.setOnClickListener(view -> {
            String itemName = item.getText().toString();
            String itemType = type.getText().toString();
            sorter.addItem(itemName, itemType);

            item.setText("");
            type.setText("");

        });

        return v;
    }

}