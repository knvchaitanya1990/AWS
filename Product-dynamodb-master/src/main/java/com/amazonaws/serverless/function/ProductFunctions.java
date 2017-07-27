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

package com.amazonaws.serverless.function;

import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.log4j.Logger;
import com.amazonaws.serverless.dao.DynamoDBProductDao;
import com.amazonaws.serverless.domain.Product;


public class ProductFunctions {

    private static final Logger log = Logger.getLogger(ProductFunctions.class);

    private static final DynamoDBProductDao productDao = DynamoDBProductDao.instance();


    public List<Product> getAllProductsHandler() {

        log.info("GetAllEvents invoked to scan table for ALL events");
        List<Product> items = productDao.findAllProducts();
        log.info("Found Products :::" + items.size() + " total Products.");
        return items;
    }
    


    public List<Product> getProductsBysku(String  sku) throws UnsupportedEncodingException {
        List<Product> item = productDao.findProductsBysku(sku);
        log.info("Found Product :::" + item);
        return item;
    }

    public void saveOrUpdateEvent(Product product) {

        if (null == product) {
            log.error("SaveProduct received null input");
            throw new IllegalArgumentException("Cannot save null object");
        }

        log.info("Saving or updating Product = " + product.toString());
        productDao.saveOrUpdateProduct(product);
        log.info("Successfully saved/updated event");
    }

    public void deleteProduct(Product product) {

        if (null == product) {
            log.error("DeleteProduct received null input");
            throw new IllegalArgumentException("Cannot delete null object");
        }

        log.info("Deleting product  with sku is ::" +product.getSku());
        productDao.deleteProduct(product.getSku());
        log.info("Successfully deleted product");
    }

}
