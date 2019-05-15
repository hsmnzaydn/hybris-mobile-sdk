package com.felece.hybris;
import com.felece.hybris.UI.AddPaymentDetail;
import com.felece.hybris.UI.AddresActivity;
import com.felece.hybris.UI.BasketActivity;
import com.felece.hybris.UI.CatalogsActivity;
import com.felece.hybris.UI.CreateNewAddress;
import com.felece.hybris.UI.DeliveryAdressActivity;
import com.felece.hybris.UI.HistoryOrderActivity;
import com.felece.hybris.UI.MainActivity;
import com.felece.hybris.UI.ProductCommentsActivity;
import com.felece.hybris.UI.ProductDetailActivity;
import com.felece.hybris.UI.ProductListActivity;
import com.felece.hybris.UI.ProfileActivity;
import com.felece.hybris.UI.RegisterActivity;
import com.felece.hybris.UI.UserProfileActivity;
import com.felece.hybris_network_sdk.di.modules.HybrisDataModules;
import com.felece.hybris_network_sdk.di.modules.HybrisPresenterModules;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {HybrisPresenterModules.class, HybrisDataModules.class})
public interface ViewComponents {
    void injectMainActivity(MainActivity mainActivity);


    void injectRegisterActivity(RegisterActivity registerActivity);

    void injectProductListActivity(ProductListActivity productListActivity);

    void injectProductDetailActivity(ProductDetailActivity productDetailActivity);

    void injectBasketActivity(BasketActivity basketActivity);

    void injectProfileActivity(ProfileActivity profileActivity);

    void injectAddressActivity(AddresActivity addresActivity);

    void injectCreateNewAddress(CreateNewAddress createNewAddress);

    void injectProductCommentsActivity(ProductCommentsActivity productCommentsActivity);

    void injectDeliveryAdressActivity(DeliveryAdressActivity deliveryAdressActivity);

    void injectCatalogsActivity(CatalogsActivity catalogsActivity);

    void injectAddPaymentDetail(AddPaymentDetail addPaymentDetail);

    void injectUserProfileActivity(UserProfileActivity userProfileActivity);

    void injectHistoryOrderActivity(HistoryOrderActivity historyOrderActivity);
}
