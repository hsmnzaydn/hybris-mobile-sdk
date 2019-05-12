package com.felece.hybris.UI.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.felece.hybris.R;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressListRecylerViewAdapter extends RecyclerView.Adapter<AddressListRecylerViewAdapter.ViewHolder> {



    private List<Address> myItems;
    private ItemListener myListener;

    public AddressListRecylerViewAdapter(List<Address> items, ItemListener listener) {
        myItems = items;
        myListener = listener;
    }

    public void setListener(ItemListener listener) {
        myListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_address, parent, false)); // TODO
    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(myItems.get(position));
    }

    public interface ItemListener {
        void onItemClick(Address item);
        void onEditItemClick(Address item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // TODO - Your view members
        public Address item;
        @BindView(R.id.row_entry_header_text_view)
        TextView rowEntryHeaderTextView;
        @BindView(R.id.row_entry_description_text_view)
        TextView rowEntryDescriptionTextView;
        @BindView(R.id.row_address_delete_text_view)
        TextView rowDeleteTextView;
        @BindView(R.id.row_address_edit_text_view)
        TextView rowEditTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
            // TODO instantiate/assign view members
        }

        public void setData(Address item) {
            this.item = item;
            rowEntryHeaderTextView.setText(item.getFirstName()+" "+item.getLastName());
            rowEntryDescriptionTextView.setText(item.getFormattedAddress()+"\n"+item.getTown()+"/"+item.getCountry().getName());



            rowDeleteTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myListener.onItemClick(item);
                }
            });

            rowEditTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myListener.onEditItemClick(item);
                }
            });
            // TODO set data to view
        }

        @Override
        public void onClick(View v) {

        }
    }


}
