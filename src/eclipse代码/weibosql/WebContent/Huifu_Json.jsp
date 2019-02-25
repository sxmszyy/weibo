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
	String weibonum = request.getParameter("weibonum");
	String Pinglunid = request.getParameter("Pinglunid");

//	String weibonum = "123654789";
	PinglunBean bean = new PinglunBean();
	List< PinglunInfo> data = bean.getHuifu(weibonum, Pinglunid);
	int i = 0;
	
	//封装成JSON
	out.println("[");
	Iterator< PinglunInfo > iter = data.iterator();
	while( iter.hasNext() ){
		PinglunInfo info = iter.next();
	 out.println("{");
	 out.println("\"pinglunid\":\"" + info.getPinglunid() + "\",");
	 out.println("\"head\":\"" + addr + info.getHead() + "\",");
	 out.println("\"name\":\"" + info.getName() + "\",");
	 out.println("\"pinglunword\":\"" + info.getPinglunWords() + "\",");
	 out.println("\"pingluntime\":\"" + info.getPinglunTime() + "\",");
	 out.println("\"level\":\"" + info.getLevel() + "\"");
	 out.println("}");
	 ++i;
	 if( i < data.size() ){
		 out.println(",");
	 }
//		System.out.printf( "%s\n", info.toString()  );
	}
	out.println("]");
%>