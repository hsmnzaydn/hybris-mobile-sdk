package com.felece.hybris.UI;

import android.os.Bundle;
import android.widget.TextView;

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
import com.felece.hybris_network_sdk.data.network.entities.order.Cart;
import com.felece.hybris_network_sdk.data.network.entities.order.CartList;
import com.felece.hybris_network_sdk.data.network.entities.order.CartModification;
import com.felece.hybris_network_sdk.data.network.entities.order.OrderEntry;
import com.felece.hybris_network_sdk.data.network.entities.product.Image;
import com.felece.hybris_network_sdk.data.network.entities.product.Product;
import com.felece.hybris_network_sdk.data.network.entities.product.ProductBase;
import com.google.android.material.button.MaterialButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_product_detail_product_text_view)
    TextView activityProductDetailTextView;
    @BindView(R.id.activity_product_thumb_recylerview)
    RecyclerView productThubRecylerView;
    @BindView(R.id.activity_product_detail_price_text_view)
    TextView activityProductDetailPriceTextView;
    @BindView(R.id.activity_product_detail_add_basket_button)
    MaterialButton activityProductDetailAddBasketButton;
    private String productId;
    ProductCommectRecylerviewAdapter adapter;
    @Inject
    DataManager dataManager;

    ProductDetailRecylerviewAdapter productDetailRecylerviewAdapter;
    OrderEntry orderEntry=new OrderEntry();
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
                Product product=new Product();
                product.setCode(response.getCode());
                orderEntry.setProduct(product);
                toolbar.setTitle(response.getName());
                productDetailRecylerviewAdapter = new ProductDetailRecylerviewAdapter(response.getImages(), new ProductDetailRecylerviewAdapter.ItemListener() {
                    @Override
                    public void onItemClick(Image item) {

                    }
                });

                CommonUtils.setHtmlTextToTextView(response.getDescription(), activityProductDetailTextView);
                activityProductDetailPriceTextView.setText(response.getPrice().getFormattedValue());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                LinePagerIndicatorDecoration linePagerIndicatorDecoration = new LinePagerIndicatorDecoration(150);
                productThubRecylerView.addItemDecoration(linePagerIndicatorDecoration);
                productThubRecylerView.setLayoutManager(mLayoutManager);
                productThubRecylerView.setItemAnimator(new DefaultItemAnimator());
                productThubRecylerView.setAdapter(productDetailRecylerviewAdapter);
                SnapHelper snapHelper = new PagerSnapHelper();
                snapHelper.attachToRecyclerView(productThubRecylerView);






                /*adapter = new ProductCommectRecylerviewAdapter(response.getReviews(), new ProductCommectRecylerviewAdapter.ItemListener() {
                    @Override
                    public void onItemClick(Review item) {
                    }
                });
                LinearLayoutManager manager = new LinearLayoutManager(ProductDetailActivity.this, LinearLayoutManager.VERTICAL, false);
                activityProductDetailRecylerview.setLayoutManager(manager);
                activityProductDetailRecylerview.setAdapter(adapter);*/
                hideLoading();
            }

            @Override
            public void onError(int code, String errorResponse) {
                showMessage(errorResponse);
                hideLoading();
            }
        });
    }

    @OnClick(R.id.activity_product_detail_add_basket_button)
    public void onViewClicked() {
        showLoading();
        dataManager.getCarts(null, FIELDS.DEFAULT.getFieldType(), null, null, null, null, new ServiceCallback<CartList>() {
            @Override
            public void onSuccess(CartList response) {
                if(response.getCarts().size() == 0){

                    Cart cart=new Cart();

                    dataManager.createOrUpdateCart(null, null, cart, null, null, new ServiceCallback<Cart>() {
                        @Override
                        public void onSuccess(Cart response) {

                            dataManager.addEntryToCart(null, orderEntry, response.getCode(), new ServiceCallback<CartModification>() {
                                @Override
                                public void onSuccess(CartModification response) {
                                    hideLoading();
                                    showMessage("Ürün başarıyla eklendi");
                                }

                                @Override
                                public void onError(int code, String errorResponse) {
                                    hideLoading();
                                    showMessage(errorResponse);
                                }
                            });

                        }

                        @Override
                        public void onError(int code, String errorResponse) {
                            hideLoading();
                            showMessage(errorResponse);
                        }
                    });
                }else {
                    dataManager.addEntryToCart(null, orderEntry, response.getCarts().get(0).getCode(), new ServiceCallback<CartModification>() {
                        @Override
                        public void onSuccess(CartModification response) {
                            hideLoading();
                            showMessage("Ürün başarıyla eklendi");
                        }

                        @Override
                        public void onError(int code, String errorResponse) {
                            hideLoading();
                            showMessage(errorResponse);
                        }
                    });
                }
            }

            @Override
            public void onError(int code, String errorResponse) {
                hideLoading();
                showMessage(errorResponse);
            }
        });
    }
}
