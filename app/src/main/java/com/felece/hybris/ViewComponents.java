package com.felece.hybris;
import com.felece.hybris.UI.MainActivity;
import com.felece.hybris.UI.ProductDetailActivity;
import com.felece.hybris.UI.ProductListActivity;
import com.felece.hybris.UI.RegisterActivity;
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
}
