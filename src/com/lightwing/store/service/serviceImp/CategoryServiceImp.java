package com.lightwing.store.service.serviceImp;

import com.lightwing.store.dao.CategoryDao;
import com.lightwing.store.domain.Category;
import com.lightwing.store.service.CategoryService;
import com.lightwing.store.utils.BeanFactory;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImp implements CategoryService {
    CategoryDao CategoryDao = (CategoryDao) BeanFactory.createObject("CategoryDao");

    @Override
    public void saveCat(Category c) throws SQLException {
        CategoryDao.saveCat(c);
    }

    @Override
    public List<Category> findAllCats() throws SQLException {
        return CategoryDao.findAllCats();
    }
}