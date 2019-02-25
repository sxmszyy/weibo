<%@page import="weibosql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
    // 接收参数
    // phone
    String phone = request.getParameter("phone");
    //String phone = "13588772975";
    // 执行　
    UserBean bean = new UserBean();
    UserInfo info = new UserInfo(phone);
    String tphone = bean.selectPhone(info);
    if (phone.equals(tphone))
    {
    	out.println("2");//已注册返回2
    }
    else
    {
    	boolean result = bean.add(info);
    	System.out.println("result = " + result);
	 	// 返回执行结果
	    if (result)
	    {
	    	GuanzhuBean guanzhubean = new GuanzhuBean();
	        GuanzhuInfo guanzhuinfo = new GuanzhuInfo(phone, phone);
	        guanzhubean.addGuanzhu(guanzhuinfo);
	    	out.println("1");//注册成功返回2
	    }
	    else
	    {
	    	out.println("0");//注册失败
	    }
    }
   
%>