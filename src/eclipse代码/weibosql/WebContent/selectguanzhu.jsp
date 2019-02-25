<%@page import="weibosql.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	// 接收参数
	String addr = "image/";
	// phone
	String phone = request.getParameter("phone");
	WeiboBean bean = new WeiboBean();
	List< WeiboInfo> data = bean.getGuanzhuWeibo(phone);
	int i = 0;
	
	//封装成JSON
	out.println("[");
	Iterator< WeiboInfo > iter = data.iterator();
	while( iter.hasNext() ){
		WeiboInfo info = iter.next();
	 out.println("{");
	 out.println("\"weibonum\":\"" + info.getWeibonum() + "\",");
	 out.println("\"head\":\"" + addr + info.getHead() + "\",");
	 out.println("\"name\":\"" + info.getName() + "\",");
	 out.println("\"time\":\"" + info.getTime() + "\",");
	 out.println("\"introduce\":\"" + info.getIntroduce() + "\",");
	 out.println("\"follow\":\"" + info.getFollow() + "\",");
	 out.println("\"word\":\"" + info.getWord() + "\",");
	 out.println("\"image\":\"" + info.getImage() + "\",");
	 out.println("\"label\":\"" + info.getLabel() + "\",");
	 out.println("\"zhuan\":\"" + info.getZhuan() + "\",");
	 out.println("\"zan\":\"" + info.getZan() + "\",");
	 out.println("\"ping\":\"" + info.getPing() + "\",");
	 out.println("\"iszan\":\"" + info.getIszan() + "\"");
	 out.println("}");
	 ++i;
	 if( i < data.size() ){
		 out.println(",");
	 }
		System.out.printf( "%s\n", info.toString()  );
	}
	out.println("]");

%>