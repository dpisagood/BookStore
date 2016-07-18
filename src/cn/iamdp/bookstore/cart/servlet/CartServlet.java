package cn.iamdp.bookstore.cart.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.iamdp.bookstore.book.javabean.Book;
import cn.iamdp.bookstore.book.service.BookService;
import cn.iamdp.bookstore.cart.javabean.Cart;
import cn.iamdp.bookstore.cart.javabean.CartItem;
import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
       
	/**
	 * 将商品添加到购物车中
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public  String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart=(Cart) request.getSession().getAttribute("cart");//先把登录时系统给的cart拿过来
		String bid=request.getParameter("bid");//添加商品时会将商品的ID传过来
		Book book= new BookService().load(bid);//通过id找到book
		int count=Integer.parseInt(request.getParameter("count"));
		CartItem cartItem=new CartItem();//实例化一个条目，条目里有你选的书和数量
		cartItem.setBook(book);
		cartItem.setCount(count);
		cart.add(cartItem);//将书籍添加到cart类中的map中
		return "f:/jsps/cart/list.jsp";
	}

	/**
	 * 清空购物车
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public  String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		cart.clear();
		return "f:/jsps/cart/list.jsp";
	}

	
	public  String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		String bid=request.getParameter("bid");
		cart.delete(bid);
		return "f:/jsps/cart/list.jsp";
	}
}
