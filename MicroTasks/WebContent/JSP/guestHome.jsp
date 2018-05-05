<%@page import="com.dao.GuestDA"%>
<%@page import="com.model.Answer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.model.Task"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.model.Guest"%>

<%
ArrayList<Task> tl, t2, t3,t4; 

Task t;
Answer a;
Guest guest = (Guest)request.getSession().getAttribute("guest");
t2 = guest.getReceivedTaskList();
String txt = null;

%> 
    
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5/themes/icon.css">

<script src="jquery-easyui-1.5/jquery.min.js" type="text/javascript"></script>  
<script src="jquery-easyui-1.5/jquery.easyui.min.js" type="text/javascript"></script>
<script src="jquery-easyui-1.5/locale/easyui-lang-zh_CN.js" type="text/javascript"></script> 
<title>客人主页</title>

<script>
var selectedRowId;

function showDialog(targetid, flag)
{
	//window.open("addTask.html","Add New Task","height=300,width=300,toolbar=no,menubar=no")
	if (document.getElementById){
        target=document.getElementById(targetid);
            if (flag){
                target.style.display="block";
            } else {
                target.style.display="none";
            }
    }
}

// Used for navigation among tabs
function selectTab(title, url) {
	if($('#home').tabs('exists', title)) {  
	    $('#home').tabs('select', title);  
	}
}

// get selected row in a dataGrid, return true when a row is selected
function getSelected(tableid, formid){
	document.getElementById("acceptDebug").innerHTML = "function called";
    var row = $(tableid).datagrid('getSelected');
    if (row){
    	document.forms[formid]["rowID"].value = row.ID;
    	document.getElementById("acceptDebug").innerHTML = "选中" + document.forms[formid]["rowID"].value;
        return true;
    }
    document.getElementById("acceptDebug").innerHTML = "没有任务被选中";
    return false;
}


   
 function showform(x)
 {
	 var form = document.getElementById("tid"+x);
	
	 form.style.display = "block";
	 
 }
 function hideform(x)
 {
	 var form = document.getElementById("tid"+x);
	 form.style.display = "none";
	 if(document.getElementsByName("a_translation").value!=null)
	 alert("提交成功");
/* 	 else
	 alert("内容不能为空"); */
 }
 function formError(x)
 {
	 var reward = document.forms["AddTaskForm"]["PayField"].value;
	 var tname = document.forms["AddTaskForm"]["TNameField"].value;
     var date = document.forms["AddTaskForm"]["DueTimeField"].value;
	 if(date == "")
	 { alert("日期不能为空");
	   return false; 
	 }
	 if(reward >= 0&&reward<=x)
	{
		 alert("正在上传文件，请耐心等候，结束后自动跳转主页");
		 return true;
	}
	 else if(reward > x)
	{
		 alert("余额不足");
		 return false;
	}
	 else
	{
		alert("格式不正确") ;
		return false;
	}
	return true;
	
 }
 function showalert()
 {
	 

 }
 
 function showEdit(obj)
 {
	 document.getElementById(obj).readOnly=false;

 }
 function saveEdit(obj)
 {
	 document.getElementById(obj).readOnly=true;

 }
 
 function doSome(){  
     alert($("#sel option:selected").text());//方法一：获取select标签选中的option中的文本。  
     alert($("#sel").find("option:selected").text());//方法二：获取select标签选中的option中的文本。   
     alert($("#sel option:selected").val());//方法一：获取select标签选中的option中的value的值。  
     alert($("#sel").find("option:selected").val());//方法二：获取select标签选中的option中的value的值。  

 }  
 
 $(function(){
	   $(":radio").click(function(){
	   if($(this).val()=="text")
		   {
		      document.getElementById("upload").style.display = "none";
		      document.getElementById("sourceT").style.display = "block";
		     // document.getElementById("p").style.display = "none";
		   }
	   else 
		   {
		      document.getElementById("upload").style.display = "block";
		      document.getElementById("sourceT").style.display = "none";
		     //document.getElementById("p").style.display = "block";
		    }
	  });
	 });
 
 
 

   
   
   

 
