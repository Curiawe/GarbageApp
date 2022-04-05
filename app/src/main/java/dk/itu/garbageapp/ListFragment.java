package dk.itu.garbageapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListFragment extends Fragment {

    private RecyclerView list;

    // Model: ItemsDB

    private ItemsViewModel sorter;

    public ListFragment() {
        // Required empty public constructor
    }

    private class ItemHolder extends RecyclerView.ViewHolder  {
        private final TextView mNo , mItemText;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            mNo = itemView.findViewById(R.id.number);
            mItemText = itemView.findViewById(R.id.list_item);
            itemView.setOnClickListener((View.OnClickListener) this);
        }

        public void bind(Item item, int position) {
            mNo.setText(" " + position + " ");
            mItemText.setText(item.toString());
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        @NonNull
        public ItemHolder onCreateViewHolder (@NonNull ViewGroup parent , int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(requireContext());
            View v = layoutInflater.inflate(R.layout.items_list_row, parent, false);
            return (new ItemHolder(v));
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
            Item item = sorter.getItem(position);
            holder.bind(item, position);
        }

        @Override
        public int getItemCount() {
            return sorter.getSize();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sorter = new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        list = v.findViewById(R.id.recycler_view);
        list.setLayoutManager(new LinearLayoutManager(requireContext()));

        ItemAdapter adapter = new ItemAdapter();
        list.setAdapter(adapter);
        sorter.getValue().observe(this, sorter -> {adapter.notifyDataSetChanged();});

        return v;
    }
}