package com.lightwing.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lightwing.store.domain.Category;
import com.lightwing.store.service.serviceImp.CategoryServiceImp;
import com.lightwing.store.utils.JedisUtils;
import com.lightwing.store.utils.UUIDUtils;
import com.lightwing.store.web.base.BaseServlet;
import redis.clients.jedis.Jedis;

public class AdminCategoryServlet extends BaseServlet {

    com.lightwing.store.service.CategoryService CategoryService = new CategoryServiceImp();

    public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 调用service查询全部分类信息,
        List<Category> allCats = CategoryService.findAllCats();
        // 放入request
        request.setAttribute("allCats", allCats);
        // 转发到admin/category/list.jsp
        return "/admin/category/list.jsp";
    }

    // addCatUI
    public String addCatUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/admin/category/add.jsp";
    }

    // addCat
    public String addCat(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cname = request.getParameter("cname");
        Category c = new Category();
        c.setCid(UUIDUtils.getId());
        c.setCname(cname);
        CategoryService.saveCat(c);

        // 跟新缓存
        Jedis jedis = JedisUtils.getJedis();
        jedis.del("allCats");
        JedisUtils.closeJedis(jedis);

        response.sendRedirect(request.getContextPath() + "/AdminCategoryServlet?method=findAllCats");
        return null;
    }
}



