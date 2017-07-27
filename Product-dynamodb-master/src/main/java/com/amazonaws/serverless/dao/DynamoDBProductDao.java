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


package com.amazonaws.serverless.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.log4j.Logger;
import com.amazonaws.serverless.domain.Product;
import com.amazonaws.serverless.manager.DynamoDBManager;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;


public class DynamoDBProductDao implements ProductDao {

    private static final Logger log = Logger.getLogger(DynamoDBProductDao.class);

    private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

    private static volatile DynamoDBProductDao instance;


    private DynamoDBProductDao() { }

    public static DynamoDBProductDao instance() {

        if (instance == null) {
            synchronized(DynamoDBProductDao.class) {
                if (instance == null)
                    instance = new DynamoDBProductDao();
            }
        }
        return instance;
    }

    @Override
    public List<Product> findAllProducts() {
        return mapper.scan(Product.class, new DynamoDBScanExpression());
    }

    @Override
    public List<Product> findProductsBysku(String sku) {

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":v1", new AttributeValue().withS(sku));

        // NOTE:  without an index, this query would require a full table scan with a filter:
       
         DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                                                    .withFilterExpression("sku = :val1")
                                                    .withExpressionAttributeValues(eav);

         return mapper.scan(Product.class, scanExpression);
       
    }

    

    @Override
    public void saveOrUpdateProduct(Product product) {

        mapper.save(product);
    }

    @Override
    public void deleteProduct(String sku) {

        Optional<Product> oproduct = null;
        if (oproduct.isPresent()) {
            mapper.delete(oproduct.get());
        }
        else {
            log.error("Could not delete event, no such team and date combination");
            throw new IllegalArgumentException("Delete failed for nonexistent event");
        }
    }
}
