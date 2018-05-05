<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>         
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset = UTF-8">
<title>注册页</title>
<script type="text/javascript" src="../js/checker.js"></script>
<script>
   function ding(x)
   {
	   var divA = document.getElementById("guestidCheckDiv");
	   var divB = document.getElementbyId("passwordCheckDiv");
	   System.out.println("lalala");
 	   if(divA.className="warning"||divB.className=="warning")
	{
		   alert("页面存在输入错误，请检查");
		   return false;
    }		   	   
		   return true;
   }
</script>
</head>

<body>
	<form action="/MicroTasks/SignupController" method ="post" enctype="multipart/form-data" id="formdata" > 
		<table>
			<tr>
				<td>账号:</td>
				<td>
					<input type="text" name="guestid" required="required" size="10" id="guestid" onblur="Checker.checkNode(this)">
				</td>
				<td>
					<div id="guestidCheckDiv" class="warning"></div>
				</td>
			</tr>
			<tr>
				<td>昵称:</td>
				<td>
					<input type="text" name="guestName" required="required" size="10">
				</td>
			</tr>
			
			<tr>
				<td>密码:</td>
				<td>
					<input type="password" name="password" required="required" size="10" id="password" onBlur="Checker.checkPassword()">
				</td>
				<td>
					<div id="passwordCheckDiv" class="warning"></div>
				</td>
			</tr>
			
			<tr>
				<td>确认密码:</td>
				<td>
					<input type="password" name="password2" required="required" size="10" id="password2" onBlur="Checker.checkPassword()">
				</td>
				<td>
					<div id="password2CheckDiv" class="warning"></div>
				</td>
			</tr>
			<tr>
				<td>头像:</td>
				<td>
					<input type="file" name="avatar" size="10">
				</td>
			</tr>
			
			<tr>
				<td>母语:</td>
				<td>
					<select name="nativelang">
       							<option value="英语" selected="selected">英语</option>
       							<option value="汉语" selected="selected">汉语</option>
       							<option value="日语" selected="selected">日语</option>
       							<option value="其它" selected="selected">其它</option>
						</select>
				</td>
			</tr>
			
			<tr>
				<td>擅长语言:</td>
				<td>
						<select name="lang_1">
       							<option value="英语" selected="selected">英语</option>
       							<option value="汉语" selected="selected">汉语</option>
       							<option value="日语" selected="selected">日语</option>
       							<option value="" selected="selected">无</option>
						</select>
				</td>
			</tr>	
			<!-- <tr>
				<td>相关证书认定:</td>
				<td>
					<input type="file" name="diploma_1" size="10">
				</td>
			</tr> -->
	
		</table>
	
				<input type="submit" value="完成注册" name="Submit" onclick="return ding(2)">
	            <button type="button" onclick="window.location.href='/MicroTasks'">返回主页</button>
	</form>

</body>
</html>