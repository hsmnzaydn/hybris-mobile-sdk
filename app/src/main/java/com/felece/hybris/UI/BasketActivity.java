package com.felece.hybris.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felece.hybris.HybrisApp;
import com.felece.hybris.R;
import com.felece.hybris.UI.Adapters.BasketListRecylerViewAdapter;
import com.felece.hybris.Utility.Constant;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.Entry;
import com.felece.hybris_network_sdk.data.network.entities.enums.FIELDS;
import com.felece.hybris_network_sdk.data.network.entities.order.Cart;
import com.felece.hybris_network_sdk.data.network.entities.order.CartList;
import com.felece.hybris_network_sdk.data.network.entities.order.OrderEntry;
import com.felece.hybris_network_sdk.data.network.entities.voucher.Voucher;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BasketActivity extends BaseActivity {

    @Inject
    DataManager dataManager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_basket_start_shipping_button)
    MaterialButton activityBasketStartShippingButton;
    @BindView(R.id.activity_basket_empty_card_view)
    CardView activityBasketEmptyCardView;

    @BindView(R.id.activity_basket_recylerview)
    RecyclerView activityBasketRecylerview;
    @BindView(R.id.activity_basket_voucher_edit_text)
    TextInputEditText activityBasketVoucherEditText;
    @BindView(R.id.activity_basket_voucher_apply_button)
    MaterialButton activityBasketVoucherApplyButton;
    @BindView(R.id.activity_basket_total_price_text_view)
    TextView activityBasketTotalPriceTextView;
    @BindView(R.id.activity_basket_fill_card_view)
    CardView activityBasketFillCardView;
    Cart cart;

    BasketListRecylerViewAdapter basketListRecylerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        ButterKnife.bind(this);

        ((HybrisApp) getApplication()).getActivityComponent().injectBasketActivity(this);
        setSupportActionBar(toolbar);
        setTitle("Sepetim");


        getCarts();
    }

    public void getCarts() {
        showLoading();

        dataManager.getCarts(null, FIELDS.FULL.getFieldType(), false, null, null, null, new ServiceCallback<CartList>() {
            @Override
            public void onSuccess(CartList response) {
                if (response.getCarts().size() != 0) {

                    if (response.getCarts().get(0).getEntries().size() == 0) {
                        activityBasketEmptyCardView.setVisibility(View.VISIBLE);
                        activityBasketFillCardView.setVisibility(View.GONE);

                    } else {
                        activityBasketEmptyCardView.setVisibility(View.GONE);
                        cart = response.getCarts().get(0);
                        activityBasketTotalPriceTextView.setText(cart.getTotalPrice().getFormattedValue());
                        basketListRecylerViewAdapter = new BasketListRecylerViewAdapter(cart.getEntries(), new BasketListRecylerViewAdapter.ItemListener() {
                            @Override
                            public void onItemClick(OrderEntry item) {

                            }

                            @Override
                            public void onDeleteClick(OrderEntry item) {
                                dataManager.deleteEntryFromCart(cart.getCode(), item.getEntryNumber(), new ServiceCallback<Entry>() {
                                    @Override
                                    public void onSuccess(Entry response) {
                                        getCarts();
                                    }

                                    @Override
                                    public void onError(int code, String errorResponse) {
                                        showMessage(errorResponse);
                                    }
                                });
                            }
                        });

                        LinearLayoutManager manager = new LinearLayoutManager(BasketActivity.this, LinearLayoutManager.VERTICAL, false);
                        activityBasketRecylerview.setLayoutManager(manager);
                        activityBasketRecylerview.setAdapter(basketListRecylerViewAdapter);
                    }
                }

                hideLoading();
            }

            @Override
            public void onError(int code, String errorResponse) {
                showMessage(errorResponse);
                hideLoading();
            }
        });
    }


    @OnClick({R.id.activity_basket_voucher_apply_button, R.id.activity_basket_check_out_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_basket_voucher_apply_button:

                showLoading();
                dataManager.addVoucherToCart(cart.getCode(), activityBasketVoucherEditText.getText().toString(), new ServiceCallback<Voucher>() {
                    @Override
                    public void onSuccess(Voucher response) {

                        getCarts();
                        hideLoading();
                    }

                    @Override
                    public void onError(int code, String errorResponse) {

                        hideLoading();
                        showMessage(errorResponse);
                    }
                });


                break;
            case R.id.activity_basket_check_out_button:
                Intent intent = new Intent(this, DeliveryAdressActivity.class);
                intent.putExtra(Constant.BUNDLE_CART_ID, cart.getCode());
                startActivity(intent);
                break;
        }
    }
}
