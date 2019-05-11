package com.felece.hybris_network_sdk.data.network;


import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.order.Cart;
import com.felece.hybris_network_sdk.data.network.entities.order.CartList;
import com.felece.hybris_network_sdk.data.network.entities.order.OrderEntry;
import com.felece.hybris_network_sdk.data.network.entities.order.PaymentDetails;
import com.felece.hybris_network_sdk.data.network.entities.product.Product;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.felece.hybris_network_sdk.data.network.entities.user.User;
import com.felece.hybris_network_sdk.data.network.entities.user.UserSignUp;


import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {


    @GET("countries")
    Single<Object> getCountries(@Query("type") String type,
                                @Query("fields") String fields);

    @GET("countries/{countyIsoCode}/regions")
    Single<Object> getCountryRegions(@Path("countyIsoCode") String isoCode, @Query("fields") String field);

    @GET("catalogs")
    Single<Object> getCatalogs(@Query("fields") String fields);

    @GET("catalogs/{catalogId}")
    Single<Object> getCatalog(@Path("catalogId") String catalogId, @Query("fields") String fields);

    @GET("catalogs/{catalogId}/{catalogVersionId}")
    Single<Object> getInformationOfCatalogId(@Path("catalogId") String catalogId, @Path("catalogVersionId") String catalogVersionId,
                                             @Query("fields") String fields);

    @GET("catalogs/{catalogId}/{catalogVersionId}/categories/{categoryId}")
    Single<Object> getInformationCategoryOfCatalogVersion(@Path("catalogId") String catalogId, @Path("catalogVersionId") String catalogVersionId,
                                                          @Path("categoryId") String categoryId,
                                                          @Query("fields") String fields);


    // User services
    @POST
    Single<Object> authorization(@Url String url, @Query("client_id") String clientId,
                                 @Query("client_secret") String clientSecret, @Query("grant_type") String grant_type,
                                 @Query(value = "username", encoded = true) String username, @Query("password") String password);


    @POST("users")
    Single<Object> register(@Body UserSignUp user, @Query("fields") String field);

    @GET("users/{userId}")
    Single<Object> getUserProfile(@Path("userId") String userId);

    @DELETE("users/{userId}")
    Single<Object> deleteUser(@Path("userId") String userId);

    @PATCH("users/{userId}")
    Call<Void> updateProfile(@Path("userId") String userId, @Body User user);

    @GET("users/{userId}/addresses")
    Single<Object> getUserAdresses(@Path("userId") String userId,@Query("fields") String fields);

    @POST("users/{userId}/addresses")
    Single<Object> addNewAdress(@Path("userId") String userId, @Body Address address);

    @DELETE("users/{userId}/addresses/{addressId}")
    Call<Void> deleteUserAdress(@Path("userId") String userId, @Path("addressId") String adressId);

    @GET("users/{userId}/addresses/{addressId}")
    Single<Object> getUserAdress(@Path("userId") String userId, @Path("addressId") String adressId);

    @PATCH("users/{userId}/addresses/{addressId}")
    Single<Object> updateUserAdress(@Path("userId") String userId, @Path("addressId") String adressId, @Body Address address);

    @PUT("users/{userId}/login")
    Single<Response<Void>> updateUserId(@Path("userId") String userId, @Query(value = "newLogin", encoded = true) String newLoginId, @Query("password") String password);

    @PUT("users/{userId}/password")
    Single<Response<Void>> updateUserPassword(@Path("userId") String userId, @Query("old") String oldPassword, @Query("new") String newPassword);

    // Cart Services
    @GET("users/{userId}/carts")
    Single<CartList> getCartList(@Path("userId") String userId, @Query("fields") String fields,
                                 @Query("savedCartsOnly") Boolean isSavedCartsOnly, @Query("currentPage") Integer currentPage, @Query("pageSize") Integer pageSize
            , @Query("sort") String sort);

    @POST("users/{userId}/carts")
    Single<Object> createOrUpdateCart(@Path("userId") String userId, @Body Cart cart, @Query("fields") String fields, @Query("oldCartId") String oldCartId,
                                      @Query("toMergeCartGuid") String toMergeCartGuid);

    @DELETE("users/{userId}/carts/{cartId}")
    Single<Response<Void>> deleteCart(@Path("userId") String userId,@Path("cartId") String cartId);

    @GET("users/{userId}/carts/{cartId}")
    Single<Object> getCart(@Path("userId") String userId,@Path("cartId") String cartId,@Query("fields") String fields);

    @POST("users/{userId}/carts/{cartId}/entries")
    Single<Object> addEntryToCart(@Path("userId") String userId, @Path("cartId") String cartId, @Body OrderEntry product);

    @DELETE("users/{userId}/carts/{cartId}/addresses/delivery")
    Single<Response<Void>> deleteDeliveryAddressOfCart(@Path("userId") String userId,@Path("cartId") String cartId);

    @POST("users/{userId}/carts/{cartId}/addresses/delivery")
    Single<Object> addDeliveryAddressOfCart(@Path("userId") String userId,@Path("cartId") String cartId,@Body Address address);

    @PUT("users/{userId}/carts/{cartId}/addresses/delivery")
    Single<Response<Void>> setDeliveryAddressToCart(@Path("userId") String userId,@Path("cartId") String cartId,@Query("addressId") String addressId);

    @DELETE("users/{userId}/carts/{cartId}/deliverymode")
    Single<Response<Void>> deleteDeliveryModeFromCart(@Path("userId") String userId,@Path("cartId") String cartId);

    @GET("users/{userId}/carts/{cartId}/deliverymode")
    Single<Response<Void>> getDeliveryModeOfCart(@Path("userId") String userId,@Path("cartId") String cartId,@Query("fields") String field);

    @GET("users/{userId}/carts/{cartId}/deliverymodes")
    Single<Object> getDeliveryModesOfCart(@Path("userId") String userId,@Path("cartId") String cartId,@Query("fields") String field);

    @GET("users/{userId}/carts/{cartId}/entries")
    Single<Object> getEntriesOfCart(@Path("userId")String userId,@Path("cartId") String cartId,@Query("fields") String fields);

    @DELETE("users/{userId}/carts/{cartId}/entries/{entryId}")
    Single<Response<Void>> deleteEntryFromCart(@Path("userId") String userId,@Path("cartId") String cartId,@Path("entryId") Integer entryId);

    @GET("users/{userId}/carts/{cartId}/vouchers")
    Single<Object> getVouchersOfCart(@Path("userId") String userId,@Path("cartId") String cartId,@Query("fields") String fields);

    @POST("users/{userId}/carts/{cartId}/vouchers")
    Single<Response<Void>> addVoucherToCart(@Path("userId") String userId,@Path("cartId") String cartId,@Query("voucherId") String voucherId);

    @POST("users/{userId}/carts/{cartId}/paymentdetaild")
    Single<Response<Void>> addPaymentToCart(@Path("userId") String userId, @Path("cartId") String cartId, @Query("fields") String fields, @Body PaymentDetails paymentDetails);


    // Product Services

    @GET("products/search")
    Single<Object> searchProducts(@Query("query") String query,
                                  @Query("currentPage") Integer currentPage,
                                  @Query("pageSize") Integer pageSize,
                                  @Query("sort") String sort,
                                  @Query("fields") String fields,
                                  @Query("searchQueryContext") String searchQueryContext);

    @GET("products/{productId}")
    Single<Object> getProduct(@Path("productId") String productId,@Query("fields") String fields);

}
