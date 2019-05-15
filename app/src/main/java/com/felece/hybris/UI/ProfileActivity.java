package com.felece.hybris.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import com.felece.hybris.HybrisApp;
import com.felece.hybris.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_profile_addres_linear_layout)
    LinearLayout activityProfileAddresLinearLayout;
    @BindView(R.id.activity_profile_profile_linear_layout)
    LinearLayout activityProfileProfileLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        ((HybrisApp) getApplication()).getActivityComponent().injectProfileActivity(this);
        setSupportActionBar(toolbar);
        setTitle("Profilim");

    }

    @OnClick({R.id.activity_profile_addres_linear_layout, R.id.activity_profile_profile_linear_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_profile_addres_linear_layout:
                Intent intent=new Intent(ProfileActivity.this,AddresActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_profile_profile_linear_layout:
                Intent intent1=new Intent(ProfileActivity.this,UserProfileActivity.class);
                startActivity(intent1);
                break;
            case R.id.activity_profile_history_linear_layout:

                break;

        }
    }
}
