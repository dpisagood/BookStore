package cn.iamdp.bookstore.book.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.iamdp.bookstore.book.service.BookService;
import cn.itcast.servlet.BaseServlet;

@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookservice=new BookService();
	
	/**
	 * 查询所有图书
	 * @param request
	 * @param response
	 * @return
	 */
	public String findAllBook(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("booklist", bookservice.findAll());
		return "f:/jsps/book/list.jsp";
	}
	
	/**
	 * 分类查询
	 * @param request
	 * @param response
	 * @return
	 */
	public String findByCategory(HttpServletRequest request, HttpServletResponse response){
		String cid=request.getParameter("cid");
		request.setAttribute("booklist", bookservice.findBookByCategory(cid));
		return "f:/jsps/book/list.jsp";
	}
	
	/**
	 * 加载方法
	 * @param request
	 * @param response
	 * @return
	 */
	public String load(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("book", bookservice.load(request.getParameter("bid")));
		return "f:/jsps/book/desc.jsp";
	}
	
}
