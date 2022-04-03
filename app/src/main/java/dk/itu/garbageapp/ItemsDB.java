package dk.itu.garbageapp;

import android.content.Context;
import android.renderscript.ScriptGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ItemsDB {
    private final ArrayList<Item> sorter;
    private final Inputhandler handler = new Inputhandler();

    public ItemsDB(Context context) {
        sorter = new ArrayList<>();
        populateSorter(context);
    }

    /**
     * Find the garbage category a String value is assigned to.
     * Return the registered category in a natural sentence.
     *
     * Input NOT case sensitive.
     *
     * @see Item Item for Item behavior
     * @see ItemsViewModel ItemsViewModel for primary use
     *
     * @param input Based on user input
     * @return The registered category for a given input. Returns String "[input] should be placed in: not found" if there is no match.
     */
    public String lookUp(String input) {
        String cleanInput = handler.processString(input);
        try {
            // for debugging, can be removed
            System.out.println("beginning ItemsDB lookUp");
            for (int i = 0; i < sorter.size(); i++) {
                if (sorter.get(i).getItem() == cleanInput) {
                    return sorter.get(i).toString();
                } // else do nothing and go to the next one
            } // move on to final return statement if nothing was found
            // or no exception was thrown
        } catch(Exception e){
                return (input + " should be placed in: Database not initialized");
            }
            // if it's not in there, then...
            return (input + " should be placed in: not found");
    }

    /**
     * Adds an Item object to the ItemsDB.
     *
     * @see Item Item (reference to see Item behavior)
     * @see ItemsViewModel ItemsViewModel (primary point of use)
     *
     * @param item String of item name (like "salmon", "shampoo bottle" etc.)
     * @param category String of category name (like "bio", "bulk", "electro" etc.)
     */
    public void addItem (String item, String category) {
        sorter.add(new Item(handler.processString(item), handler.processString(category)));
    }

    // will need this for local version of Assignment 7

    /**
     * Removes Item object with name of user input from the ItemsDB.
     *
     * Input is NOT case sensitive
     *
     * @param item
     */
    public void delete(String item) {
        try {
            sorter.remove(item);
        } catch (Exception e) {
            System.out.println("Tried to remove " + item + ", but found no such item");
        }

    }

    /**
     *
     * @return String - Multi-line list of all String-representations of Item objects in the ItemsDB.
     *
     * @see ItemsViewModel
     */
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
                String[] gItem= line.split(", ");
                sorter.add(new Item(gItem[0], gItem[1]));
                line= reader.readLine();
            }
        } catch (IOException e) {  // Error occurred when opening raw file for reading.
            System.out.println("Tried to populate the ItemsDB sorter. Error: " + e.getMessage() + "\n" + e.getCause());
        }
    }

}
