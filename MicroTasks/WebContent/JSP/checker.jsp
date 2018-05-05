<%@ page language="java" contentType="text/plain; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.dao.GuestDA"%>
<%@page import="java.util.ArrayList"%>
<%!
public boolean checkGuestID(String guestID,String value)
{
	return GuestDA.hasSameValue(guestID, value);
}
%>
<%

	out.clear();
    request.setCharacterEncoding("utf-8");
    String name = request.getParameter("name");
	String value = request.getParameter("value");
	String info = null;
	if("GIDField".equals(name))
	{
		info = "账号";
		name="guestid";
		if(!checkGuestID(name, value))
		{
			out.print("0该"+info+"不存在，请先注册"+info+"。");
		}
		else{
			out.print("1请输入密码。");
		}
	}
	else{
		if("guestid".equals(name))
		{
			info = "账号";
		}		
		if(checkGuestID(name, value))
		{
			out.print("0该"+info+"已存在，请更换"+info+"。");
		}
		else{
			out.print("1该"+info+"可正常使用。");
		}
	}

%>