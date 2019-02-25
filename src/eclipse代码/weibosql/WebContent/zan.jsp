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
    //String phone = "13588772975";
    // 执行　
    UserWeiboBean bean = new UserWeiboBean();
    UserWeiboInfo info = new UserWeiboInfo(phone, weibonum);
    String tphone = bean.selectPhone(info);
    if (phone.equals(tphone))
    {
    	if (bean.delZan(info))
    	{
    		out.println("2");//删除成功返回2
    	}
    }
    else
    {
	    if (bean.addZan(info))
	    {
	    	out.println("1");//添加成功返回1
	    }
    }
   
%>