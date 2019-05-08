package com.felece.hybris.UI;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.felece.hybris.HybrisApp;
import com.felece.hybris.R;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.enums.FIELDS;
import com.felece.hybris_network_sdk.data.network.entities.user.User;
import com.felece.hybris_network_sdk.data.network.entities.user.UserSignUp;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_register_name_edit_text)
    TextInputEditText activityRegisterNameEditText;
    @BindView(R.id.activity_register_surname_edit_text)
    TextInputEditText activityRegisterSurnameEditText;
    @BindView(R.id.activity_register_email_edit_text)
    TextInputEditText activityRegisterEmailEditText;
    @BindView(R.id.activity_register_password_edit_text)
    TextInputEditText activityRegisterPasswordEditText;
    @BindView(R.id.activity_register_register_button)
    MaterialButton activityRegisterRegisterButton;

    UserSignUp userSignUp;
    @Inject
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        userSignUp=new UserSignUp();
        ((HybrisApp) getApplication()).getActivityComponent().injectRegisterActivity(this);

        setSupportActionBar(toolbar);
        setTitle("Kayıt Ol");
    }

    @OnClick(R.id.activity_register_register_button)
    public void onViewClicked() {
        userSignUp.setTitleCode("ms");
        userSignUp.setPassword(activityRegisterPasswordEditText.getText().toString());
        userSignUp.setUid(activityRegisterEmailEditText.getText().toString());
        userSignUp.setFirstName(activityRegisterNameEditText.getText().toString());
        userSignUp.setLastName(activityRegisterSurnameEditText.getText().toString());

        dataManager.register(null, FIELDS.DEFAULT.getFieldType(), userSignUp, new ServiceCallback<User>() {
            @Override
            public void onSuccess(User response) {
                onBackPressed();
            }

            @Override
            public void onError(int code, String errorResponse) {
                Toast.makeText(RegisterActivity.this,errorResponse,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
