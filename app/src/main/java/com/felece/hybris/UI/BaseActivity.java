package com.felece.hybris.UI;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.felece.hybris.DialogCallback;
import com.felece.hybris.R;
import com.felece.hybris.Utility.CommonUtils;

import java.util.List;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private AlertDialog.Builder alertDialogBuilder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


    }

    public void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void showLoading() {
        progressDialog = CommonUtils.showLoadingDialog(BaseActivity.this);
    }

    public void hideLoading() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();

            }
        }
    }

    public void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE

                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition( R.anim.slide_from_left, R.anim.slide_to_right);

    }

    public void showListDialog(List<String> itemList, String title, ListSelectItem<Integer> listSelectItem) {
        if(itemList != null){
            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(BaseActivity.this);
            builder.setTitle(title);


            String[] stockArr = new String[itemList.size()];
            stockArr = itemList.toArray(stockArr);



            builder.setItems(stockArr, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    listSelectItem.selectedItem(which);
                }
            });

            androidx.appcompat.app.AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    public void showDialogWithChoose(String title, String description, String possitiveButtonText, String negativeButtonText, final DialogCallback dialogCallback) {
        alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(title).setMessage(description).setPositiveButton(possitiveButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialogCallback.pressedPossitiveButton();
            }
        }).setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialogCallback.pressedNegativeButton();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
        alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.black));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));

            }
        });

        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
    }
}
