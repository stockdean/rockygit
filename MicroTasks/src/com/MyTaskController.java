package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AnswerDA;
import com.model.Answer;
import com.model.Guest;
import com.model.Task;
@WebServlet("/MyTaskController")
public class MyTaskController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
					resp.setContentType("text/html;charset=UTF-8");
					resp.setCharacterEncoding("UTF-8");
					PrintWriter out = resp.getWriter();

					int tid,count = 0;
				
					Guest guest = (Guest)req.getSession().getAttribute("guest");
					ArrayList<Answer> myTasks = new ArrayList<Answer>();
					ArrayList<Task> taskList = new ArrayList<Task>();
					Answer answer = new Answer();
					Task task = new Task();
					Answer.initialize();
					myTasks = AnswerDA.findAnswer(guest.getGuestID());
					MyTaskController.sortIntMethod(myTasks);
					taskList = guest.getProposedTaskList();
					
					
					
					
					out.println("<html>");
					out.println("<head>");
					
					out.println("<link rel='stylesheet' type='text/css' href='jquery-easyui-1.5/themes/default/easyui.css'>");
					out.println("<link rel='stylesheet' type='text/css' href='jquery-easyui-1.5/themes/icon.css'>");
					out.println("<script src='jquery-easyui-1.5/jquery.min.js' type='text/javascript'></script>  ");
					out.println("<script src='jquery-easyui-1.5/jquery.easyui.min.js' type='text/javascript'></script>");
					out.println("<title>我的翻译管理</title>");
					

					out.println("</head>");
					out.println("<body class='easyui-layout'>");
					//<!-- ------------------- NORTH -------------------------------------->
					out.println("<div region='north' border='true' split='true' style='overflow: auto;'>");

					out.println(" <div class='header' align='center'>");
				    out.println("<h2>任务管理区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:history.go(-1);' style='font-size:12px'>返回主页</a></h2>");
				    out.println("</div>"); 
				    
				    out.println("</div>"); 
				    out.println("<div region='center'  style='overflow: block;'>");
				    out.println("<div style='background:#f9f9f9 ;display:table;width:100%;overflow: auto;height:100%'>");
					//out.println("<p>");
					Answer.terminate();
					if(myTasks.size()>0)
					{
						for(int i = 0;i < myTasks.size();i++)
						{
							
							answer = myTasks.get(i);
							tid = answer.getTaskid();
							for(int j = 0;j<taskList.size();j++)
							{	
								task = taskList.get(j);
					
								if(task.getTaskID()==tid)
								{	//System.out.println(count);
									out.println("<div style='background:#ffffff;margin:40px;margin-left:180px;border:1px solid #dcdcdc;width:30%;align:center;float:left'>");
									if(count == task.getAnswerCount()-1)
										count = -1;
									if(count==0||task.getAnswerCount()==1)
									{	
										//out.println("<-----------------------------------------------------------------------></br>");
										
										if(task.getFileType()==0)
										{
											out.println("<span style='font-size:15px'>我的原文:"+task.getText()+"</br></span>");
										}
										else if(task.getFileType()==1){
											out.println("<span style='font-size:15px'>我的原文:<img src='/MicroTasks/User/"+task.getSourceID()+"/upload/"+task.getTextFilePath()+"' height='200px' width='200px'></br></span>");
										}
										else {
											out.println("<span style='font-size:15px'>我发布的:"+task.getName()+"</br></span>");
										}
										
									}
									if(task.getFileType()<2)
									out.println("<span style='font-size:15px'>翻译:"+answer.getTraslation()+"</br></span>");
									else
									{
									String path = "/MicroTasks/User/"+task.getSourceID()+"/upload/"+"DST_"+answer.getGuestid()+"_"+task.getTextFilePath();
									out.println("<a href='"+path+"' style='font-size=15px;'>下载地址</a></br>");
									}
									out.println("<span style='font-size:15px'>翻译时间:"+answer.getTime()+"</br></span>");
									if(answer.getAccepted()==0&&!task.isFinished())
									{
										out.println("<form method='post' action='/MicroTasks/AcceptAnswerController'>");
										out.println("<input type='hidden' name='answerid' value='"+answer.getAnswerid()+"'>");
										out.println("<input type='hidden' name='taskid' value='"+answer.getTaskid()+"'>");
						
										out.println("<input type='hidden' name='guestid' value='"+answer.getGuestid()+"'>");
										out.println("<input type='submit' value='接受翻译'></br></br>");
										out.println("</form >");
									
									}
									else if(answer.getAccepted()==1&&task.isFinished()){
										
										out.println("<span style='font-size:15px'>已接收此翻译</br></br></span>");
										
										
									}
									else {
										out.println("<span style='font-size:15px'>未采纳</br></br></span>");
									}
									out.println("</div>");	
								  
								}
							
							}
							count++;
							
							
						}
					}
					else{
						out.println("您还没有完成的请求</br>");
					}
					out.println("</div>"); 
					req.getSession().setAttribute("guest", guest);

					//out.println("</p>");
					   out.println("</div>"); 
					//out.println("<a href='javascript:history.go(-1);'>返回上一页</a>");
					out.println("</body>");
					out.println("</html>");
					out.close();
					
					
					
					

			}
			
			@SuppressWarnings("unchecked")
			public static void sortIntMethod(ArrayList<Answer> list){
				  Collections.sort(list, new Comparator<Answer>(){
				        public int compare(Answer a1, Answer a2) {
				           
				            if(a1.getTaskid()>a2.getTaskid()){
				                return 1;
				            }else if(a1.getTaskid()==a2.getTaskid()){
				                return 0;
				            }else{
				                return -1;
				            }
				        }
				       
				
				}
						  );
			

}
}
