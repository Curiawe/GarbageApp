package dk.itu.garbageapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.method.MovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListFragment extends Fragment {

    private TextView list;

    // Model: ItemsDB

    private ItemsViewModel sorter;

    public ListFragment() {
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
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        list = v.findViewById(R.id.list_display);
        list.setText(sorter.listAll());

        sorter.getValue().observe(this,
                sorter -> {
                    list.setText(sorter.listAll());
                });

        return v;
    }
}