package dk.itu.garbageapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.text.method.ScrollingMovementMethod;

import androidx.fragment.app.Fragment;

import java.util.Observable;
import java.util.Observer;

public class AddFragment extends Fragment implements Observer {

    private ItemsDB sorter;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ItemsDB.initialize(getActivity());
        sorter = ItemsDB.get();
        sorter.addObserver(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.add_fragment, container, false);

        // get UI elements
        EditText allItems = v.findViewById(R.id.all_items);
        String s = sorter.listAll();
        allItems.setText(s);
        allItems.setMovementMethod(ScrollingMovementMethod.getInstance());

        return v;
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}