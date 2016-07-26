package cn.iamdp.bookstore.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.iamdp.bookstore.book.javabean.Book;
import cn.iamdp.bookstore.order.javabean.Order;
import cn.iamdp.bookstore.order.javabean.OrderItem;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {
	private QueryRunner qr=new TxQueryRunner();
	
	
	/**
	 * 添加订单
	 * @param order
	 */
	public void addOrder(Order order){
		try {
			String sql="insert into orders values(?,?,?,?,?,?)";
			//处理util的Date转换成sql的Timestamp
			Timestamp timestamp=new Timestamp(order.getOrdertime().getTime());
			Object[] params={order.getOid(),timestamp,
								order.getTotal(),order.getState(),
								order.getOwner().getUid(),
								order.getAddress()};
			qr.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 插入订单条
	 * @param orderItemList
	 */
	public void addOrderItemList(List<OrderItem> orderItemList){
			try {
				String sql="insert into orderitem values(?,?,?,?,?)";
				Object [][] params=new Object[orderItemList.size()][];//设置一个二维数组
				for(int i=0;i<orderItemList.size();i++){
					OrderItem item=orderItemList.get(i);
					//得到一系列参数
					params[i]=new Object[] { item.getIid(),item.getCount(),
									item.getSubtotal(),item.getOrder().getOid(),
									item.getBook().getBid()
									};
				}
				qr.batch(sql, params);//执行批处理
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}

	
	/**
	 * 按uid来查询订单
	 * @param uid
	 * @return
	 */
	public List<Order> findByUid(String uid) {
		String sql="select *from orders where uid=?";
		try {
			List<Order> orderList=qr.query(sql, new BeanListHandler<Order>(Order.class), uid);
			for (Order order : orderList) {
				loadOrderItems(order);
			}
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对用户的每个订单进行处理
	 * @param order
	 * @throws SQLException
	 */
	private void loadOrderItems(Order order) throws SQLException {
		//多表查询,将一个订单条目的信息本身的信息加上订单条目中的书的信息变成一个map对象
		//每个map对象可以看做是被添加所购图书信息的订单，里面有10个键值对，是orderitem和book两个表的字段并集
		String sql="select *from orderitem i,book b where i.bid=b.bid and oid=?";
		List<Map<String,Object>> mapList=qr.query(sql, new MapListHandler(), order.getOid());
		List<OrderItem> orderItemList=toOrderItemList(mapList);
		order.setOrderItemList(orderItemList);
	}

	
	/**
	 * 把mapList中的每个Map转换成两个对象，并建立关系
	 * @param mapList
	 * @return
	 */
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		List<OrderItem> orderItemList=new ArrayList<OrderItem>();
		for (Map<String,Object>  map : mapList) {
			OrderItem item=toOrderItem(map);
			orderItemList.add(item);
		}
		return orderItemList;
	}


	/**
	 * 把一个Map转换成一个OrderItem对象,因为OrderItem对象中有Book属性
	 * @param map
	 * @return
	 */
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem=CommonUtils.toBean(map, OrderItem.class);
		Book book=CommonUtils.toBean(map, Book.class);
		orderItem.setBook(book);
		return orderItem;
	}

	/**
	 * 加载订单
	 * @return
	 */
	public Order load(String oid) {
		//通过订单oid查询出订单
		String sql="select *from orders where oid=?";
		try {
			Order order=qr.query(sql, new BeanHandler<Order>(Order.class), oid);
				loadOrderItems(order);//为这个订单加载订单条目
			return order;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	/**
	 * 查询订单状态
	 * @param oid
	 * @return
	 */
	public int getStateByOid(String oid) {
		String sql="select *from orders where oid=?";
		Order order;
		try {
			order = qr.query(sql, new BeanHandler<Order>(Order.class),oid);
			int state=order.getState();
			return state;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改订单状态
	 * @param oid
	 * @param state
	 */
	public void updateState(String oid,int state) {
		String sql="update orders set state=? where oid=? ";
		try {
			qr.update(sql, state,oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
		
}

