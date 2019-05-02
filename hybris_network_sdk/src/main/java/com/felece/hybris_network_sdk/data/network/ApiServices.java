package com.felece.hybris_network_sdk.data.network;


import com.felece.hybris_network_sdk.data.network.services.CartServices.CartServices;
import com.felece.hybris_network_sdk.data.network.services.CatalogServices.CatalogServices;
import com.felece.hybris_network_sdk.data.network.services.CountriesServices.CountriesServices;
import com.felece.hybris_network_sdk.data.network.services.UserServices.UserServices;

public interface ApiServices extends CountriesServices, CatalogServices,
        UserServices, CartServices {
}
