package com.felece.hybris_network_sdk.data.network.services.CartServices;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.order.CardTypeList;
import com.felece.hybris_network_sdk.data.network.entities.order.Cart;
import com.felece.hybris_network_sdk.data.network.entities.order.CartList;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;

public interface CartServices {


    void getCarts(Class object, String field, Boolean savedCartsOnly, Integer currentPage, Integer pageSize, String sort, String userId, ServiceCallback<CartList> cartListServiceCallback);
    void createOrUpdateCart(Class object, Cart cart,String field, String oldCartId, String toMergeCartGuid, String userId, ServiceCallback<Cart> cartServiceCallback);
    void deleteCart(String userId,String cartId,ServiceCallback<Cart> cartServiceCallback);
    void getCart(Class object,String field,String userId,String cartId,ServiceCallback<Cart> cartServiceCallback);
    void deleteDeliveryAddresOfCart(String userId,String cartId,ServiceCallback<Cart> cartServiceCallback);
    void createDeliveryAddresForCart(Class object, Address address,String userId, String cartId, ServiceCallback<Cart> cartServiceCallback);
    void setDeliveryAddresToCart(String userId,String cartId,String addressId,ServiceCallback<Cart> cartServiceCallback);
}
