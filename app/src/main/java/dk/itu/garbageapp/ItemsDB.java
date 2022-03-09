package dk.itu.garbageapp;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

public class ItemsDB extends Observable {
    private static ItemsDB sItemsDb;

    private final TreeMap<String, Item> sorter;

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
        sorter = new TreeMap<>();
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
        if (sItemsDb.sorter.get(input) == null) {
            return (input + " should be placed in: not found");
        } else {
            System.out.println("searching");

            return sItemsDb.sorter.get(input).toString();
        }
    }

    public void addItem (String item, String category) {
        sorter.put(item, new Item(item, category));
        this.setChanged(); notifyObservers();// mark as changed and notify observers of change
    }

    public String listAll() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry <String, Item> item: sorter.entrySet()) {
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
                sorter.put(gItem[0], new Item(gItem[0], gItem[1]));
                line= reader.readLine();
            }
            this.setChanged(); notifyObservers();
        } catch (IOException e) {  // Error occurred when opening raw file for reading.
        }
    }

}
