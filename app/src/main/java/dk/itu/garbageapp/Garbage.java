package dk.itu.garbageapp;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Garbage extends AppCompatActivity {

    private FragmentManager fm;
    Fragment fragmentSearch, fragmentAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garbage);
        fm = getSupportFragmentManager();
        setUpFragments();
    }

    private void setUpFragments() {
        fragmentSearch= fm.findFragmentById(R.id.container_search);
        fragmentAdd = fm.findFragmentById(R.id.container_add);

        if (fragmentSearch == null && fragmentAdd == null) {
            fragmentSearch = new SearchFragment();
            fragmentAdd = new AddFragment();
            fm.beginTransaction()
                    .add(R.id.container_add, fragmentAdd)
                    .add(R.id.container_search, fragmentSearch)
                    .commit();
        }
    }
}