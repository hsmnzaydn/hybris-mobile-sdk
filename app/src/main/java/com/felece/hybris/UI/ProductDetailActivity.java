package com.felece.hybris.UI;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felece.hybris.HybrisApp;
import com.felece.hybris.R;
import com.felece.hybris.UI.Adapters.ProductCommectRecylerviewAdapter;
import com.felece.hybris.Utility.CommonUtils;
import com.felece.hybris.Utility.Constant;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.enums.FIELDS;
import com.felece.hybris_network_sdk.data.network.entities.product.ProductBase;
import com.felece.hybris_network_sdk.data.network.entities.product.Review;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_product_detail_price_text_view)
    TextView activityProductDetailPriceTextView;
    @BindView(R.id.activity_product_detail_product_image_view)
    AppCompatImageView activityProductDetailProductImageView;
    @BindView(R.id.activity_product_detail_product_detail_text_view)
    TextView activityProductDetailProductDetailTextView;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.activity_product_detail_recylerview)
    RecyclerView activityProductDetailRecylerview;
    private String productId;

    ProductCommectRecylerviewAdapter adapter;
    @Inject
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        ((HybrisApp) getApplication()).getActivityComponent().injectProductDetailActivity(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            productId = bundle.getString(Constant.BUNDLE_PRODUCT_ID, "");
            getProductDetail(productId);
        }
        setSupportActionBar(toolbar);


    }

    private void getProductDetail(String productId) {
        showLoading();
        dataManager.getProductDetail(null, productId, FIELDS.FULL.getFieldType(), new ServiceCallback<ProductBase>() {
            @Override
            public void onSuccess(ProductBase response) {
                toolbar.setTitle(response.getName());
                CommonUtils.setHtmlTextToTextView(response.getDescription(), activityProductDetailProductDetailTextView);
                activityProductDetailPriceTextView.setText(response.getPrice().getFormattedValue());
                adapter = new ProductCommectRecylerviewAdapter(response.getReviews(), new ProductCommectRecylerviewAdapter.ItemListener() {
                    @Override
                    public void onItemClick(Review item) {

                    }
                });
                LinearLayoutManager manager = new LinearLayoutManager(ProductDetailActivity.this, LinearLayoutManager.VERTICAL, false);
                activityProductDetailRecylerview.setLayoutManager(manager);
                activityProductDetailRecylerview.setAdapter(adapter);
                hideLoading();
            }

            @Override
            public void onError(int code, String errorResponse) {
                showMessage(errorResponse);
                hideLoading();
            }
        });
    }
}
