package com.felece.hybris.UI;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.felece.hybris.HybrisApp;
import com.felece.hybris.R;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.enums.FIELDS;
import com.felece.hybris_network_sdk.data.network.entities.enums.TYPE;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.felece.hybris_network_sdk.data.network.entities.user.Country;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateNewAddress extends BaseActivity {


    @Inject
    DataManager dataManager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_create_new_address_name_edit_text)
    TextInputEditText activityCreateNewAddressNameEditText;
    @BindView(R.id.activity_create_new_address_surname_edit_text)
    TextInputEditText activityCreateNewAddressSurnameEditText;
    @BindView(R.id.activity_create_new_address_country_edit_text)
    TextInputEditText activityCreateNewAddressCountryEditText;
    @BindView(R.id.activity_create_new_address_open_address_edit_text)
    TextInputEditText activityCreateNewAddressOpenAddressEditText;
    @BindView(R.id.activity_create_new_address_save_button)
    MaterialButton activityCreateNewAddressSaveButton;
    Address address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_address);
        ButterKnife.bind(this);

        ((HybrisApp) getApplication()).getActivityComponent().injectCreateNewAddress(this);
        address = new Address();


    }

    @OnClick({R.id.activity_create_new_address_country_edit_text, R.id.activity_create_new_address_save_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_create_new_address_country_edit_text:
              /*  dataManager.getContries(TYPE.BILLING.getType(), FIELDS.FULL.getFieldType(), null, new ServiceCallback<CountryList>() {
                    @Override
                    public void onSuccess(CountryList response) {
                        List<String> stringList=new ArrayList<>();

                        for(Country country:response.getCountries()){
                            stringList.add(country.getName());
                        }

                        showListDialog(stringList, "Ülke Seçiniz", new ListSelectItem<Integer>() {
                            @Override
                            public void selectedItem(Integer select) {
                                address.setCountry(response.getCountries().get(select));
                            }
                        });
                    }
                    @Override
                    public void onError(int code, String errorResponse) {

                    }
                });*/


                break;
            case R.id.activity_create_new_address_save_button:

                address.setTitleCode("ms");
                address.setTown("İstanbul");
                address.setFirstName(activityCreateNewAddressNameEditText.getText().toString());
                address.setLastName(activityCreateNewAddressSurnameEditText.getText().toString());
                address.setShippingAddress(true);
                address.setVisibleInAddressBook(true);
                address.setPostalCode("34000");
                address.setLine1(activityCreateNewAddressOpenAddressEditText.getText().toString());
                Country country = new Country();
                country.setName("turkey");
                country.setIsocode("TR");
                address.setCountry(country);
                showLoading();
                dataManager.createNewUserAdress(null, address, new ServiceCallback<Address>() {
                    @Override
                    public void onSuccess(Address response) {
                        hideLoading();
                    }

                    @Override
                    public void onError(int code, String errorResponse) {
                        hideLoading();
                        showMessage(errorResponse);
                    }
                });

                break;
        }
    }
}
