package com.lightwing.store.dao.daoImp;

import com.lightwing.store.dao.ProductDao;
import com.lightwing.store.domain.Product;
import com.lightwing.store.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImp implements ProductDao {
    @Override
    public void saveProduct(Product product) throws SQLException {
        String sql = "INSERT INTO product VALUES( ?,?,?,?,?,?,?,?,?,? )";
        Object[] params = {product.getPid(), product.getPname(), product.getMarket_price(), product.getShop_price(), product.getPimage(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(), product.getCid()};
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        qr.update(sql, params);
    }

    @Override
    public Product findProductByPid(String pid) throws SQLException {
        String sql = "select * from product where pid=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanHandler<Product>(Product.class), pid);

    }

    @Override
    public int findTotalNum(String cid) throws SQLException {
        String sql = "select count(*) from product where cid=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        Long num = (Long) qr.query(sql, new ScalarHandler(), cid);
        return num.intValue();
    }

    @Override
    public List<Product> findProductsWithCidAndPage(String cid, int startIndex, int pageSize) throws SQLException {
        String sql = "SELECT * FROM product WHERE cid=? LIMIT ?,?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanListHandler<Product>(Product.class), cid, startIndex, pageSize);
    }

    @Override
    public List<Product> findNewProducts() throws SQLException {
        String sql = "SELECT * FROM product WHERE pflag=0 ORDER BY pdate DESC LIMIT 0 , 9";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanListHandler<Product>(Product.class));

    }

    @Override
    public List<Product> findHotProducts() throws SQLException {
        String sql = "SELECT * FROM product WHERE pflag=0 AND is_hot= 1 ORDER BY pdate DESC LIMIT 0 , 9";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanListHandler<Product>(Product.class));
    }
}
