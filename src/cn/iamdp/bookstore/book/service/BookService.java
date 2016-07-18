package cn.iamdp.bookstore.book.service;

import java.util.List;

import cn.iamdp.bookstore.book.dao.BookDao;
import cn.iamdp.bookstore.book.javabean.Book;

public class BookService {
	private BookDao bookdao=new BookDao();
	
	/**
	 * ��ѯ����ͼ��
	 * @return
	 */
	public List<Book> findAll(){
		return bookdao.findAll();
	}

	/**
	 * �����ѯ
	 * @param cid
	 * @return
	 */
	public List<Book> findBookByCategory(String cid) {
		return bookdao.findBookByCategory(cid);
	}
	
	/**
	 * ���ط���
	 * @param bid
	 * @return
	 */
	public Book load(String bid) {
		return bookdao.findByid(bid);
	}
}
