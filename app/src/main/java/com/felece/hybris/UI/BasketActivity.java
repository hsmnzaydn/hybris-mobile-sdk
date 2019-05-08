package com.felece.hybris.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.felece.hybris.HybrisApp;
import com.felece.hybris.R;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.Entry;
import com.felece.hybris_network_sdk.data.network.entities.enums.FIELDS;
import com.felece.hybris_network_sdk.data.network.entities.order.CartList;
import com.felece.hybris_network_sdk.data.network.entities.order.OrderEntry;
import com.google.android.material.button.MaterialButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BasketActivity extends BaseActivity {

    @Inject
    DataManager dataManager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_basket_start_shipping_button)
    MaterialButton activityBasketStartShippingButton;
    @BindView(R.id.activity_basket_empty_card_view)
    CardView activityBasketEmptyCardView;
    @BindView(R.id.activity_basket_products_text_view)
    TextView activityBasketProductsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        ButterKnife.bind(this);

        ((HybrisApp) getApplication()).getActivityComponent().injectBasketActivity(this);
        setSupportActionBar(toolbar);
        setTitle("Sepetim");

        showLoading();
        dataManager.getCarts(null, FIELDS.FULL.getFieldType(), false, null, null, null, new ServiceCallback<CartList>() {
            @Override
            public void onSuccess(CartList response) {
                if(response.getCarts().size() != 0){
                    if (response.getCarts().get(0).getEntries().size() == 0) {

                    } else {
                        activityBasketEmptyCardView.setVisibility(View.GONE);
                        String text="";
                        for(OrderEntry entry:response.getCarts().get(0).getEntries()){
                            text=text+entry.getProduct().getName()+"\n";
                        }
                        text=text+response.getCarts().get(0).getTotalPrice().getFormattedValue();
                        activityBasketProductsTextView.setText(text);
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
}
