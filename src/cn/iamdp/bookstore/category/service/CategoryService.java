package cn.iamdp.bookstore.category.service;

import java.util.List;

import cn.iamdp.bookstore.category.dao.CategoryDao;
import cn.iamdp.bookstore.category.javabean.Category;

public class CategoryService {
	private  CategoryDao categoryDao=new CategoryDao();
	
	/**
	 * 查询所有分类
	 * @return 返回对象结果
	 */
	public List<Category> findAll(){
		return categoryDao.findAll();
	}
}
