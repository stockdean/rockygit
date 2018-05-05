package com.utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import com.utils.baidu.translate.demo.Decode;
import com.utils.baidu.translate.demo.TransApi;


public class SubTitle {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20180118000116613";
    private static final String SECURITY_KEY = "fnucqPkLmd0Y4GVOB4Lc";

    public static void translate (String sourceFile,String dstFile)throws UnsupportedEncodingException {
    	
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        Scanner scan = new Scanner(System.in);
        String str3,str4;
        int flag = 0;
        String file = sourceFile;
        String filewrite = dstFile;
        String purposeLang = "zh";
        int count = 2;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            BufferedWriter bw = new BufferedWriter(new FileWriter(filewrite));
            String str;
            
                        while ((str = br.readLine()) != null) {                            	
						 //System.out.println(str);
                       
                        if(flag == 0)
                        {
                        	if(count > 0)
                        	{
                            	bw.write(str);
        				        bw.newLine();
                        		
                        	}
                        	if(count == 0)
                        	{
                        		flag = 1;
                        	}
                        	
                        	
                        }
                         if(str.equals(""))
                        {
                        	flag = 2;
                        	count = 3;    
                        	
                        }
                         if(flag == 2)
                        {
                        	 if(count!=0)
                        	 {
                        		 bw.write(str);
 				                 bw.newLine();
                        		 
                        	 }
                        	if(count == 0)
                        	{
                        		flag = 1;
                        	}
                        }
                        if(flag == 1)
                        {
                        	 String strs = api.getTransResult(str, "auto", purposeLang);
     						// System.out.println(strs); 	
     						 str3 = Decode.decodeUnicode(strs);
     						 bw.write(str3);
     				         bw.newLine();
     						 System.out.println(str3); 	
                        	
						}
                        
                	
                        if(flag == 0||flag == 2)	
                        count -- ;
                        	
	 
            }
           bw.close();
           br.close();
           
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        
        
        
        
        
        
//        String query = scan.nextLine();
//        String purposeLang = scan.nextLine();
//        String strs = api.getTransResult(query, "auto", purposeLang);
//        //System.out.println(api.getTransResult(query, "auto", "zh"));
//        if(purposeLang.equals("zh"))
//        {   System.out.println(strs);
//            String str3 = Decode.decodeUnicode(strs);
//            System.out.println(str3); 	
//        }
//        else{
//        	
//            System.out.println(StringCut.stringCut(strs));
//        }
//      
//    
        
        
    
    }

}
