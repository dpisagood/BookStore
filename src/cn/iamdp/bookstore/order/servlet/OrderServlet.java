package cn.iamdp.bookstore.order.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.iamdp.bookstore.cart.javabean.Cart;
import cn.iamdp.bookstore.cart.javabean.CartItem;
import cn.iamdp.bookstore.order.javabean.Order;
import cn.iamdp.bookstore.order.javabean.OrderItem;
import cn.iamdp.bookstore.order.service.OrderService;
import cn.iamdp.bookstore.user.javabean.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderservice=new OrderService();
    /**
     * 根据购物车里的部分信息生成订单
     * @param request
     * @param response
     * @return 跳转
     * @throws ServletException
     * @throws IOException
     */
	public  String  add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart =(Cart) request.getSession().getAttribute("cart");//从session域中得到购物车
		Order order=new Order();//新建一个订单
		//给订单赋值
		order.setOid(CommonUtils.uuid());//生成订单编号
		order.setOrdertime(new Date());//下单时间
		order.setState(1);//订单状态
		User user=(User) request.getSession().getAttribute("session_user");
		order.setOwner(user);//设置订单所有者
		order.setTotal(cart.getTotal());//得到合计
		//根据购物车中的条目集合生成，创建订单条目集合，并添加到当前订单
		List<OrderItem> orderItemList=new ArrayList<OrderItem>();
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem oi=new OrderItem();//创建订单条目
			
			oi.setIid(CommonUtils.uuid());//设置订单条目的id
			oi.setCount(cartItem.getCount());//设置条目图书的数量
			oi.setBook(cartItem.getBook());//设置条目的图书
			oi.setSubtotal(cartItem.getSubtotal());//设置条目小计
			oi.setOrder(order);//设置所属订单
			
			orderItemList.add(oi);//把订单条目添加到集合中
		}
		//把所有的订单条目添加到订单中
		order.setOrderItemList(orderItemList);
		cart.clear();//清空购物车
		
		
		
		//调用orderService添加订单
		orderservice.add(order);
		request.setAttribute("order", order);
		return "f:/jsps/order/desc.jsp";
	}

}
