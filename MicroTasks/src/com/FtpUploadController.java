package com;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.utils.FtpUtil;

@WebServlet("/FtpUploadController")
public class FtpUploadController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "upload";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
//		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
//		System.out.println(savePath);
		  try {
	            List<FileItem> formItems = upload.parseRequest(req);
	         
	            if (formItems != null && formItems.size() > 0) {            
	                	   FileItem item = formItems.get(0);
	                       String fileName = item.getName();//会将完整路径名传过来 
	                       System.out.println(fileName);
	                       File file = new File(fileName);
	                       try {
	                   	        Ftp f= new Ftp();
	                	        f.setIpAddr("127.0.0.1");
	                            f.setUserName("rocky");
	                            f.setPwd("");
	                            FtpUtil.connectFtp(f);
	                			FtpUtil.upload(file);
	                		} catch (Exception e) {
	                			// TODO Auto-generated catch block
	                			e.printStackTrace();
	                		}
	                      // System.out.println(fileName);
	                    
	                
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
		
		
		
		
		
		
		
		
		



	
	}

}
