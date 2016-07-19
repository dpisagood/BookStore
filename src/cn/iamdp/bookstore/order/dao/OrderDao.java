package cn.iamdp.bookstore.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import cn.iamdp.bookstore.order.javabean.Order;
import cn.iamdp.bookstore.order.javabean.OrderItem;
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
		
	}

