package cn.iamdp.bookstore.order.javabean;

import java.util.Date;
import java.util.List;

import cn.iamdp.bookstore.user.javabean.User;

/**
 * 订单类
 * @author wl
 *
 */
public class Order {

	private String oid;//订单id
	private Date ordertime;//下单时间
	private double total;//合计，和购物车有关
	private int state;//订单状态：1.未付款，2.已经支付，还没有发货  3.已发货但未确定收货   4.已确定交易成功
	private String address;
	private User owner;//下单的用户是当前用户
	private List<OrderItem> orderItemList;//当前订单下所有订单条目
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
}
