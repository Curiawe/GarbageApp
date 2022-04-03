package dk.itu.garbageapp;

import androidx.annotation.NonNull;

public class Item implements Comparable<Item> {
    private final String item;
    private final String category;


    public Item(String item, String category) {
        this.item = item;
        this.category = getCategory(category);
    }

    // let's make sure that our output is somewhat consistent
    private String getCategory (String input) {
        switch (input){
            case "bio":
                return new GarbageCategories().getBio();
            case "bulk":
                return new GarbageCategories().getBulk();
            case "cardboard":
                return new GarbageCategories().getCardboard();
            case "electro":
                return new GarbageCategories().getElectro();
            case "garden":
                return new GarbageCategories().getGarden();
            case "glass":
                return new GarbageCategories().getGlass();
            case "hazard":
                return new GarbageCategories().getHazard();
            case "metal":
                return new GarbageCategories().getMetal();
            case "paper":
                return new GarbageCategories().getPaper();
            case "plastic":
                return new GarbageCategories().getPlastic();
            case "rest":
                return new GarbageCategories().getResidual();
            default: return input;
        }
    }

    public String getItem() {
        return item;
    }

    @NonNull
    @Override
    public String toString() {
        return (item + " in: " + category);
    }

    // doing this in case I want to use a more efficient data structure than HashMap, or need this for future functionality
    @Override
    public int compareTo(Item input) {
        String inputItem = input.toString();
        return (item.compareTo(inputItem));
    }
}