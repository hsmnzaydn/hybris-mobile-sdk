package com.felece.hybris.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.felece.hybris.HybrisApp;
import com.felece.hybris.R;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @Inject
    DataManager dataManager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_main_username_edit_text)
    TextInputEditText activityMainUsernameEditText;
    @BindView(R.id.activity_main_password_edit_text)
    TextInputEditText activityMainPasswordEditText;
    @BindView(R.id.activity_main_login_button)
    MaterialButton activityMainLoginButton;
    @BindView(R.id.activity_main_register_text_view)
    TextView activityMainRegisterTextView;


    private String userName = "serkan.zaydn@gmail.com";
    private String password = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((HybrisApp) getApplication()).getActivityComponent().injectMainActivity(this);
        setSupportActionBar(toolbar);
        setTitle("Giriş");

       /* dataManager.deleteEntryFromCart(userName, "00001001", 0, new ServiceCallback<Entry>() {
            @Override
            public void onSuccess(Entry response) {

            }

            @Override
            public void onError(int code, String errorResponse) {

            }
        });*/


    }

    @OnClick({R.id.activity_main_login_button, R.id.activity_main_register_text_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_main_login_button:
                showLoading();
                dataManager.auth(null, activityMainUsernameEditText.getText().toString(), activityMainPasswordEditText.getText().toString(), new ServiceCallback<UserInformation>() {
                    @Override
                    public void onSuccess(UserInformation response) {
                        Intent intent=new Intent(MainActivity.this, ProductListActivity.class);
                        startActivity(intent);
                        hideLoading();
                    }

                    @Override
                    public void onError(int code, String errorResponse) {
                        showMessage(errorResponse);
                        hideLoading();

                    }
                });
                break;
            case R.id.activity_main_register_text_view:
                Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
