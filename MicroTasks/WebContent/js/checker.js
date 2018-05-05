var Checker = new function(){

	this._url="checker.jsp";	
	this._infoDivSuffix="CheckDiv";
	
	
	
	//检查普通输入信息
	this.checkNode = function(_node){
		var nodeId =_node.id;
		if(nodeId == "GIDField")
		this._url="JSP/checker.jsp";		
		if(_node.value!="")
		{ 			
			var xmlHttp = this.createXmlHttp();
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState == 4)
				{
					Checker.showInfo(nodeId + Checker._infoDivSuffix,xmlHttp.responseText);

				}
				
			};
			
			xmlHttp.open("POST",this._url,true);
			xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			xmlHttp.send("name="+encodeURIComponent(_node.id)+"&value="+encodeURIComponent(_node.value));
			

			
		}
		
	};
	
	//显示服务器反馈信息
	this.showInfo = function(_infoDivId,text)
	{
		var infoDiv = document.getElementById(_infoDivId);
		var status = text.substr(0,1);
		if(status =="1")
		{
			infoDiv.className = "ok";
		}else{
			infoDiv.className = "warning";
		}
		infoDiv.innerHTML = text.substr(1);
	};
	
	this.createXmlHttp = function()
	{
		var xmlHttp = null;
		if(window.XMLHttpRequest)
		{
			xmlHttp = new XMLHttpRequest();
			
		}
		else{
		    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");//IE	
	     }
	return xmlHttp;
};
	this.checkPassword = function()
	{
		var p1 =document.getElementById("password").value;
		var p2 =document.getElementById("password2").value;
		
		if(p1!=""&&p2!="")
		{
			if(p1!=p2)
			{
				this.showInfo("password2"+Checker._infoDivSuffix,"0密码验证与密码不一致。");
			}
			else{
				this.showInfo("password2"+Checker._infoDivSuffix,"1密码无误");
			}
		}else if(p1!=null)
		{
			this.showInfo("password"+Checker._infoDivSuffix,"1");
		}
		
		

		
	};
	
};