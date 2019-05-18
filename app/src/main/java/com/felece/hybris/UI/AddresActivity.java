package com.felece.hybris.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felece.hybris.DialogCallback;
import com.felece.hybris.HybrisApp;
import com.felece.hybris.R;
import com.felece.hybris.UI.Adapters.AddressListRecylerViewAdapter;
import com.felece.hybris.Utility.Constant;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.enums.FIELDS;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.felece.hybris_network_sdk.data.network.entities.user.AddressList;
import com.google.android.material.button.MaterialButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddresActivity extends BaseActivity {


    @Inject
    DataManager dataManager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_addres_add_addres_button)
    MaterialButton activityAddresAddAddresButton;
    @BindView(R.id.activity_addres_recylerview)
    RecyclerView activityAddresRecylerview;

    AddressListRecylerViewAdapter addressListRecylerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addres);
        ButterKnife.bind(this);

        ((HybrisApp) getApplication()).getActivityComponent().injectAddressActivity(this);
        setSupportActionBar(toolbar);

        getAdress();

    }

    private void getAdress() {
        showLoading();
        dataManager.getUserAdresses(null, FIELDS.FULL.getFieldType(),new ServiceCallback<AddressList>() {
            @Override
            public void onSuccess(AddressList response) {
                addressListRecylerViewAdapter=new AddressListRecylerViewAdapter(response.getAddresses(), new AddressListRecylerViewAdapter.ItemListener() {
                    @Override
                    public void onItemClick(Address item) {

                        showDialogWithChoose("Bilgi", "Adresi silmek istediğinize emin misiniz?", "Evet", "Hayır", new DialogCallback() {
                            @Override
                            public void pressedPossitiveButton() {
                                showLoading();

                                dataManager.deleteUserAdress(item.getId(), new ServiceCallback<Address>() {
                                    @Override
                                    public void onSuccess(Address response) {
                                        hideLoading();
                                        getAdress();

                                    }

                                    @Override
                                    public void onError(int code, String errorResponse) {
                                        showMessage(errorResponse);
                                        hideLoading();
                                    }
                                });
                            }

                            @Override
                            public void pressedNegativeButton() {

                            }
                        });






                    }

                    @Override
                    public void onEditItemClick(Address item) {
                        Intent intent=new Intent(AddresActivity.this,CreateNewAddress.class);
                        intent.putExtra(Constant.BUNDLE_ADDRESS_ID,item.getId());
                        startActivity(intent);
                    }
                });

                LinearLayoutManager manager = new LinearLayoutManager(AddresActivity.this, LinearLayoutManager.VERTICAL, false);
                activityAddresRecylerview.setLayoutManager(manager);
                activityAddresRecylerview.setAdapter(addressListRecylerViewAdapter);
                hideLoading();
            }

            @Override
            public void onError(int code, String errorResponse) {
                showMessage(errorResponse);
                hideLoading();
            }
        });
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        getAdress();
    }

    @OnClick(R.id.activity_addres_add_addres_button)
    public void onViewClicked() {
        Intent intent=new Intent(AddresActivity.this,CreateNewAddress.class);
        startActivity(intent);
    }
}
