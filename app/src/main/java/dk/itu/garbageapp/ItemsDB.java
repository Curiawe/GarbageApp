package dk.itu.garbageapp;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class ItemsDB {

    private final TreeMap<String, Item> sorter;

    /**
     * Initialize the ItemsDB singleton.
     * Ensures that an ItemsDB object exists.
     */

    public ItemsDB get(){
        return ItemsDB.this;
    }

    public ItemsDB() {
        sorter = new TreeMap<>();
        new GarbageCategories();
        populateSorter(/**There needs to be a context here and I haven't figured out how to fix that*/);
    }

    /**
     * Find the garbage category a String value is assigned to.
     *
     * @param input Based on user input
     * @return The registered category for a given input. Returns "not found" if there is no match.
     */
    public String lookUp(String input) {
        if (get() == null) {
            return (input + " should be placed in: not found");
        } else {
            System.out.println("searching");

            return sorter.get(input).toString();
        }
    }

    public void addItem (String item, String category) {
        sorter.put(item, new Item(item, category));
    }

    // setup method

    private void populateSorter(Context context){

        // ok this isn't working if I want it to be working within the live data model
        // try-catch block largely adapted from https://github.itu.dk/jst/MMAD2022
        try {
            BufferedReader reader= new BufferedReader(
                    new InputStreamReader(context.getAssets().open("items.txt")));
            String line= reader.readLine();
            while (line != null) {
                String[] gItem= line.split(",");
                sorter.put(gItem[0], new Item(gItem[0], gItem[1]));
                line= reader.readLine();
            }
        } catch (IOException e) {  // Error occurred when opening raw file for reading.
        }
    }

}
