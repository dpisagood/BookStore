<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function createXMLHttpRequset(){//创建异步对象
			try {
				return new XMLHttpRequest();//大多数浏览器
			} catch (e) {
				try {
					return ActvieXObject("Msxml12.XMLHTTP");//IE6.0
				} catch (e) {
					try {
						return ActvieXObject("Microsoft.XMLHTTP");//IE5.5及更早
					} catch (e) {
						alert("哥们，你用的是什么浏览器啊！");
						throw e;
						
					}
				}
			}
		}
		window.onload=function(){//监听此文档是否被加载完成
			//获取文本框，给他的失去焦点事件注册监听
			var userEle=document.getElementById("un");
			var emaEle=document.getElementById("ema");
			userEle.onblur=function(){
			//1.得到异步对象
			var xmlHttp=createXMLHttpRequset();
			//2.打开链接
			xmlHttp.open("POST","<c:url value='/UserCheckServlet?method=checkun'/>",true);
			//3.设置请求头，post请求中中需要做这个操作s
			xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			//4.发送请求体
			//var method="checkun";
			xmlHttp.send("uname="+userEle.value);//得到文本框内容，将它发送到服务器
			
			//5.给xmlHttp的oreadystatechange事件注册监听
			xmlHttp.onreadystatechange=function(){
				if(xmlHttp.readyState==4 && xmlHttp.status==200){//双重判断，只关心4状态
					//获取服务器响应，判断是否为1
					//是：获取span,添加内容：“该用户名已被注册”
					var text=xmlHttp.responseText;
				//	alert(text);
					var span=document.getElementById("errorun");//得到这个标签对象
					if(text=="1"){
						span.innerHTML="用户名不能为空 2";//将内容赋值给这个标签域
					}else if(text=="2"){
						span.innerHTML="请输入3-10位的用户名 2";//将内容赋值给这个标签域
					}else if(text=="3"){
						span.innerHTML="用户名已被注册 2";//将内容赋值给这个标签域
					}else{
						span.innerHTML="";
					}
				}
			}
		};
		
		emaEle.onblur=function(){
		//1.得到异步对象
		var xmlHttp=createXMLHttpRequset();
		//2.打开链接
		xmlHttp.open("POST","<c:url value='/UserCheckServlet?method=checkemail'/>",true);
		//3.设置请求头，post请求中中需要做这个操作s
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		//4.发送请求体
		xmlHttp.send("email="+emaEle.value);//得到文本框内容，将它发送到服务器
		
		//5.给xmlHttp的oreadystatechange事件注册监听
		xmlHttp.onreadystatechange=function(){
			if(xmlHttp.readyState==4 && xmlHttp.status==200){//双重判断，只关心4状态
				var text=xmlHttp.responseText;
				var span=document.getElementById("errorema");//得到这个标签对象
				if(text=="1"){
					span.innerHTML="邮箱不能为空  2";//将内容赋值给这个标签域
				}else if(text=="2"){
					span.innerHTML="请输入正确的邮箱格式  2";//将内容赋值给这个标签域
				}else{
					span.innerHTML="";
				}
			}
		}
	};
	
};
	
	</script>

  </head>
  
  <body>
  <h1>注册</h1>
  
<p style="color: red; font-weight: 900">${msg }</p>
<form action="<c:url value='/UserServlet'/>" method="post">
	<input type="hidden" name="method" value="regist"/>
	用户名：<input type="text" name="username" value="${form.username}" id="un"/>
	<span style="color: red; font-weight:900"></span>
	<span style="color: red; font-weight:900" id="errorun">${errors.username }</span>
	<br/>
	密　码：<input type="password" name="password"  value="${form.password}"/>
	<span style="color: red; font-weight:900">${errors.password}</span>
	<br/>
	邮　箱：<input type="text" name="email" value="${form.email}" id="ema"/>
	<span style="color: red; font-weight:900" ></span>
	<span style="color: red; font-weight:900" id="errorema">${errors.email}</span>
	<br/>
	<input type="submit" value="注册"/>
</form>
  </body>
</html>
