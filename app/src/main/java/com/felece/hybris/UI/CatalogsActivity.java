package com.felece.hybris.UI;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.felece.hybris.HybrisApp;
import com.felece.hybris.R;
import com.felece.hybris.UI.Adapters.CatalogsListRecylerViewAdapter;
import com.felece.hybris.UI.Adapters.SubCatalogListRecylerViewAdapter;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogList;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CategoryHierarchy;
import com.felece.hybris_network_sdk.data.network.entities.enums.FIELDS;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CatalogsActivity extends BaseActivity {


    @BindView(R.id.activity_catalogs_recylerview)
    RecyclerView activityCatalogsRecylerview;
    @BindView(R.id.activity_catalogs_subcategories_recylerview)
    RecyclerView activityCatalogsSubcategoriesRecylerview;

    CatalogsListRecylerViewAdapter catalogsListRecylerViewAdapter;
    SubCatalogListRecylerViewAdapter subCatalogListRecylerViewAdapter;
    @Inject
    DataManager dataManager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogs);
        ButterKnife.bind(this);

        ((HybrisApp) getApplication()).getActivityComponent().injectCatalogsActivity(this);
        setSupportActionBar(toolbar);

        getCatalogs();
    }

    private void getCatalogs() {
        showLoading();
        dataManager.getCatalogs(null, FIELDS.DEFAULT.getFieldType(), new ServiceCallback<CatalogList>() {
            @Override
            public void onSuccess(CatalogList response) {
                catalogsListRecylerViewAdapter = new CatalogsListRecylerViewAdapter(response.getCatalogs().get(0).getCatalogVersions().get(0).getCategories(), new CatalogsListRecylerViewAdapter.ItemListener() {
                    @Override
                    public void onItemClick(CategoryHierarchy item) {
                        subCatalogListRecylerViewAdapter = new SubCatalogListRecylerViewAdapter(item.getSubcategories(), new SubCatalogListRecylerViewAdapter.ItemListener() {
                            @Override
                            public void onItemClick(CategoryHierarchy item) {

                            }
                        });

                        LinearLayoutManager manager = new LinearLayoutManager(CatalogsActivity.this, LinearLayoutManager.VERTICAL, false);
                        activityCatalogsSubcategoriesRecylerview.setLayoutManager(manager);
                        activityCatalogsSubcategoriesRecylerview.setAdapter(subCatalogListRecylerViewAdapter);


                    }
                });
                LinearLayoutManager manager = new LinearLayoutManager(CatalogsActivity.this, LinearLayoutManager.HORIZONTAL, false);
                activityCatalogsRecylerview.setLayoutManager(manager);
                activityCatalogsRecylerview.setAdapter(catalogsListRecylerViewAdapter);


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
