package com.felece.hybris.UI;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felece.hybris.HybrisApp;
import com.felece.hybris.R;
import com.felece.hybris.UI.Adapters.DeliveryAddressRecylerviewAdapter;
import com.felece.hybris.Utility.Constant;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.enums.FIELDS;
import com.felece.hybris_network_sdk.data.network.entities.order.Cart;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.felece.hybris_network_sdk.data.network.entities.user.AddressList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeliveryAdressActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_delivery_adress_recylerview)
    RecyclerView activityDeliveryAdressRecylerview;

    @Inject
    DataManager dataManager;

    DeliveryAddressRecylerviewAdapter addressRecylerviewAdapter;
    String cartId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_adress);
        ButterKnife.bind(this);

        ((HybrisApp) getApplication()).getActivityComponent().injectDeliveryAdressActivity(this);

        setSupportActionBar(toolbar);
        setTitle("Teslimat Adreslerim");

        Bundle bundle=getIntent().getExtras();

        if(bundle != null){
            cartId=bundle.getString(Constant.BUNDLE_CART_ID,"");

        }

        getUserAdresses();
    }

    private void getUserAdresses() {
        showLoading();
        dataManager.getUserAdresses(null, FIELDS.FULL.getFieldType(), new ServiceCallback<AddressList>() {
            @Override
            public void onSuccess(AddressList response) {
                addressRecylerviewAdapter=new DeliveryAddressRecylerviewAdapter(response.getAddresses(), new DeliveryAddressRecylerviewAdapter.ItemListener() {
                    @Override
                    public void onItemClick(Address item) {

                        dataManager.createDeliveryAddresForCart(null, item, cartId, new ServiceCallback<Cart>() {
                            @Override
                            public void onSuccess(Cart response) {

                            }

                            @Override
                            public void onError(int code, String errorResponse) {
                                showMessage(errorResponse);
                            }
                        });


                    }
                });

                LinearLayoutManager manager = new LinearLayoutManager(DeliveryAdressActivity.this, LinearLayoutManager.VERTICAL, false);
                activityDeliveryAdressRecylerview.setLayoutManager(manager);
                activityDeliveryAdressRecylerview.setAdapter(addressRecylerviewAdapter);
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
