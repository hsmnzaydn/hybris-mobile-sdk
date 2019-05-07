package com.felece.hybris.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felece.hybris.HybrisApp;
import com.felece.hybris.R;
import com.felece.hybris.UI.Adapters.ProductListRecylerViewAdapter;
import com.felece.hybris.Utility.Constant;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.product.Product;
import com.felece.hybris_network_sdk.data.network.entities.search.facetdata.ProductSearchPage;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductListActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_product_list_recyler_view)
    RecyclerView activityProductListRecylerView;
    @Inject
    DataManager dataManager;

    ProductListRecylerViewAdapter productListRecylerViewAdapter;

    final int currentPage = 1;
    int pageSize = 50;
    String query = "a";
    @BindView(R.id.activity_product_list_user_image_view)
    ImageView activityProductListUserImageView;
    @BindView(R.id.activitY_product_list_basket_image_view)
    ImageView activitYProductListBasketImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);

        ((HybrisApp) getApplication()).getActivityComponent().injectProductListActivity(this);

        getSearchProduct(query, currentPage, pageSize, null, null, null);
        setSupportActionBar(toolbar);
        setTitle("Ürünler");

    }

    public void getSearchProduct(String query, final Integer currentPage, Integer pageSize, String sort, String fields, String searchQueryContext) {
        showLoading();
        dataManager.searchProduct(null, query, currentPage, pageSize, sort, fields, searchQueryContext, new ServiceCallback<ProductSearchPage>() {
            @Override
            public void onSuccess(ProductSearchPage response) {

                productListRecylerViewAdapter = new ProductListRecylerViewAdapter(response.getProducts(), new ProductListRecylerViewAdapter.ItemListener() {
                    @Override
                    public void onItemClick(Product item) {
                        Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                        intent.putExtra(Constant.BUNDLE_PRODUCT_ID, item.getCode());
                        startActivity(intent);

                    }
                });
                activityProductListRecylerView.setLayoutManager(new GridLayoutManager(ProductListActivity.this, 2));
                activityProductListRecylerView.setAdapter(productListRecylerViewAdapter);
               /* activityProductListRecylerView.addOnScrollListener(new EndlessOnScrollListener(manager) {
                    @Override
                    public void onScrolledToEnd() {
                        int page = currentPage;
                        page++;
                        getSearchProduct(query, page, pageSize, null, fields, null);
                    }
                });*/
                hideLoading();
            }

            @Override
            public void onError(int code, String errorResponse) {
                hideLoading();
                showMessage(errorResponse);
            }
        });
    }

    @OnClick({R.id.activity_product_list_user_image_view, R.id.activitY_product_list_basket_image_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_product_list_user_image_view:
                Intent intentA=new Intent(ProductListActivity.this,ProfileActivity.class);
                startActivity(intentA);
                break;
            case R.id.activitY_product_list_basket_image_view:
                Intent intent=new Intent(ProductListActivity.this,BasketActivity.class);
                startActivity(intent);
                break;
        }
    }
}
