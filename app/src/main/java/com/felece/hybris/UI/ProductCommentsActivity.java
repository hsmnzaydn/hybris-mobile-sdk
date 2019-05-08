package com.felece.hybris.UI;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.felece.hybris.HybrisApp;
import com.felece.hybris.R;
import com.felece.hybris.UI.Adapters.ProductCommectRecylerviewAdapter;
import com.felece.hybris.UI.Adapters.ProductDetailRecylerviewAdapter;
import com.felece.hybris.Utility.CommonUtils;
import com.felece.hybris.Utility.Constant;
import com.felece.hybris.Utility.LinePagerIndicatorDecoration;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.enums.FIELDS;
import com.felece.hybris_network_sdk.data.network.entities.product.Image;
import com.felece.hybris_network_sdk.data.network.entities.product.Product;
import com.felece.hybris_network_sdk.data.network.entities.product.ProductBase;
import com.felece.hybris_network_sdk.data.network.entities.product.Review;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductCommentsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_prduct_comments_recylerview)
    RecyclerView activityPrductCommentsRecylerview;

    ProductCommectRecylerviewAdapter adapter;
    private String productId;


    @Inject
    DataManager dataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_comments);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setTitle("Yorumlar");
        ((HybrisApp) getApplication()).getActivityComponent().injectProductCommentsActivity(this);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            productId = bundle.getString(Constant.BUNDLE_PRODUCT_ID, "");
            getProductDetail(productId);
        }
    }

    private void getProductDetail(String productId) {
        showLoading();
        dataManager.getProductDetail(null, productId, FIELDS.FULL.getFieldType(), new ServiceCallback<ProductBase>() {
            @Override
            public void onSuccess(ProductBase response) {
                adapter = new ProductCommectRecylerviewAdapter(response.getReviews(), new ProductCommectRecylerviewAdapter.ItemListener() {
                    @Override
                    public void onItemClick(Review item) {
                    }
                });
                LinearLayoutManager manager = new LinearLayoutManager(ProductCommentsActivity.this, LinearLayoutManager.VERTICAL, false);
                activityPrductCommentsRecylerview.setLayoutManager(manager);
                activityPrductCommentsRecylerview.setAdapter(adapter);
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
