package cn.iamdp.bookstore.user.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.iamdp.bookstore.user.javabean.User;
import cn.iamdp.bookstore.user.service.UserException;
import cn.iamdp.bookstore.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;

/**
 * User������
 * @author wl
 *
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService=new UserService();
	
	
	/**
	 * ��������õ��û�ͨ���������Ӵ������ļ�����������ҵ���ļ����
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String active(HttpServletRequest request,HttpServletResponse response)
	throws ServletException ,IOException{
		String code=request.getParameter("code");
		try {
			userService.active(code);
			request.setAttribute("msg", "恭喜激活成功");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/jsps/msg.jsp";
	}
     
	
	/**
	 * ע�Ṧ��
	 * @param request
	 * @param response
	 * @return ����ֵ��BaseServlet��׽��������ת
	 * @throws ServletException
	 * @throws IOException
	 */
	public String regist(HttpServletRequest request,HttpServletResponse response)
	throws ServletException ,IOException{
		//��װ�����ݵ�form������
		User form=CommonUtils.toBean(request.getParameterMap(), User.class);
		//��ȫ uid code
		form.setUid(CommonUtils.uuid());
		form.setCode(CommonUtils.uuid()+CommonUtils.uuid());
		//����У�飺form��request��ת����regist.jsp
		//У��,���ж��Ƿ���ڴ�����Ϣ���������ֱ����ת��ԭҳ��
		if(this.checkForm(form).size()>0){
			request.setAttribute("errors", this.checkForm(form));
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		//����service��regist����
		try {
			userService.regist(form);
			request.setAttribute("msg", "恭喜注册成功");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		//���ʼ�
		this.sendMail(form);
		return "f:/jsps/msg.jsp";
	}
	
	
	/**
	 * ��¼����
	 * @param request
	 * @param response
	 * @return ��¼�ɹ��ض���index.jsp��ʧ����ת��login.jsp������
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request,HttpServletResponse response)
	throws ServletException ,IOException{
		//��װ�����ݵ�form������
		User form=CommonUtils.toBean(request.getParameterMap(), User.class);
		try {
			User user=userService.login(form);
			request.getSession().setAttribute("session_user", user);
			return "r:/index.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form",form );//为了回显
			return "f:/jsps/user/login.jsp";
		}
	} 
	
	/**
	 * ע��������ɱ��session
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String quit(HttpServletRequest request,HttpServletResponse response)
	throws ServletException ,IOException{
		request.getSession().invalidate();
		return "r:/index.jsp";
	}
	
	/**
	 * �����ʼ�
	 * @param form �û���
	 */
	public void sendMail(User form){
		//1.��ȡ�����ļ�����
		Properties  props=new Properties();
		try {
			props.load(this.getClass().getClassLoader().
					getResourceAsStream("email_template.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String host=props.getProperty("host");//��ȡ����������
		String uname=props.getProperty("uname");//��ȡ�û���
		String pwd=props.getProperty("pwd");//��ȡ����
		String from=props.getProperty("from");//��ȡ������
		String to=form.getEmail();//��ȡ�ռ���
		String subject=props.getProperty("subject");//��ȡ����
		String content=props.getProperty("content");//��ȡ�ʼ�����
		content =MessageFormat.format(content, form.getCode());//�������е�ռλ���滻��code
		
		
		Session session=MailUtils.createSession(host, uname, pwd);//�õ�session
		Mail mail=new Mail(from,to,subject,content);//�����ʼ�����

		try {
			MailUtils.send(session, mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �����ʽ����С����ʽУ��
	 * @param form
	 * @return
	 */
	public Map<String,String> checkForm(User form){
		//1.����һ��map����װ������Ϣ������keyΪ���Զ����ƣ�valueΪ������Ϣ
		Map<String,String> errors=new HashMap<String,String>();
		String username=form.getUsername();
		if(username==null||username.trim().isEmpty()){
			errors.put("username", "用户名不能 为空 1");
		}else if(username.length()<3||username.length()>10){
			errors.put("username", "请设置3-10位的用户名 1");
		}
		String password=form.getPassword();
		if(password==null||password.trim().isEmpty()){
			errors.put("password", "密码不能为空 1");
		}else if(password.length()<3||password.length()>10){
			errors.put("password", "请将密码设置3-10位数之间  1");
		}
		
		String email=form.getEmail();
		if(email==null||email.trim().isEmpty()){
			errors.put("email", "邮箱不能为空 1");
		}else if(!email.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")){
			errors.put("email", "请填写正确的邮箱格式 1");
		}
		
		return errors;
	}
}
