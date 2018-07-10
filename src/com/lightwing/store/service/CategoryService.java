package com.lightwing.store.service;

import java.sql.SQLException;
import java.util.List;

import com.lightwing.store.domain.Category;

public interface CategoryService {
    List<Category> findAllCats() throws SQLException;

    void saveCat(Category c) throws SQLException;
}
