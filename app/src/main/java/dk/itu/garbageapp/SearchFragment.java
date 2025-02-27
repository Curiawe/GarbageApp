package dk.itu.garbageapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SearchFragment extends Fragment {

    private ItemsViewModel sorter;
    private EditText prompt;


    public SearchFragment() {
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
        final View v = inflater.inflate(R.layout.search_fragment, container, false);

        //TextView description = findViewById(R.id.task_description);
        // do we really need this if we don't interact with it?

        // EditText by id
        prompt = v.findViewById(R.id.main_input);
        prompt.setHint("What do you want to recycle?");

        Button where = v.findViewById(R.id.where_button);

        //@Override
        where.setOnClickListener(view -> {
            // let's get the input and ensure that it has no weird capitalization and no trailing white spaces
            String input = prompt.getText().toString();
            // look up the input in our ItemsDB
            String result = sorter.lookUp(input);
            prompt.setText(result);
        });

        return v;
    }
}