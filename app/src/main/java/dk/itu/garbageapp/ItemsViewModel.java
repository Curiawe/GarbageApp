package dk.itu.garbageapp;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemsViewModel extends ViewModel {
    private static MutableLiveData<ItemsDB> itemsDB;

    public ItemsViewModel(Context context) {
        ItemsDB.initialize(context);
        itemsDB = new MutableLiveData<>();
        itemsDB.setValue(ItemsDB.get());
    }

    public MutableLiveData<ItemsDB> getValue() {
        return itemsDB;
    }

    public void addItem (String item, String type) {
        ItemsDB temp = itemsDB.getValue();
        temp.addItem(item, type);
        itemsDB.setValue(temp);
    }
}
