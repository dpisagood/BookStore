package cn.iamdp.bookstore.book.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.iamdp.bookstore.book.javabean.Book;
import cn.itcast.jdbc.TxQueryRunner;

public class BookDao {
	private QueryRunner qr=new TxQueryRunner();
	
	/**
	 * ��ѯ����ͼ��
	 * @return
	 */
	public List<Book> findAll(){
		String sql="select *from book";
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * ͨ�������ѯ
	 * @param cid ����id 
	 * @return
	 */
	public List<Book> findBookByCategory(String cid) {
		String sql="select *from book where cid=?";
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class),cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ���ݲ�ѯ�����һ����
	 * @param bid
	 * @return
	 */
	public Book findByid(String bid) {
		String sql="select *from book where bid=?";
		try {
			Book book= qr.query(sql, new BeanHandler<Book>(Book.class),bid);
			return book;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
