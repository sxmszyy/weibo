<%@page import="weibosql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
    // 接收参数
    // phone
    String phone = request.getParameter("phone");
    //String phone = "13588772975";
    // word
    String word = request.getParameter("word");
 	// time
    String time = request.getParameter("time");
    // 执行　
    WeiboBean bean = new WeiboBean();
    if (bean.addWeibo(phone, word, time))
    {
    	out.println("1");//成功返回1
    }
    else
    {
    	out.println("0");//失败返回0
    }
   
%>