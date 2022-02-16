package dk.itu.garbageapp;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class ItemsDB {
    private static ItemsDB sItemsDb;

    private final TreeMap<String, Item> sorter;
    private final GarbageCategories categories;

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
        categories = new GarbageCategories();
        populateSorter(context,"items.txt");
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

    public static void deleteItem(String input) {
            sItemsDb.sorter.remove(input);
    }

    public void add (String itemName, String itemType) {
        Item toAdd = new Item(itemName, itemType);
        sItemsDb.sorter.put(itemName, toAdd);
    }

    private boolean checkContains (String input) {
        return (sItemsDb.sorter.containsKey(input));
    }

    // setup methods

    private void populateSorter(Context context, String filename){
        // Loosely based on
        // https://international.kk.dk/live/housing/settling-into-your-new-home/recycling-in-copenhagen
        // Not Comprehensive!

        // try-catch block largely adapted from https://github.itu.dk/jst/MMAD2022
        try {
            BufferedReader reader= new BufferedReader(
                    new InputStreamReader(context.getAssets().open(filename)));
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
