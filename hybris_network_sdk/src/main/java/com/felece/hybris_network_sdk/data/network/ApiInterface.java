package com.felece.hybris_network_sdk.data.network;




import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;


import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {



    @GET("countries")
    Single<Object> getCountries(@Query("type") String type,
                                @Query("fields") String fields);

    @GET("countries/{countyIsoCode}/regions")
    Single<Object> getCountryRegions(@Path("countyIsoCode") String isoCode,@Query("fields") String field);

    @GET("catalogs")
    Single<Object> getCatalogs(@Query("fields") String fields);

    @GET("catalogs/{catalogId}")
    Single<Object> getCatalog(@Path("catalogId") String catalogId,@Query("fields") String fields);

    @GET("catalogs/{catalogId}/{catalogVersionId}")
    Single<Object> getInformationOfCatalogId(@Path("catalogId") String catalogId,@Path("catalogVersionId") String catalogVersionId,
                                             @Query("fields") String fields);

    @GET("catalogs/{catalogId}/{catalogVersionId}/categories/{categoryId}")
    Single<Object> getInformationCategoryOfCatalogVersion(@Path("catalogId") String catalogId,@Path("catalogVersionId") String catalogVersionId,
                                                          @Path("categoryId") String categoryId,
                                                          @Query("fields") String fields);
}
