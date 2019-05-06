package com.felece.hybris;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.product.Product;
import com.felece.hybris_network_sdk.data.network.entities.search.facetdata.ProductSearchPage;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);

        ((HybrisApp) getApplication()).getActivityComponent().injectProductListActivity(this);

        getSearchProduct(query, currentPage, pageSize, null, null, null);


    }

    public void getSearchProduct(String query, final Integer currentPage, Integer pageSize, String sort, String fields, String searchQueryContext) {
        showLoading();
        dataManager.searchProduct(null, query, currentPage, pageSize, sort, fields, searchQueryContext, new ServiceCallback<ProductSearchPage>() {
            @Override
            public void onSuccess(ProductSearchPage response) {

                productListRecylerViewAdapter = new ProductListRecylerViewAdapter(response.getProducts(), new ProductListRecylerViewAdapter.ItemListener() {
                    @Override
                    public void onItemClick(Product item) {

                    }
                });
                LinearLayoutManager manager = new LinearLayoutManager(ProductListActivity.this, LinearLayoutManager.VERTICAL, false);
                activityProductListRecylerView.setLayoutManager(manager);
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
}
