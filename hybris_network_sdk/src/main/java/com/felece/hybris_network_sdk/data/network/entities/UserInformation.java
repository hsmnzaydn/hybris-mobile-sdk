/*******************************************************************************
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 ******************************************************************************/
package com.felece.hybris_network_sdk.data.network.entities;



import com.felece.hybris_network_sdk.helper.SecurityHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Calendar;


public class UserInformation implements java.io.Serializable  {

    private String userId;
    private String cartId;
    @SerializedName("access_token")
    @Expose
    private String access_token;
    private String refresh_token;
    private String secureAccessToken;
    private String secureRefreshToken;
    private long expires_in;
    private long issuedOn;
    private Calendar calendarTokenExpiration;
    private boolean tokenInvalid = false;


    /**
     * Return true if the token is expired
     *
     * @return true if token is expired else false
     */
    public boolean isTokenExpired() {
        if (calendarTokenExpiration == null) {
            calendarTokenExpiration = Calendar.getInstance();
            calendarTokenExpiration.setTimeInMillis(issuedOn + expires_in);
        }

        return calendarTokenExpiration.before(Calendar.getInstance());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getSecureAccessToken() {
        return secureAccessToken;
    }

    public void setSecureAccessToken(String secureAccessToken) {
        this.secureAccessToken = secureAccessToken;
    }

    public String getSecureRefreshToken() {
        return secureRefreshToken;
    }

    public void setSecureRefreshToken(String secureRefreshToken) {
        this.secureRefreshToken = secureRefreshToken;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public long getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(long issuedOn) {
        this.issuedOn = issuedOn;
    }

    public Calendar getCalendarTokenExpiration() {
        return calendarTokenExpiration;
    }

    public void setCalendarTokenExpiration(Calendar calendarTokenExpiration) {
        this.calendarTokenExpiration = calendarTokenExpiration;
    }

    public boolean isTokenInvalid() {
        return tokenInvalid;
    }

    public void setTokenInvalid(boolean tokenInvalid) {
        this.tokenInvalid = tokenInvalid;
    }
}