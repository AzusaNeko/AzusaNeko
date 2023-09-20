package com.azusa.util;

import java.io.*;

public class IOUtil {
    public IOUtil() {
    }
    //使用缓冲输入流来进行IO操作
    public String Read(String fileAddr,String text){
        try {
            File file =new File(fileAddr);
            BufferedInputStream inputStream =new BufferedInputStream(new FileInputStream(file));
            long length = file.length();
            byte[] bytes = new byte[(int) length];
            int b;
            String sim=null;
            while((b=inputStream.read(bytes))!=-1){
                sim = new String(bytes);
            }
            if(sim!=null){
                System.out.println(text+"读取成功");
            }else {
                System.out.println(text+"读取失败");
            }


            return sim;
        } catch (FileNotFoundException e) {
            System.out.println("读取失败文件名错误");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
