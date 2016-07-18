package cn.iamdp.bookstore.cart.javabean;

import java.math.BigDecimal;

import cn.iamdp.bookstore.book.javabean.Book;

/**
 * 购物车条目，购物车里存的是条目，条目=商品+数量,商品唯一，数量可以加
 * 购物车可以是一个Map，里面key为商品，value为条目
 * @author wl
 *
 */
public class CartItem {
//	public CartItem(Book book, int count) {
//		this.book = book;
//		this.count = count;
//	}
	private Book book;//书
	private int count;//数量
	
	public double getSubtotal(){//小计,处理了二进制运算运算误差
		BigDecimal d1=new BigDecimal(book.getPrice()+"");
		BigDecimal d2=new BigDecimal(count+"");
		return d1.multiply(d2).doubleValue();
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
