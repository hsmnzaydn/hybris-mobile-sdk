package com.felece.hybris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.enums.FIELDS;
import com.felece.hybris_network_sdk.data.network.entities.order.Cart;
import com.felece.hybris_network_sdk.data.network.entities.order.CartList;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.felece.hybris_network_sdk.data.network.entities.user.AddressList;
import com.felece.hybris_network_sdk.data.network.entities.user.Country;
import com.felece.hybris_network_sdk.data.network.entities.user.User;


import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {

    @Inject
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((HybrisApp) getApplication()).getActivityComponent().injectMainActivity(this);
       /* dataManager.auth(null, "serkan.zaydn@gmail.com", "123456", new ServiceCallback<UserInformation>() {
            @Override
            public void onSuccess(UserInformation response) {

            }

            @Override
            public void onError(int code, String errorResponse) {
                Log.d("ver","veri");
            }
        });
*/
/*
dataManager.getUserAdresses(null, "serkan.zaydn@gmail.com", new ServiceCallback<AddressList>() {
    @Override
    public void onSuccess(AddressList response) {
        Log.d("veri","veri");
    }

    @Override
    public void onError(int code, String errorResponse) {
        Log.d("veri","veri");
    }
});


        Address address=new Address();
        Country country=new Country();
        country.setIsocode("AF");
        address.setCountry(country);
        address.setId("8796158590999");
        address.setFirstName("Ahmet");
        address.setLastName("Mehmet");
        address.setTown("Agri");
        address.setLine1("Mehmet mah.");
        address.setLine2("Asd meee");
        address.setPostalCode("34000");
        address.setTitleCode("ms");

        dataManager.createNewUserAdress(null, "serkan.zaydn@gmail.com", address, new ServiceCallback<Address>() {
            @Override
            public void onSuccess(Address response) {
                Log.d("veri","veri");

            }

            @Override
            public void onError(int code, String errorResponse) {
                Log.d("veri","veri");

            }
        });
*/


/*
dataManager.deleteUserAdress("canonlover@hybris.com", "8796158754839", new ServiceCallback<Address>() {
    @Override
    public void onSuccess(Address response) {
        Log.d("veri","veri");
    }

    @Override
    public void onError(int code, String errorResponse) {
        Log.d("veri","veri");
    }
});
*/

/*
dataManager.getUserAdress(null, "canonlover@hybris.com", "8796158787607", new ServiceCallback<Address>() {
    @Override
    public void onSuccess(Address response) {
        Log.d("veri","veri");
    }

    @Override
    public void onError(int code, String errorResponse) {
        Log.d("veri","veri");
    }
});
*/

        /*Address address=new Address();
        Country country=new Country();
        country.setIsocode("AF");
        address.setCountry(country);
        address.setId("8796158590999");
        address.setFirstName("Ahmet");
        address.setLastName("Mehmet");
        address.setTown("Agri");
        address.setLine1("Mehmet mah.");
        address.setLine2("Asd meee");
        address.setPostalCode("34000");
        address.setTitleCode("ms");

dataManager.updateUserAddress(null, "canonlover@hybris.com", "8796158787607", address, new ServiceCallback<Address>() {
    @Override
    public void onSuccess(Address response) {

    }

    @Override
    public void onError(int code, String errorResponse) {

    }
});*/

     /*   dataManager.updateUserLoginId("serkan.zaydn@gmail.com", "kasim.sagir@hotmail.com", "123456", new ServiceCallback<UserInformation>() {
            @Override
            public void onSuccess(UserInformation response) {
        Log.d("veri","veri");
            }

            @Override
            public void onError(int code, String errorResponse) {
                Log.d("veri","veri");
            }
        });*/

    /* dataManager.updateUserPassword("123456", "HAsan1994!*", "serkan.zaydn@gmail.com", new ServiceCallback<UserInformation>() {
         @Override
         public void onSuccess(UserInformation response) {

         }

         @Override
         public void onError(int code, String errorResponse) {
             Log.d("veri","veri");

         }
     });*/

   /* Cart cart=new Cart();
    cart.setName("asdasd");

    dataManager.createOrUpdateCart(null, FIELDS.DEFAULT.getFieldType(),cart, null, null, "serkan.zaydn@gmail.com", new ServiceCallback<Cart>() {
        @Override
        public void onSuccess(Cart response) {

        }

        @Override
        public void onError(int code, String errorResponse) {

        }
    });*/

 /* dataManager.getCarts(null, FIELDS.DEFAULT.getFieldType(), null, null, null, null, "serkan.zaydn@gmail.com", new ServiceCallback<CartList>() {
      @Override
      public void onSuccess(CartList response) {

      }

      @Override
      public void onError(int code, String errorResponse) {

      }
  });*/

 dataManager.deleteCart("serkan.zaydn@gmail.com", "00000000", new ServiceCallback<Cart>() {
     @Override
     public void onSuccess(Cart response) {

     }

     @Override
     public void onError(int code, String errorResponse) {
        Log.d("veri","veri");
     }
 });
    }
}
