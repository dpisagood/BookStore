package cn.iamdp.bookstore.category.servlet;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.iamdp.bookstore.category.service.CategoryService;
import cn.itcast.servlet.BaseServlet;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	CategoryService categoryService=new CategoryService();
	
	
	/**
	 * 查询所有分类
	 * @param request
	 * @param response
	 * @return 跳转到left.jsp显示 
	 */
	public String findCategory(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("categorylist", categoryService.findAll());
 		return "f:/jsps/left.jsp";
		
	}
	
}
