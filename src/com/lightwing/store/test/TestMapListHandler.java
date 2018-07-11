package com.lightwing.store.test;

import com.lightwing.store.domain.OrderItem;
import com.lightwing.store.domain.Product;
import com.lightwing.store.utils.JDBCUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMapListHandler {
    @Test
    public void testForeachMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("111", "aaaa");
        map.put("222", "bbbb");
        map.put("333", "ccccc");

        // 增强的for循环遍历map
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "   " + entry.getValue());
        }
    }

    @Test
    public void test00() throws Exception {
        List<OrderItem> list01 = new ArrayList<OrderItem>();
        String sql = "SELECT * FROM orderitem o, product p WHERE o.pid=p.pid AND oid='090330CE64CC4083AB8426502CCD5D74'";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        // 由于返回的数据是来自多个表,多行数据 MapListHandler封装返回的数据
        // 返回结果:List,list中的每个元素是Map
        List<Map<String, Object>> list = qr.query(sql, new MapListHandler());
        // 遍历List
        for (Map<String, Object> map : list) {
            OrderItem orderItem = new OrderItem();
            Product product = new Product();

            // 由于BeanUtils将字符串"1992-3-3"向user对象的setBithday();方法传递参数有问题,手动向BeanUtils注册一个时间类型转换器
            // 1_创建时间类型的转换器
            DateConverter dt = new DateConverter();
            // 2_设置转换的格式
            dt.setPattern("yyyy-MM-dd");
            // 3_注册转换器
            ConvertUtils.register(dt, java.util.Date.class);

            // 将map中属于orderItem的数据自动填充到orderItem对象上
            BeanUtils.populate(orderItem, map);
            // 将map中属于product的数据自动填充到product对象上
            BeanUtils.populate(product, map);

            // 让orderItem和product发生关联关系
            orderItem.setProduct(product);
            list01.add(orderItem);
        }
    }
}
