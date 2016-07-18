package cn.iamdp.bookstore.book.service;

import java.util.List;

import cn.iamdp.bookstore.book.dao.BookDao;
import cn.iamdp.bookstore.book.javabean.Book;

public class BookService {
	private BookDao bookdao=new BookDao();
	
	/**
	 * 查询所有图书
	 * @return
	 */
	public List<Book> findAll(){
		return bookdao.findAll();
	}

	/**
	 * 分类查询
	 * @param cid
	 * @return
	 */
	public List<Book> findBookByCategory(String cid) {
		return bookdao.findBookByCategory(cid);
	}
	
	/**
	 * 加载方法
	 * @param bid
	 * @return
	 */
	public Book load(String bid) {
		return bookdao.findByid(bid);
	}
}
