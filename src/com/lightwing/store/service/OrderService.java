package com.lightwing.store.service;

import com.lightwing.store.domain.Order;
import com.lightwing.store.domain.User;
import com.lightwing.store.utils.PageModel;

import java.util.List;

public interface OrderService {

    void saveOrder(Order order);

    PageModel findOrdersByUidWithPage(User user, int curNum) throws Exception;

    Order findOrderByOid(String oid) throws Exception;

    void updateOrder(Order order) throws Exception;

    List<Order> findAllOrders() throws Exception;

    List<Order> findAllOrdersWithState(String state) throws Exception;
}
