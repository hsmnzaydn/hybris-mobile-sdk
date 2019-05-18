package com.felece.hybris.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felece.hybris.HybrisApp;
import com.felece.hybris.R;
import com.felece.hybris.UI.Adapters.ProductListRecylerViewAdapter;
import com.felece.hybris.Utility.Constant;
import com.felece.hybris.Utility.EndlessOnScrollListener;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.enums.FIELDS;
import com.felece.hybris_network_sdk.data.network.entities.product.Product;
import com.felece.hybris_network_sdk.data.network.entities.search.facetdata.ProductSearchPage;
import com.felece.hybris_network_sdk.data.network.entities.search.pagedata.Sort;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.activity_product_list_filter_image_view)
    ImageView activityProductListFilterImageView;

    List<String> sorts=new ArrayList<>();
    List<Product> products=new ArrayList<>();
    ProductSearchPage productSearchPage;
    int size=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);

        ((HybrisApp) getApplication()).getActivityComponent().injectProductListActivity(this);

        getSearchProduct(query, currentPage, pageSize, null, FIELDS.FULL.getFieldType(), null,false);
        setSupportActionBar(toolbar);
        setTitle("Ürünler");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                String input = newText.toLowerCase();
                query = input;
                getSearchProduct(input, currentPage, pageSize, null, null, null,false);
                return false;
            }
        });


    }

    public void getSearchProduct(String query, final Integer currentPage, Integer pageSize, String sort, String fields, String searchQueryContext,boolean pagenation) {
        showLoading();
        dataManager.searchProduct(null, query, currentPage, pageSize, sort, fields, searchQueryContext, new ServiceCallback<ProductSearchPage>() {
            @Override
            public void onSuccess(ProductSearchPage response) {
                if(pagenation){
                    products.addAll(response.getProducts());
                }else {
                    products=response.getProducts();
                }
                productSearchPage=response;

                productListRecylerViewAdapter = new ProductListRecylerViewAdapter(products, new ProductListRecylerViewAdapter.ItemListener() {
                    @Override
                    public void onItemClick(Product item) {
                        Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                        intent.putExtra(Constant.BUNDLE_PRODUCT_ID, item.getCode());
                        startActivity(intent);

                    }
                });
                sorts.clear();
               for(Sort sort1:response.getSorts()){
                   sorts.add(sort1.getName());
               }

               GridLayoutManager gridLayoutManager=new GridLayoutManager(ProductListActivity.this,2);
                activityProductListRecylerView.setLayoutManager(gridLayoutManager);
                activityProductListRecylerView.setAdapter(productListRecylerViewAdapter);


           /*     activityProductListRecylerView.addOnScrollListener(new EndlessOnScrollListener(gridLayoutManager) {
                    @Override
                    public void onScrolledToEnd() {
                        int page = currentPage;
                        page++;
                        getSearchProduct(query, page, pageSize, null, fields, null,true);
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

    @OnClick({R.id.activity_product_list_user_image_view, R.id.activitY_product_list_basket_image_view,R.id.activity_product_list_hamburger_image_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_product_list_user_image_view:
                Intent intentA = new Intent(ProductListActivity.this, ProfileActivity.class);
                startActivity(intentA);
                break;
            case R.id.activitY_product_list_basket_image_view:
                Intent intent = new Intent(ProductListActivity.this, BasketActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_product_list_hamburger_image_view:
                Intent intent1=new Intent(ProductListActivity.this,CatalogsActivity.class);
                startActivity(intent1);
        }
    }

    @OnClick(R.id.activity_product_list_filter_image_view)
    public void onViewClicked() {
        showListDialog(sorts, "Filtrele", new ListSelectItem<Integer>() {
            @Override
            public void selectedItem(Integer select) {
                getSearchProduct(query,currentPage,pageSize,productSearchPage.getSorts().get(select).getCode(),null,null,false);
            }
        });
    }
}
