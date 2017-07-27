// Copyright 2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License"). You may
// not use this file except in compliance with the License. A copy of the
// License is located at
//
//	  http://aws.amazon.com/apache2.0/
//
// or in the "license" file accompanying this file. This file is distributed
// on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
// express or implied. See the License for the specific language governing
// permissions and limitations under the License.

package com.amazonaws.serverless.domain;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import java.io.Serializable;


@DynamoDBTable(tableName = "product")
public class Product implements Serializable {

	@DynamoDBAttribute
	private String sku;
	
	@DynamoDBAttribute
	private String name;
	
	@DynamoDBAttribute
	private Long price;
	
	@DynamoDBAttribute
	private String longdescription;
	
	
	@DynamoDBAttribute
	private String shortdescription;


	public String getSku() {
		return sku;
	}


	public void setSku(String sku) {
		this.sku = sku;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getPrice() {
		return price;
	}


	public void setPrice(Long price) {
		this.price = price;
	}


	public String getLongdescription() {
		return longdescription;
	}


	public void setLongdescription(String longdescription) {
		this.longdescription = longdescription;
	}


	public String getShortdescription() {
		return shortdescription;
	}


	public void setShortdescription(String shortdescription) {
		this.shortdescription = shortdescription;
	}


	public Product(String sku, String name, Long price, String longdescription,
			String shortdescription) {
		super();
		this.sku = sku;
		this.name = name;
		this.price = price;
		this.longdescription = longdescription;
		this.shortdescription = shortdescription;
	}


	public Product() {
		super();
	}


	public Product(String sku) {
		super();
		this.sku = sku;
	}
	
	

}
