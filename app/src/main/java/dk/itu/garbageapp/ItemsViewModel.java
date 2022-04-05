package dk.itu.garbageapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class ItemsViewModel extends AndroidViewModel {
    private static MutableLiveData<ItemsDB> itemsDB;

    public ItemsViewModel(Application application) {
        super(application);
        itemsDB = new MutableLiveData<>();
        itemsDB.setValue(new ItemsDB(application));
    }

    public MutableLiveData<ItemsDB> getValue() {
        return itemsDB;
    }

    public void addItem (String item, String type) {
        if (item == "" || type == "") {
            System.out.println("Item must have both a name and a category.");
        } else {
            ItemsDB temp = itemsDB.getValue();
            temp.addItem(item, type);
            itemsDB.setValue(temp);
            System.out.println("Added item successfully");
        }
    }

    public String lookUp(String input) {
        if (input == "") return ""; // not sure if we need this, but let's just make sure
        return itemsDB.getValue().lookUp(input);
    }

    /**
     * Input not case sensitive
     *
     * Remove Item object with input name from data structure.
     *
     * @param item name of the Item to remove
     */
    public void delete(String item) {
        ItemsDB temp = itemsDB.getValue();
        temp.delete(item);
        itemsDB.setValue(temp);
    }

    /**
     *
     * @return String - Multi-line list of all Items in the ItemsDB
     *
     * @see ItemsDB ItemsDB.listAll
     *
     */
    public String listAll() {
        return itemsDB.getValue().listAll();
    }

    public Item getItem(int position) {
        return itemsDB.getValue().get(position);
    }

    public int getSize() {return itemsDB.getValue().getSize();}

    public List<Item> getList(){return itemsDB.getValue().getList();}
}
