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
    String pinglunword = request.getParameter("pinglunword");
 	// time
    String pingluntime = request.getParameter("pingluntime");
 	
    String weibonum = request.getParameter("weibonum");
    
    String level = request.getParameter("level");
 	
    // 执行　
    PinglunBean bean = new PinglunBean();
    if (bean.addPinglun(weibonum, phone, pinglunword, pingluntime, level)){
    	out.println("1");//成功返回1
    }
    else
    {
    	out.println("0");//失败返回0
    }
   
%>