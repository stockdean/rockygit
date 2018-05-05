package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Guest;
import com.utils.TimeOptimizer;
import com.utils.baidu.translate.demo.Decode;


@WebServlet("/SubTitleTranslation")
public class SubTitleTranslation extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		ArrayList<TimeOptimizer> topList = new ArrayList<TimeOptimizer>();
		String file =(String)req.getSession().getAttribute("dstFile");
		String srcUser =(String)req.getSession().getAttribute("srcUser");
		String path = getServletContext().getRealPath("./") + File.separator + "User"+File.separator+srcUser+File.separator+"upload"+File.separator;
		String srcPath = path + file;
		Guest guest = (Guest)req.getSession().getAttribute("guest");
		String prePath = path +"DST_"+file;
		String dstPath = path +"DST_"+guest.getGuestID()+"_"+file;
		out.println("<html>");
		out.println("<head>");
		
		out.println("<link rel='stylesheet' type='text/css' href='jquery-easyui-1.5/themes/default/easyui.css'>");
		out.println("<link rel='stylesheet' type='text/css' href='jquery-easyui-1.5/themes/icon.css'>");
		out.println("<script src='jquery-easyui-1.5/jquery.min.js' type='text/javascript'></script>  ");
		out.println("<script src='jquery-easyui-1.5/jquery.easyui.min.js' type='text/javascript'></script>");
		out.println("<script src='js/pageControl.js' type='text/javascript'></script>");
		out.println("<title>字幕翻译</title>");
		

		out.println("</head>");
		out.println("<body class='easyui-layout'>");
		//<!-- ------------------- NORTH -------------------------------------->
		out.println("<div region='north' border='true' split='true' style='overflow: hidden;'>");

		out.println(" <div class='header' align='center'>");
	    out.println("<h2>字幕翻译区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:history.go(-1);' style='font-size:12px'>返回主页</a></h2>");
	    out.println("</div>"); 
	    
	    out.println("</div>"); 
	  //<!-- ------------------- CENTER -------------------------------------->
	        out.println("<div region='center' style='overflow: block'>");
	        
			out.println("<div style='float:left'>");
			
			out.println("<textArea id='pp' cols='60' rows='40%' name ='srcFile' readonly='readonly' style='margin:30px;resize:none;'>");
			topList = printText(out,srcPath);
			out.println("</textArea>");		
			
			out.println("</div>");
			
			out.println("<div style='float:left'>");
			//out.println("<input class='easyui-slider' ='height:300px' data-options='showTip: true");
		
			out.println("<form method = 'post' action='/MicroTasks/SaveTranslationController'>");
			out.println("<input type='hidden' id='dstPath' name='dstPath' value='"+dstPath+"'>"); 
			
			out.println("<textArea cols='60' rows='40%' id='tt' id='dstFile' name='dstfile' style='margin:30px;resize:none;'>");
			printText(out,prePath);
			out.println("</textArea>");
			out.println("<input  type='submit' value='提交结果' >");	
			out.println("</form>");
			out.println("</div>");
			
			out.println("</div>");
//<!-- ------------------- EAST -------------------------------------->		
			
			out.println("<div region='east' split='true' title='字幕信息' style='width: 500px;padding:40px'>");
			out.println("<div>");
			out.println("<span style='text-align: center;display:block;'>整个文件共有"+topList.size()+"条字幕</span><br>");
			out.println("<span style='text-align: center;display:block;'>初始序号:"+topList.get(0).getNo()+"</span><br>");
			out.println("<span style='text-align: center;display:block;'>结束序号:"+topList.get(topList.size()-1).getNo()+"</span><br>");
			out.println("<span style='text-align: center;display:block;'>请不要修改序号和时间轴</span>");
			out.println("</div>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
			
			
	}
	
	
	
	
	
	
	
	public ArrayList<TimeOptimizer> printText(PrintWriter out,String path)
	{
		
	    ArrayList<TimeOptimizer> topList = new ArrayList<TimeOptimizer>();
	    TimeOptimizer top = new TimeOptimizer();
        int flag = 0;
        int count = 2;
        int index = 0;
        int endIndex = 0;
		 try {
	            FileReader fr = new FileReader(path);
	            BufferedReader br = new BufferedReader(fr);
	            String str;
	            String startT;
	            String endT;
	     while ((str = br.readLine()) != null) {  		   
	            if(flag == 0)
                {

                	if(count == 2)
                	{
                		top.setNo(str);
                		
               	}
                	if(count == 1)
                	{
                		index =str.indexOf(" --> ");
                		startT =str.substring(0, index);
                		endIndex = index+4;
                		endT = str.substring(endIndex+1, str.length());
                		top.setStartTime(startT);
                		top.setEndTime(endT);   
                		
                		topList.add(top);
                	//	System.out.println(topList.get(0).getNo());
                		flag = 1;
                		
                	}
                }
                 if(str.equals(""))
                {
                	flag = 0;
                	count = 3;    
	            	top = new TimeOptimizer();
                }

                if(flag == 0)	
                count -- ;
	         
                
	           out.println(str);
	        }
	        br.close();
		 }
	     catch (IOException e) {
	            e.printStackTrace();
	        }
/*		 for(int i = 0;i<topList.size();i++)
		 {
			TimeOptimizer topp = topList.get(i);
			System.out.println(topp.getNo());
		 }*/
		 return topList;
	}
	

}
