<%@page import="weibosql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
    // 接收参数
    String phone = request.getParameter("phone");
    String password = request.getParameter("password");
    String name = request.getParameter("name");
    // 执行　
    UserBean bean = new UserBean();
    UserInfo info = new UserInfo(phone, password, name);
    if (bean.updateUser(info))
    {
    	out.println("1");//成功返回1
    }
    else
    {
	    out.println("0");//失败
    }
   
%>