package cn.iamdp.bookstore.category.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.iamdp.bookstore.category.javabean.Category;
import cn.itcast.jdbc.TxQueryRunner;

public class CategoryDao {
	private QueryRunner qr=new TxQueryRunner();
	
	
	public List<Category> findAll(){
		String sql="select *from category";
		try {
			return  qr.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
