/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 2-Sep-2015 12:47:23 PM
 * ----------------------------------------------------------------
 *
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.felece.hybris_network_sdk.data.network.entities.user;

import java.util.List;

public class MemberList  implements java.io.Serializable 
{

	/** <i>Generated property</i> for <code>MemberList.members</code> property defined at extension <code>commercewebservicescommons</code>. */
	private List<Principal> members;
		
	public MemberList()
	{
		// default constructor
	}
	
		
	public void setMembers(final List<Principal> members)
	{
		this.members = members;
	}
	
		
	public List<Principal> getMembers() 
	{
		return members;
	}
		
	
}