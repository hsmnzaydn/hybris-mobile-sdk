package com.felece.hybris.UI;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.felece.hybris.HybrisApp;
import com.felece.hybris.R;
import com.felece.hybris.Utility.Constant;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.enums.FIELDS;
import com.felece.hybris_network_sdk.data.network.entities.order.PaymentDetails;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPaymentDetail extends BaseActivity {


    @Inject
    DataManager dataManager;


    String cartId;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_add_payment_detail_card_number_edit_text)
    TextInputEditText activityAddPaymentDetailCardNumberEditText;
    @BindView(R.id.activity_add_payment_detail_expired_month_edit_text)
    TextInputEditText activityAddPaymentDetailExpiredMonthEditText;
    @BindView(R.id.activity_add_payment_detail_expired_year_edit_text)
    TextInputEditText activityAddPaymentDetailExpiredYearEditText;

    PaymentDetails paymentDetails;
    @BindView(R.id.activity_add_payment_detail_payment_button)
    MaterialButton activityAddPaymentDetailPaymentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_detail);
        ButterKnife.bind(this);


        ((HybrisApp) getApplication()).getActivityComponent().injectAddPaymentDetail(this);
        paymentDetails = new PaymentDetails();
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            cartId = bundle.getString(Constant.BUNDLE_CART_ID, "");
        }

    }

    @OnClick(R.id.activity_add_payment_detail_payment_button)
    public void onViewClicked() {
        paymentDetails.setCardNumber(activityAddPaymentDetailCardNumberEditText.getText().toString());
        paymentDetails.setExpiryMonth(activityAddPaymentDetailExpiredMonthEditText.getText().toString());
        paymentDetails.setExpiryYear(activityAddPaymentDetailExpiredYearEditText.getText().toString());
        showLoading();
        dataManager.addPaymentDetailToCart(null, FIELDS.BASIC.getFieldType(), cartId, paymentDetails, new ServiceCallback<PaymentDetails>() {
            @Override
            public void onSuccess(PaymentDetails response) {

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
