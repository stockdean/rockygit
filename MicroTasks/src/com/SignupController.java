package com;

import java.io.File;
import java.io.IOException;
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
import com.mysql.jdbc.Connection;

@WebServlet("/SignupController")
public class SignupController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "User";
	private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 10;  // 10MB
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	
	
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
 
        String uploadPath = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
        String userPath = null;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
        
        
        
        
        
        Guest aGuest = new Guest();

        try {
        	 List<FileItem> formItems = upload.parseRequest(req);
        	 if(formItems != null && formItems.size() > 0)
        	 {
        		 for(FileItem item:formItems)
        		 {
        			 if (item.isFormField()) 
        			 {
        				 switch(item.getFieldName())
        				 {
        				    case "guestid":
        				    String guestID = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
        					aGuest.setGuestID(guestID);

        				    userPath = uploadPath + File.separator + aGuest.getGuestID();
        					break;
        				    case "guestName":
        				    aGuest.setName(new String(item.getString().getBytes("ISO-8859-1"),"UTF-8"));
            				break;
        				    case "password":
        				    aGuest.setPassword(item.getString());
        				    break;
        				   
        				    case "nativelang":
        				    	String nativelang = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
        				    	aGuest.setNativelang(nativelang);
        				    break;
        				    case "lang_1":
        				    	aGuest.setLang(new String(item.getString().getBytes("ISO-8859-1"),"UTF-8"));
        				    break;

        				    default:
        				    break;
        				 }
        			 }
        			 else{
        				 switch(item.getFieldName())
        				 {
        				 case "avatar":
        					 try {
     				    		 System.out.println(userPath);
     				    		 
     				    		 File userDir = new File(userPath);
     				             userDir.mkdir();
     				             String profFile = userPath + File.separator + "profile";
     				             
     				             File profFileDir = new File(profFile);
     				             profFileDir.mkdir();
     				            
                         		 String fileName = new File(item.getName()).getName();
                                 String filePath = profFile + File.separator + fileName;
                                 File storeFile = new File(filePath);
                                 
                                 System.out.println(filePath);
                                 
                                 item.write(storeFile);
                                 aGuest.setAvatar(fileName);
     							
     						} 
     				    	catch (Exception e) {
     				    		 e.printStackTrace();
     						}
     				       break;
/*        				   case "diploma_1":
  				    		try {
  				    			  String fileName = new File(item.getName()).getName();                        		
                                  String filePath = userPath + File.separator + fileName;
                                  File storeFile = new File(filePath);                               
                                  System.out.println(filePath);
                                  
                                  item.write(storeFile);
          				    	  aGuest.setDiploma(fileName);
          				    	  
  							} catch (Exception e) {
  								e.printStackTrace();
  							}
                          		
          				    break;*/
        				 default:
            		     break;
        				 }
        			 }
        		 }
        		 
        	 }
        	
        	
        	
			
		} catch (Exception e) {
			// TODO: handle exception
		}


		
        aGuest.setScore(3000);   //注册送3000
        
        
        
		Guest.initialize();
		GuestDA.add(aGuest);
		Guest.terminate();
		
		req.getSession().setAttribute("userPath", userPath);
		req.getSession().setAttribute("guestid", aGuest.getGuestID());
		RequestDispatcher dispatcher = req.getRequestDispatcher("");
    	dispatcher.forward(req, resp);

		
	}

	

}
