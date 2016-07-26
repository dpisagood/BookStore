package cn.iamdp.bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.iamdp.bookstore.order.dao.OrderDao;
import cn.iamdp.bookstore.order.javabean.Order;
import cn.itcast.jdbc.JdbcUtils;
import oracle.net.aso.s;

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

	/**
	 * 查询我的订单
	 * @param uid
	 * @return
	 */
	public List<Order> myOrders(String uid) {
		return orderdao.findByUid(uid);
	}

	public Order load(String oid) {
		return orderdao.load(oid);
	}

	/**
	 * 确定收货
	 * @param parameter
	 */
	public void confirm(String oid)throws OrderException {
		int state= orderdao.getStateByOid(oid);
		if(state!=3) throw new OrderException("确定订单失败！");
		orderdao.updateState(oid, 4);
	}
}