</script>
</head>

<body class="easyui-layout">
	<!-- ------------------- NORTH -------------------------------------->
	<div region="north" border="true" split="true" style="overflow: hidden;">
        <div class="header" align="center">
        	<h2>欢迎您登陆, 我们的客人<i> <%= guest.getName() %></i>  
        	<a href="/MicroTasks/LogoutController">退出登录</a></h2>
        </div>
    </div>
 
  	<!-- ------------------- WEST -------------------------------------->
    <div region="west" split="true" title="功能菜单" style="width: 200px;">
       	<div id="aa" class="easyui-accordion" style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;">
           	<div title="发任务"  style="overflow: auto; padding: 10px;">
                <ul class="easyui-tree">
               		<li><a href="#" onclick="selectTab('我的主页','#')">我的主页</a></li> 
               		<li><a href="#" onclick="selectTab('我的个人信息','#')">我的个人信息</a></li> 
                    <li><a href="#" onclick="selectTab('发布新任务','#')">发布新任务</a></li>                   
                    <li><a href="#" onclick="selectTab('已发任务','#')">已发任务</a></li> 
                </ul>
            </div>
 
            <div title="做翻译" style="padding: 10px;">  
                <ul class="easyui-tree">  
                    <li><a href="#" onclick="selectTab('做翻译','#')">做翻译</a></li>
             		<li><a href="#" onclick="selectTab('字幕翻译','#')">字幕翻译</a></li>
                </ul>
            </div>
 
      	</div>  <!-- end of accordion -->
   	</div>

 	<!-- ------------------- CENTER -------------------------------------->
   	<div id="mainPanel" region="center" style="overflow: block">
       	<div id="home" class="easyui-tabs">  
        
        <div id="homeDiv" title="我的主页">
        	<p>
        	您目前的积分是： <%= guest.getScore() %><br/>
			您已发布 <%= guest.getNbProposedTasks() %> 个任务,<br/>
			 您有 <%= guest.getNbAcceptedTasks() %> 个回答被采纳。<br/>
			<% if(t2 != null) {%>
			您有 <%= t2.size() %> 个待接受的任务。
			<% }else{ %>
			您暂时没有新任务。
			<% } %>
			</p>
			<a href="/MicroTasks/MyTaskController">查看已回复的任务请求</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        
       <div id="myInfo" title="我的个人信息" >
         <div style="width:40%;float:left">
       	<form method="post" action="/MicroTasks/UpdateInfoController">
       		<table>
       			<tr>
       				<td> 姓名：</td>
       				<td><input type="text" id="my_name" name="my_name" readonly="readonly" value="<%= guest.getName() %>"><button  type="button" onclick="showEdit('my_name')">编辑</button><button  type="button" onclick="saveEdit('my_name')">保存</button></td>
       			</tr>
       			<tr>
       				<td> 密码：</td>
       				<td><input type="password" id="my_pwd" name="my_pwd" readonly="readonly" value="<%= guest.getPassword() %>"><button  type="button" onclick="showEdit('my_pwd')">编辑</button><button  type="button" onclick="saveEdit('my_pwd')">保存</button></td>
       			</tr>
       		    <tr>
       				<td> 母语：</td>
       				<td><input type="text" id="my_nativelang" name="my_nativelang" readonly="readonly" value="<%= guest.getNativelang() %>"><button  type="button" onclick="showEdit('my_nativelang')">编辑</button><button  type="button" onclick="saveEdit('my_nativelang')">保存</button></td>
       			</tr>
       			<tr>
       				<td> 擅长语言：</td>
       				<td><input type="text" id="my_lang"readonly="readonly" name="my_lang" value="<%= guest.getLang() %>"><button  type="button" onclick="showEdit('my_lang')">编辑</button><button  type="button" onclick="saveEdit('my_lang')">保存</button></td>
       			</tr>
       			<tr>
       				<td> 等级：</td>
       				<td> <%= guest.getLevel() %></td>
       			</tr>
       		  <%--   <tr>
       				<td> 是否通过认证：</td>
       				<td>  <% if(guest.getCertificated()==0)
	           						txt = "否";
	              					else
	        	    				txt = "是";
	           				%>
	        	   <span><%=txt %></span>
       			</tr> --%>
       		</table>
       		<input type="submit" value="提交我的修改" name="info_update" >
    </form> 

	</div>	
		<div style="width:40%;float:left">
		   <span style="font-size:15px;line-height:20px">头像：</span>
           <img src="/MicroTasks/User/<%= guest.getGuestID() %>/profile/<%= guest.getAvatar() %>" style="width:100px;height:100px;margin-top:20px">
		</div>		
    </div>
        
	<div id="proposedTaskListDiv" title="已发任务">
		<% if(guest.getNbProposedTasks() > 0){ 
			tl = guest.getProposedTaskList();
		%>
			<table class="easyui-datagrid" data-options="singleSelect:true">
			<thead>
				<tr>
					<th field="ID" width="50">ID</th>
					<th field="name" width="200">名称</th>
					<th field="reward" width="50">酬金</th>
					<th field="publishedTime" width="200">发布时间</th>
					<th field="dueTime" width="200">完成期限</th>
					<th field="accepted" width="60">翻译数</th>
					<th field="finished" width="50">已完成</th>
				</tr>
			</thead>
			<tbody>
		<% for(int i=0; i<tl.size(); i++) {
			t = tl.get(i);  %>
			<tr>
				<td><%= t.getTaskID() %></td>
				<td><%= t.getName() %></td>
				<td><%= t.getReward() %></td>
				<td><%= t.getSubmitTime() %></td>
				<td><%= t.getDueTime() %></td>
				<td><%= t.getAnswerCount() %></td>
				<td><%= t.isFinished() %></td>
			</tr>
		<% } %>
			</tbody>
			</table>
		<% }
		else{
			out.println("您还没有提交任务");
		}
		%>
		</div>
		
		
		<div id="addTaskDiv" title="发布新任务">

		<form name="AddTaskForm" action="/MicroTasks/AddTaskController" method="post" enctype="multipart/form-data" onsubmit="return formError(<%=guest.getScore()%>)">
			<table>
				<tr>
					<td>任务名</td>
					<td><input type="text" name="TNameField" size="20" required="required"></td>
				</tr>
				<tr>
					<td>酬金</td>
					<td><input type="number" name="PayField" size="20" required="required" step="0.01" min="0"></td>
				</tr>
				<tr>
					<td>截止时间</td>
					<td><input type="text"  name="DueTimeField" class="easyui-datebox" ></td>
				</tr> 
				<tr>
					<td>源文件语言</td>
					<td>
						<select name="sourceLangField">
       							<option value="英语" selected="selected">英语</option>
       							<option value="简体中文" selected="selected">简体中文</option>
       							<option value="日语" selected="selected">日语</option>
						</select>
					</td>
				<td>目标语言</td>
					<td>
						<select name= "targetLangField">
       							<option value="英语" selected="selected">英语</option>
       							<option value="简体中文" selected="selected">简体中文</option>
       							<option value="日语" selected="selected">日语</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>任务类型</td>
					<td><input type="radio" name="type" value="text" checked="checked" />文本 <input type="radio" name="type" value="image" />图片  <input type="radio" name="type" value="subtitle" />字幕 
					</td>
					
				</tr>
				<tr>
					<td>原文</td>
					<td><textarea cols="50" rows="10" name="sourceText" id="sourceT"  placeholder="请输入文字"></textarea></td>
				</tr>
				
				<tr>
					<td>上传</td>
					<td><input type="file" name="FileField" size="20" id ="upload" style="display:none"><span>（文件名不能超过20个字符，字幕仅支持UTF-8编码纯英文字幕翻译为中文）</span></td>
				</tr>
			</table>

			<input type="submit" value="提交" name="Submit" >
		</form>

		</div>

	<div id="receive" title="做翻译" style="background:#f9f9f9 ;display:table;width:80%;overflow:auto;height:80%">
		<section>
			<% if(t2.size() > 0){ %>
				<% for(int i=0; i<t2.size(); i++) {
			
			t = t2.get(i);  
			if(t.getFileType()==2)
				continue;
			%>
			<article style="background:#ffffff;margin:40px;border:1px solid #dcdcdc;width:60%;align:center">
					<section style="margin:40px">
						<header>
							<span>
								<span>标题:<%= t.getName() %></span>
							</span>
						</header>
						<div align = "left">
							<span>
								<span>原文:
								<% if(t.getFileType()==0){%>
							
								<%= t.getText() %>
								 <%} %>
								 
								<% if(t.getFileType()==1){%>
							
								<img src="/MicroTasks/User/<%= t.getSourceID() %>/upload/<%= t.getTextFilePath() %>" height="200px" width="200px">
								 <%} %>
									
								</span>
							</span>						
						</div>
						<footer style="margin-top:10px">
						<span>酬金:<%=t.getReward()%>&nbsp;&nbsp;|</span>
						<span><%=t.getSourceLang()%>---<%=t.getTargetLang()%>&nbsp;&nbsp;|</span>
						<button onclick="showform(<%=i%>)">做翻译</button>
					
						<form id="tid<%=i%>" action="/MicroTasks/AddAnswerController" method="post" style=" display:none ">
							<input type="hidden" name="a_guestid" value="<%= guest.getGuestID()%>">
							<input type="hidden" name="a_taskid" value="<%= t.getTaskID()%>">
							<input type="hidden" name="a_targetid" value="<%= t.getSourceID()%>">
							<table>
								<tr>
									<td>翻译：</td>
									<td><textArea name="a_translation" cols="50" rows="10" required="required" placeholder="请输入文字"></textArea></td>
								</tr>
							</table>
							<input type="submit" value="提交翻译" name="a_submit" onclick="hideform(<%=i%>)">	
						</form>
						</footer>
					</section>
					
				</article>
							
	
			<% } %>	
			
		<% }		
		else{
			
			out.println("暂时没有新任务可做");
		
		}
		%>	
		</section>	
		
	
		
		
		
		
		
		
	</div>
		
	<div id="subtitles" title="字幕翻译" style="background:#f9f9f9 ;display:table;width:80%;overflow:auto;height:80%">
			
		<section>
			<% if(t2.size() > 0){ %>
				<% for(int i=0; i<t2.size(); i++) {
			t = t2.get(i);  
			if(t.getFileType()==2)
			{
			 request.getSession().setAttribute("dstFile", t.getTextFilePath());
			 request.getSession().setAttribute("srcUser", t.getSourceID());
			 request.getSession().setAttribute("taskid", t.getTaskID());
			%>
			<div style="margin:30px">
			    <span style="font-size:15px;">字幕名称：</span>
				<a href="/MicroTasks/SubTitleTranslation" style="font-size:15px;"><%=t.getName()%></a>
				<br><br>
			</div>
			<%
			}
			
		
			
			
			
			
			 } %>	
			
			
			
			
			
			
			
		<% }		
		else{
			
			out.println("暂时没有新任务可做");
		
		}
		%>	
		</section>	
		
		
		
		
		
	</div>
	
       	</div>
   	</div>
   	
   	<!-- ------------------- SOUTH -------------------------------------->
    <div region="south" border="true" split="true" style="overflow: hidden; height: 40px;">
        <div class="footer" align="center">
           	 版权所有：WT@2017
        </div>
    </div>
   	
</body>
</html>