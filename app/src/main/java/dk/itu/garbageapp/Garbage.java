package dk.itu.garbageapp;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

public class Garbage extends AppCompatActivity {

    private ItemsViewModel sorter;
    private FragmentManager fm;
    Fragment fragmentList, fragmentAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.garbage);
        fm = getSupportFragmentManager();
        setUpFragments();
    }

    private void setUpFragments() {
        fragmentList= fm.findFragmentById(R.id.recycler_view);
        fragmentAdd = fm.findFragmentById(R.id.container_add);

        if (fragmentList == null && fragmentAdd == null) {
            fragmentAdd = new AddFragment();
            fragmentList = new ListFragment();
            fm.beginTransaction()
                    .add(R.id.container_add, fragmentAdd)
                    .add(R.id.recycler_view, fragmentList)
                    .commit();
        }
    }
}