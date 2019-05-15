package com.felece.hybris.UI;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.felece.hybris.HybrisApp;
import com.felece.hybris.R;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.user.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserProfileActivity extends BaseActivity {


    @Inject
    DataManager dataManager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_user_profile_name_edit_text)
    TextInputEditText activityUserProfileNameEditText;
    @BindView(R.id.activity_user_profile_surname_edit_text)
    TextInputEditText activityUserProfileSurnameEditText;
    @BindView(R.id.activity_user_profile_save_button)
    MaterialButton activityUserProfileSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);


        ((HybrisApp) getApplication()).getActivityComponent().injectUserProfileActivity(this);
        setSupportActionBar(toolbar);
        getUserProfile();

    }

    private void getUserProfile() {

        showLoading();
        dataManager.getUserProfile(null, new ServiceCallback<User>() {
            @Override
            public void onSuccess(User response) {
                setTitle(response.getName());
                activityUserProfileNameEditText.setText(response.getFirstName());
                activityUserProfileSurnameEditText.setText(response.getLastName());
                hideLoading();
            }

            @Override
            public void onError(int code, String errorResponse) {
                showMessage(errorResponse);
                hideLoading();
            }
        });
    }

    @OnClick(R.id.activity_user_profile_save_button)
    public void onViewClicked() {
        User user=new User();

        user.setFirstName(activityUserProfileNameEditText.getText().toString());
        user.setLastName(activityUserProfileSurnameEditText.getText().toString());

        showLoading();

        dataManager.updateProfile(null, user, new ServiceCallback<User>() {
            @Override
            public void onSuccess(User response) {

                onBackPressed();
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
