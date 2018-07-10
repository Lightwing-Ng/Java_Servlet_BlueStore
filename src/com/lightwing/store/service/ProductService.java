package com.lightwing.store.service;

import com.lightwing.store.domain.Product;
import com.lightwing.store.utils.PageModel;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Product> findNewProducts() throws SQLException;

    List<Product> findHotProducts() throws SQLException;

    Product findProductByPid(String pid) throws SQLException;

    PageModel findProductsWithCidAndPage(String cid, int curNum) throws SQLException;

    void saveProduct(Product product) throws SQLException;
}
