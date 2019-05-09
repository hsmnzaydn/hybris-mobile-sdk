package com.felece.hybris.UI.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.felece.hybris.R;
import com.felece.hybris_network_sdk.data.network.entities.order.OrderEntry;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeliveryAddressRecylerviewAdapter extends RecyclerView.Adapter<DeliveryAddressRecylerviewAdapter.ViewHolder> {



    private List<Address> myItems;
    private ItemListener myListener;

    public DeliveryAddressRecylerviewAdapter(List<Address> items, ItemListener listener) {
        myItems = items;
        myListener = listener;
    }

    public void setListener(ItemListener listener) {
        myListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_delivery_address, parent, false)); // TODO
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
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public Address item;

        @BindView(R.id.row_delivery_address_check_box)
        CheckBox rowDeliveryAddressCheckBox;
        @BindView(R.id.row_delivery_address_title_text_view)
        TextView rowDeliveryAddressTitleTextView;
        @BindView(R.id.row_delivery_address_description_text_ivew)
        TextView rowDeliveryAddressDescriptionTextIvew;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);


        }

        public void setData(Address item) {
            this.item = item;

            rowDeliveryAddressTitleTextView.setText(item.getFirstName()+" "+item.getLastName());
            rowDeliveryAddressDescriptionTextIvew.setText(item.getFormattedAddress()+"\n"+item.getTown()+"/"+item.getCountry().getName());

            rowDeliveryAddressCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        myListener.onItemClick(item);
                    }
                }
            });


        }

        @Override
        public void onClick(View v) {

        }
    }


}
