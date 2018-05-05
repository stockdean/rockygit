package com.utils.translate.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
	
    public  void readLine(String file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str;
                        while ((str = br.readLine()) != null) {     
                        	
						 System.out.println(str);
            }
           
           br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeLine(String file)
    {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("第一行");
            bw.newLine();
            bw.write("第二行");
            bw.newLine();
            bw.write("第三行");
           bw.close();
        }catch(IOException e){
           e.printStackTrace();
       }
    	
    	
    }

    
    
    
	

}
