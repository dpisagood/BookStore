package cn.iamdp.bookstore.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.iamdp.bookstore.user.service.UserService;
import cn.itcast.servlet.BaseServlet;

/**
 *  ��ajax����ʵ��ͼ���̳��û�ע��ʱ���û�����ʽ���Ƿ��Ѵ��ڽ���У��
 *  ��һ��:�����ύ�Ĳ��������û�������û���
 *  �ڶ������Ƚ��и�ʽУ�飬�����ʽ�Ļ����ش�����Ϣ
 *  �����������ݿ�У�飬�����������ش�����Ϣ�����ݿ��������
 *  ���ĸ����綼���򲻷�������
 *  
 */
@WebServlet("/UserCheckServlet")
public class UserCheckServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService=new UserService();
	public void checkun(HttpServletRequest request,HttpServletResponse response)
	throws ServletException ,IOException{
		String username=request.getParameter("uname");
		System.out.println(username.length());
		if(username==null||username.trim().isEmpty()){
			response.getWriter().print("1");
		}else {
			if(username.length()<3||username.length()>10){
				response.getWriter().print("2");
			}else{
				if(userService.ajaxCheck(username)){
					response.getWriter().print("3");
			}
			}
		}
	}
	
	public void checkemail(HttpServletRequest request,HttpServletResponse response)
	throws ServletException ,IOException{
		String email=request.getParameter("email");
		System.out.println(email.length());
		if(email==null||email.trim().isEmpty()){
			response.getWriter().print("1");
		}else if(!email.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")){
			response.getWriter().print("2");
		}
		}
	
}
