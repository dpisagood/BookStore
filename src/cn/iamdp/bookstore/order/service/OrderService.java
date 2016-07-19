package cn.iamdp.bookstore.order.service;

import java.sql.SQLException;

import cn.iamdp.bookstore.order.dao.OrderDao;
import cn.iamdp.bookstore.order.javabean.Order;
import cn.itcast.jdbc.JdbcUtils;

public class OrderService {
	OrderDao orderdao=new OrderDao();
	
	/**
	 * 添加订单
	 * 需要处理事务
	 * @param order
	 */
	public void add(Order order){
		try {
			JdbcUtils.beginTransaction();//开启事务
			//将这两个操作捆绑成一个事务进行处理，因为他们又相互依赖的关系
			orderdao.addOrder(order);//插入订单
			orderdao.addOrderItemList(order.getOrderItemList());//插入订单中的所有条目
			JdbcUtils.commitTransaction();//提交事务
		} catch (Exception e) {
			try {
				JdbcUtils.rollbackTransaction();//回滚事务
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
