package com.felece.hybris.UI.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.felece.hybris.R;
import com.felece.hybris_network_sdk.data.network.entities.order.OrderEntry;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BasketListRecylerViewAdapter extends RecyclerView.Adapter<BasketListRecylerViewAdapter.ViewHolder> {


    private List<OrderEntry> myItems;
    private BasketListRecylerViewAdapter.ItemListener myListener;

    public BasketListRecylerViewAdapter(List<OrderEntry> items, BasketListRecylerViewAdapter.ItemListener listener) {
        myItems = items;
        myListener = listener;
    }

    public void setListener(BasketListRecylerViewAdapter.ItemListener listener) {
        myListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_basket, parent, false)); // TODO
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
        void onItemClick(OrderEntry item);
        void onDeleteClick(OrderEntry item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // TODO - Your view members
        public OrderEntry item;


        @BindView(R.id.row_basket_image_view)
        ImageView rowBasketImageView;
        @BindView(R.id.row_basket_item_name_text_view)
        TextView rowBasketItemNameTextView;
        @BindView(R.id.row_basket_price_text_view)
        TextView rowBasketPriceTextView;
        @BindView(R.id.row_basket_qty_text_view)
        TextView rowBasketQtyTextView;
        @BindView(R.id.row_basket_delete_image_view)
        ImageView rowBasketDeleteImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
            // TODO instantiate/assign view members


        }

        public void setData(OrderEntry item) {
            this.item = item;

            // TODO set data to view
            rowBasketItemNameTextView.setText(item.getProduct().getName());
            rowBasketPriceTextView.setText(item.getBasePrice().getFormattedValue());
            rowBasketQtyTextView.setText(String.valueOf(item.getQuantity()));
            rowBasketDeleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myListener.onDeleteClick(item);
                }
            });

        }

        @Override
        public void onClick(View v) {

        }
    }


}
