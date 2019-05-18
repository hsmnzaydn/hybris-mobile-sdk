package com.felece.hybris.UI.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.felece.hybris.R;
import com.felece.hybris.Utility.CommonUtils;
import com.felece.hybris_network_sdk.data.network.entities.product.Image;
import com.felece.hybris_network_sdk.data.network.entities.product.Review;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailRecylerviewAdapter extends RecyclerView.Adapter<ProductDetailRecylerviewAdapter.ViewHolder> {

    private List<Image> myItems;
    private ItemListener myListener;

    public ProductDetailRecylerviewAdapter(List<Image> items, ItemListener listener) {
        myItems = items;
        myListener = listener;
    }

    public void setListener(ItemListener listener) {
        myListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_product_slider, parent, false)); // TODO
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
        void onItemClick(Image item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // TODO - Your view members
        public Image item;
        @BindView(R.id.cell_product_slider_image_view)
        ImageView cellProductSliderImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
            // TODO instantiate/assign view members
        }

        public void setData(Image item) {
            this.item = item;
        //    CommonUtils.getImage(item.getUrl(),cellProductSliderImageView);

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
