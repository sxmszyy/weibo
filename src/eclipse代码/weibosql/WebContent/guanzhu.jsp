<%@page import="weibosql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
    // 接收参数
    // phone
    String phone = request.getParameter("phone");
 	// weibonum
    String weibonum = request.getParameter("weibonum");
 	// flag
 	String flag = request.getParameter("flag");
 	//String weibonum = "123654789";
 	//String phone = "111";
 	//String flag = "2";
    //String phone = "13588772975";
    // 执行　
    GuanzhuBean bean = new GuanzhuBean();
    String tphone = bean.selectPhone(weibonum);
    GuanzhuInfo info = new GuanzhuInfo(phone, tphone);
    if (flag.equals("2"))
    {
    	if (bean.delGuanzhu(info))
    	{
    		out.println("2");//删除成功返回2
    	}
    }
    else
    {
	    if (bean.addGuanzhu(info))
	    {
	    	out.println("1");//添加成功返回1
	    }
    }
   
%>