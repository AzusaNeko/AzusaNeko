package com.azusa;


import com.azusa.util.IOUtil;
import com.hankcs.hanlp.HanLP;
import org.junit.Test;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

public class TestIO {
    /*D:\\！实验\\软件工程\\论文查重\\text01.txt*/
    @Test
    public void printText(){
        String addr="D:\\！实验\\软件工程\\论文查重\\text01.txt";
        IOUtil ioUtil = new IOUtil();
        String str = ioUtil.Read(addr,"测试");
        String[] strings = str.split("原");
        for (String string : strings) {
            System.out.println(string);
        }

    }
    @Test
    public void divide(){
        String text="你说得对 但是\n" +
                "《明日方舟》是由鹰角网络自主开发运营的一款策略向即时战略塔防游戏，于2019年5月1日公测。 [1-3] 该游戏适龄级别为12+。\n" +
                "在游戏中，玩家将作为罗德岛的领导者“博士”，带领罗德岛的一众干员救助受难人群、处理矿石争端以及对抗诸如整合运动等其他势力。在错综复杂的势力博弈之中，寻找治愈矿石病的方法。";
        List<String> simList = HanLP.extractKeyword(text, text.length());//取出所有关键词
        for (int i=0;i<simList.size();i++){
            String s = simList.get(i);
            System.out.print(s+"|");
        }
    }
    @Test
    public  void getHash(){
        String str="我超，喜茶真你妈的难喝";
        try{
            // 这里使用了MD5获得hash值
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            String string = new BigInteger(1, messageDigest.digest(str.getBytes("UTF-8"))).toString(2);
            System.out.println(string);
        }catch(Exception e){
            e.printStackTrace();
            /*return str;*/
        }
    }
    @Test
    public void forxunhuan(){
        int []v={1,1,2,5,-4,3,-6};
        String simHash = "";
        for (int j = 0; j < v.length; j++) {
            // 从高位遍历到低位
            if (v[j] <= 0) {
//                simHash += "0";
                v[j]=0;
            } else {
//                simHash += "1";
                v[j]=1;
            }
        }

        System.out.println(Arrays.toString(v));
    }
}

