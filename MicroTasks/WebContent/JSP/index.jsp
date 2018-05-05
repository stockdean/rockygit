<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Micro Task Platform</title>
<script>
function validateForm()
{
  var gid = document.forms["loginForm"]["GIDField"].value;
  var pw = document.forms["loginForm"]["PWField"].value;
  var text, result = true;
  
  if (gid == null || gid == ""){
      text = "ID can't be empty";
      result = false;
  }
  else if(pw == null || pw == ""){
	  text = "The password can't be empty";
	  result = false;
  }
  else
	text = "";
  document.getElementById("checkMeg").innerHTML = text;
  return result;
}
</script>
<script src="js/checker.js" type="text/javascript"></script>
</head>

<body>
	<center>
		<h1>欢迎来到微译系统</h1>
		<form name="loginForm" action="/MicroTasks/LoginController" method="post" onsubmit="return validateForm()">
			<table>
				<tr>
					<td>用户ID：</td>
					<td><input type="text" name="GIDField" id="GIDField" size="20" onblur="Checker.checkNode(this)">						
					</td>
					<td><div class="warning" id="GIDFieldCheckDiv"></div></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="PWField" size="20"></td>
				</tr>
				<tr>
					<td></td><td><input type="submit" value="登录" name="Submit" align="middle">	<button type="button" onclick="window.location.href='JSP/signup.jsp'">注册新用户</button></td>
				</tr>
			</table>
		</form>
		
		<p style="color:red;">
			<%
    			String message = (String)request.getAttribute("message");
    			if("LOGIN_FAIL".equals(message)){
        			out.print("Incorrect login or password! Please type again！");
    			}
			%>
		</p>
		<p id="checkMeg" style="color:blue;"></p>
		
		<br>

	</center>
</body>
</html>