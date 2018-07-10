package com.lightwing.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lightwing.store.domain.Cart;
import com.lightwing.store.domain.CartItem;
import com.lightwing.store.domain.Product;
import com.lightwing.store.domain.User;
import com.lightwing.store.service.ProductService;
import com.lightwing.store.service.serviceImp.ProductServiceImp;
import com.lightwing.store.web.base.BaseServlet;

public class CartServlet extends BaseServlet {

    public String addToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 判断用户是否登录,没有登录跳转到login.jsp页面,提示:请登录后在购物
        User uu = (User) request.getSession().getAttribute("loginUser");
        if (null == uu) {
            request.setAttribute("msg", "请登录后在购买商品");
            return "/jsp/login.jsp";
        }

        // 如果登录,服务端获取到pid,数量,创建好CartItem
        String pid = request.getParameter("pid");
        int num = Integer.parseInt(request.getParameter("num"));
        ProductService ProductService = new ProductServiceImp();
        Product product = ProductService.findProductByPid(pid);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setNum(num);

        // 获取购物车:从session中获取,获取到直接使用,
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 如果获取不到,创建一份,并且在session在绑定一份
        if (null == cart) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        // 调用购物车上的添加商品到购物车功能
        cart.addCart(cartItem);
        response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
        return null;
    }

    // clearCart
    public String clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.clearCart();
        response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
        return null;
    }

    // delCartItem
    public String delCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取到被删除商品pid
        String pid = request.getParameter("pid");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.delCart(pid);
        response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
        return null;
    }
}