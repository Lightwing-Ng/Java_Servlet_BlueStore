package com.lightwing.store.dao;

import com.lightwing.store.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
    List<Category> findAllCats() throws SQLException;

    void saveCat(Category c) throws SQLException;
}
