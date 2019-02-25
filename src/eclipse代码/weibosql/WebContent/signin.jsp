<%@page import="weibosql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
    // 接收参数
    // phone
    String phone = request.getParameter("phone");
    String password = request.getParameter("password");
    //String phone = "13588772975";
    //String password = "123456";
    // 执行　
    UserBean bean = new UserBean();
    UserInfo info = new UserInfo(phone);
    String tphone = bean.selectPhoneByPassword(phone, password);
    if (phone.equals(tphone))
    {
    	out.println("1");//登录成功返回1
    }
    else
    {
	    out.println("0");//登录失败
    }
   
%>