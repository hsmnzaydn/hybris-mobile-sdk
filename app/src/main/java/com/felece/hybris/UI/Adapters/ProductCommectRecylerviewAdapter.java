package com.felece.hybris.UI.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.felece.hybris.R;
import com.felece.hybris_network_sdk.data.network.entities.product.Review;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductCommectRecylerviewAdapter extends RecyclerView.Adapter<ProductCommectRecylerviewAdapter.ViewHolder> {


    private List<Review> myItems;
    private ItemListener myListener;

    public ProductCommectRecylerviewAdapter(List<Review> items, ItemListener listener) {
        myItems = items;
        myListener = listener;
    }

    public void setListener(ItemListener listener) {
        myListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_comment, parent, false)); // TODO
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
        void onItemClick(Review item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // TODO - Your view members
        public Review item;
        @BindView(R.id.row_entry_header_text_view)
        TextView rowEntryHeaderTextView;
        @BindView(R.id.row_entry_description_text_view)
        TextView rowEntryDescriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this,itemView);
            // TODO instantiate/assign view members
        }

        public void setData(Review item) {
            this.item = item;
            rowEntryHeaderTextView.setText(item.getPrincipal().getName());
            rowEntryDescriptionTextView.setText(item.getComment());
            // TODO set data to view
        }

        @Override
        public void onClick(View v) {
            if (myListener != null) {
                myListener.onItemClick(item);
            }
        }
    }


}
                                