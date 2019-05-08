package com.felece.hybris.UI.Adapters;

import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.felece.hybris.R;
import com.felece.hybris.Utility.CommonUtils;
import com.felece.hybris_network_sdk.data.network.entities.product.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductListRecylerViewAdapter extends RecyclerView.Adapter<ProductListRecylerViewAdapter.ViewHolder> {


    private List<Product> myItems;
    private ItemListener myListener;

    public ProductListRecylerViewAdapter(List<Product> items, ItemListener listener) {
        myItems = items;
        myListener = listener;
    }

    public void setListener(ItemListener listener) {
        myListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_product, parent, false)); // TODO
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
        void onItemClick(Product item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // TODO - Your view members
        public Product item;
        @BindView(R.id.row_entry_thumbnail_image_view)
        ImageView rowEntryThumbnailImageView;
        @BindView(R.id.cell_product_product_title_text_view)
        TextView rowEntryHeaderTextView;
        @BindView(R.id.cell_product_product_price_text_view)
        TextView rowEntryPriceTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this,itemView);
            // TODO instantiate/assign view members
        }

        public void setData(Product item) {
            this.item = item;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                rowEntryHeaderTextView.setText(Html.fromHtml(item.getName(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                rowEntryHeaderTextView.setText(Html.fromHtml(item.getName()));
            }
            Picasso.get().load("https://images-na.ssl-images-amazon.com/images/I/71mVF%2B93MPL._SY606_.jpg").into(rowEntryThumbnailImageView);

            rowEntryPriceTextView.setText(item.getPrice().getFormattedValue());
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
                                