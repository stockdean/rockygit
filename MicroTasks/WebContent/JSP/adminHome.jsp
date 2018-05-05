<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5/themes/icon.css">

<script src="jquery-easyui-1.5/jquery.min.js" type="text/javascript"></script>  
<script src="jquery-easyui-1.5/jquery.easyui.min.js" type="text/javascript"></script>
<title>管理员主页</title>
</head>
<body class="easyui-layout">
	<!-- ------------------- NORTH -------------------------------------->
	<div region="north" border="true" split="true" style="overflow: hidden;">
        <div class="header" align="center">       
        </div>
    </div>
 
  	<!-- ------------------- WEST -------------------------------------->
    <div region="west" split="true" title="功能菜单" style="width: 200px;">
       	<div id="aa" class="easyui-accordion" style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;">
           	<div title="任务" selected="true" style="overflow: auto; padding: 10px;">
                <ul class="easyui-tree">
               		<li><a href="#" onclick="selectTab('我的主页','#')">我的主页</a></li> 
                    <li><a href="#" onclick="selectTab('发布新任务','#')">发布新任务</a></li>
                    <li><a href="#" onclick="selectTab('已发任务','#')">已发任务</a></li> 
                </ul>
            </div>
 
            <div title="翻译" style="padding: 10px;">  
                <ul class="easyui-tree">  
                    <li><a href="#" onclick="selectTab('做翻译','#')">做翻译</a></li>
             		<li><a href="#" onclick="selectTab('字幕翻译','#')">字幕翻译</a></li>
                </ul>
            </div>
 
      	</div>  <!-- end of accordion -->
   	</div>

 	<!-- ------------------- CENTER -------------------------------------->
   	<div id="mainPanel" region="center" style="overflow: block">
 
   	</div>
   	
   	<!-- ------------------- SOUTH -------------------------------------->
    <div region="south" border="true" split="true" style="overflow: hidden; height: 40px;">
        <div class="footer" align="center">
           	 版权所有：WT@2017
        </div>
    </div>
   	
</body>
</html>