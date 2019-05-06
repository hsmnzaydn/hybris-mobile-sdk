package com.felece.hybris_network_sdk.data.network.services.ProductServices;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.search.facetdata.ProductSearchPage;

public interface ProductServices {

    void searchProduct(Class object, String query,
                       Integer currentPage,
                       Integer pageSize,
                       String sort,
                       String fields,
                       String searchQueryContext, ServiceCallback<ProductSearchPage> productSearchPageServiceCallback);

}
