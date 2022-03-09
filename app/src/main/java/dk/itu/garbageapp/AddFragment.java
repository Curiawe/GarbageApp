package dk.itu.garbageapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Observable;
import java.util.Observer;

public class AddFragment extends Fragment implements Observer {

    private ItemsDB sorter;
    private TextView allItems;

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
        allItems = v.findViewById(R.id.all_items);
        allItems.setMovementMethod(ScrollingMovementMethod.getInstance());
        String s = sorter.listAll();
        allItems.setText(s);
        return v;
    }

    @Override
    public void update(Observable observable, Object o) {

        String s = sorter.listAll();
        allItems.setText(s);
    }
}