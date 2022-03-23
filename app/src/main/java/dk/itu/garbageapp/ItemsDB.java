package dk.itu.garbageapp;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ItemsDB extends ViewModel {
    private static ItemsDB sItemsDb;

    private final ArrayList<Item> sorter;

    /**
     * Initialize the ItemsDB singleton.
     * Ensures that an ItemsDB object exists.
     */
    public static void initialize(Context context) {
        if (sItemsDb == null) {
            sItemsDb = new ItemsDB(context);
        }
    }

    public static ItemsDB get(){
        if(sItemsDb == null) throw new IllegalStateException("ItemsDB must be initialized");
        return sItemsDb;
    }

    private ItemsDB(Context context) {
        sorter = new ArrayList<>();
        new GarbageCategories();
        populateSorter(context);
    }

    /**
     * Find the garbage category a String value is assigned to.
     *
     * @param input Based on user input
     * @return The registered category for a given input. Returns "not found" if there is no match.
     */
    public String lookUp(String input) {
        if (sItemsDb.sorter == null) {
            return (input + " should be placed in: Database not initialized");
        } else {
            for (int i = 0; i < sorter.size(); i++) {
                if (sorter.get(i).toString().startsWith(input)) {
                    return sorter.get(i).toString();
                }
            }
        }
        // if it's not in there, then...
            return (input + " should be placed in: not found");
    }

    public void addItem (String item, String category) {
        sorter.add(new Item(item, category));
    }

    public void delete(String item) {
        sItemsDb.sorter.remove(item);
    }

    public String listAll() {
        StringBuilder result = new StringBuilder();

        for(Item item: sorter) {
            result.append("\n").append(item.toString());
        }
        return result.toString();
    }

    // setup method

    private void populateSorter(Context context){

        // try-catch block largely adapted from https://github.itu.dk/jst/MMAD2022
        try {
            BufferedReader reader= new BufferedReader(
                    new InputStreamReader(context.getAssets().open("items.txt")));
            String line= reader.readLine();
            while (line != null) {
                String[] gItem= line.split(",");
                sorter.add(new Item(gItem[0], gItem[1]));
                line= reader.readLine();
            }
        } catch (IOException e) {  // Error occurred when opening raw file for reading.
        }
    }

}
