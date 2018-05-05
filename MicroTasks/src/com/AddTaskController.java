package com;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dao.GuestDA;
import com.model.Guest;
import com.model.Task;
import com.utils.SubTitle;

/**
 * Servlet implementation class AddTaskController
 */
@WebServlet("/AddTaskController")
public class AddTaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String UPLOAD_DIRECTORY = "User";
	private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTaskController() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Guest aGuest = (Guest)request.getSession().getAttribute("guest");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=UTF-8");
    	// Get guest ID from client side JSP
    	
    	
		Task t = new Task();
		int score = aGuest.getScore();
		float reward = 0;
		String sourceLang = null;
		String targetLang = null;
		String dstPath = null;
    	t.setSourceID(aGuest.getGuestID());
    	
    	// Set Date
    	t.setSubmitTime(new java.util.Date());
    	
    	// File Upload
    	DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
 
       String uploadPath = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
       String userPath = uploadPath + File.separator + aGuest.getGuestID()+File.separator+"upload";
       File uploadDir = new File(userPath);
       if (!uploadDir.exists()) {
           uploadDir.mkdir();
       }
 
        
        
        
        
        try {
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (item.isFormField()) {
                    	switch(item.getFieldName()){
                    		case "TNameField":
                    			t.setName(new String(item.getString().getBytes("ISO-8859-1"),"UTF-8"));
                    			break;
                    		case "PayField":
                    			reward = Float.parseFloat(item.getString());                   	
                    			t.setReward(reward);
                    			break;
                  		case "DueTimeField":
                    			try {
                    				t.setDueTime(new SimpleDateFormat("yyyy-MM-dd").parse(item.getString()));
                    			} catch (ParseException e) {
                    				e.printStackTrace();
                    			}
                    			break;
                    		case "sourceLangField":
                    			sourceLang = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
                    			t.setSourceLang(sourceLang);
                    			break;
                    		case "targetLangField":
                    			targetLang = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
                    			t.setTargetLang(targetLang);
                    			break;
                    		case "sourceText":
                    			if(!item.getString().equals(""))
                    			{
                    				t.setText(new String(item.getString().getBytes("ISO-8859-1"),"UTF-8"));	
                    				t.setFileType(0);
                    			}
                    			
                    			break;	
                    	}
                        
                    }
                    else{
                    	try {
                    		
                    		if(item.getName()!="")
                    		{
                    			if(item.getName().endsWith(".srt"))
                        		{
                        			t.setFileType(2);	
                        		}
                        		else {
                        			t.setFileType(1);	
    							}
                    			
                    		}
                    	
                    		String fileName = new File(item.getName()).getName();
                            String filePath = userPath + File.separator + fileName;
                            
                            File storeFile = new File(filePath);
                           // System.out.println(filePath);
                            item.write(storeFile);
                            t.setTextFilePath(fileName);
						    if(t.getFileType()==2)
							{
						    	dstPath = userPath + File.separator + "DST_"+ fileName;
						    	SubTitle.translate(filePath, dstPath);
								
							}
                            
                            
                            
                            
                            
                            
                            
                            
						} catch (Exception e) {
							System.out.println("空");
						}
                    
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        t.setDestinationID(null);
    	Guest.initialize();
    	aGuest.addNewTask(t);
    	score = (int)(score - reward);
    	aGuest.setScore(score);
    	GuestDA.updateScore(aGuest.getGuestID(), score);
    	// Update task related information for the guest
    	if(aGuest.getNbProposedTasks() > 0){
    		aGuest.setProposedTaskList(aGuest.findProposedTasks());
    	}
    				
    	aGuest.setReceivedTaskList(aGuest.findReceivedTasks());
    				
    	if(aGuest.getNbAcceptedTasks() > 0){
    		aGuest.setAcceptedTaskList(aGuest.findAcceptedTasks());
    	}
    	
    	// Close the database connection
    	Guest.terminate();
    	
    	request.getSession().setAttribute("guest", aGuest);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/guestHome.jsp");
    	dispatcher.forward(request, response);
    	//response.sendRedirect("MicroTasks/JSP/guestHome.jsp");
	}

}
