package dk.itu.garbageapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;

import androidx.fragment.app.Fragment;

public class AddFragment extends Fragment {

    private ItemsDB sorter;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ItemsDB.initialize(getActivity());
        sorter = ItemsDB.get();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.add_fragment, container, false);

        // get UI elements
        TextView allItems = v.findViewById(R.id.all_imtems_label);
        allItems.setText(sorter.listAll());
        allItems.setMovementMethod(ScrollingMovementMethod.getInstance());

        return v;
    }

}