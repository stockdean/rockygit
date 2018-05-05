package com.utils.baidu.translate.demo;

public class Decode {
	 public static String decodeUnicode(final String dataStr) {     
         int start = 0;     
         int end = 0;     
         int flagstart = dataStr.indexOf("\\u",start);
         if(flagstart < 0){
        	 return dataStr;
         }
         int flagend = dataStr.indexOf("\"",flagstart);
         String datastr2 =dataStr.replace("//", "////");    
         String datasub =datastr2.substring(flagstart,flagend);
         flagstart = 0;
       
         final StringBuffer buffer = new StringBuffer();  
         while (flagstart > -1) {     
             end = datasub.indexOf("\\u", flagstart + 2);     
            // System.out.println("end:"+end);
             String charStr = ""; 
            // System.out.println(datasub.length());
             if (end == -1) {
            	 if(datasub.length() == flagstart + 6){
            	 charStr = datasub.substring(flagstart + 2, datasub.length()); 
            	 }
            	 else{
            	 String charStr2 = datasub.substring(flagstart + 6, datasub.length()); 
            	 buffer.append(charStr2);
            	
            	 }
                   
             } else {     
    
            		charStr = datasub.substring(flagstart + 2, end);   
            
                            
             }  
      if(charStr.length() == 4){
          char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。     
          buffer.append(new Character(letter).toString());     
      }
    
             
        	 if(end - flagstart > 6){
        	    buffer.append(datasub.substring(flagstart + 6, end));
        	 }
             flagstart = end;    
           //  System.out.println("start:"+flagstart);
         }     
         return buffer.toString();     
      }  

}
