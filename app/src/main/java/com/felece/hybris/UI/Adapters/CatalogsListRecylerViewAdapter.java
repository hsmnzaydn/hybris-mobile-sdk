package com.felece.hybris.UI.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.felece.hybris.R;
import com.felece.hybris_network_sdk.data.network.entities.catalog.Catalog;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CategoryHierarchy;
import com.felece.hybris_network_sdk.data.network.entities.product.Category;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CatalogsListRecylerViewAdapter extends RecyclerView.Adapter<CatalogsListRecylerViewAdapter.ViewHolder> {



    private List<CategoryHierarchy> myItems;
    private ItemListener myListener;

    public CatalogsListRecylerViewAdapter(List<CategoryHierarchy> items, ItemListener listener) {
        myItems = items;
        myListener = listener;
    }

    public void setListener(ItemListener listener) {
        myListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_catalog, parent, false)); // TODO
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
        void onItemClick(CategoryHierarchy item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CategoryHierarchy item;
        @BindView(R.id.row_catalog_image_view)
        ImageView rowCatalogImageView;
        @BindView(R.id.row_catalog_name_textview)
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }
        public void setData(CategoryHierarchy item) {
            this.item = item;
            Picasso.get().load("https://images-na.ssl-images-amazon.com/images/I/71mVF%2B93MPL._SY606_.jpg").into(rowCatalogImageView);
            textView.setText(item.getName());
        }

        @Override
        public void onClick(View v) {
            if (myListener != null) {
                myListener.onItemClick(item);
            }
        }
    }


}
