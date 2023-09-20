package com.azusa.SimHash;

import com.hankcs.hanlp.HanLP;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

public class SimHash {
    //通过MD5获取对应的hash值
    public static String getHash(String str){
        try{
            // 这里使用了MD5获得hash值
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return new BigInteger(1, messageDigest.digest(str.getBytes("UTF-8"))).toString(2);
        }catch(Exception e){
            e.printStackTrace();
            return str;
        }
    }

    public static int[] getSimHash(String text){
        //分词
        List<String> simLists = HanLP.extractKeyword(text, text.length());
        //获取hash值,合并权值
        int[] sim= new int[128];
        int size=simLists.size();
        for (int i=0;i<size;i++){
            String simList = simLists.get(i);
            String hash = getHash(simList);
            int length = hash.length();
            /*如果不够128位数，泽填补0
            * 一开始没有写这部分，一直报错QAQ*/
            if(hash.length()<128){
                int len=128-length;
                for(int j=0;j<len;j++){
                    hash+='0';
                }
            }
            for(int j=0;j<128;j++){
                if(hash.charAt(j)=='1'){//如果对应为1
                    sim[j]+=10-(i/8);/*每8个一个权重最大为10最小为0,前8个分词权重为10*/
                }else {
                    sim[j]-=10-(i/8);
                }
            }
        }
        //降维
        for (int i=0;i<128;i++){
            if(sim[i]<0){
                sim[i]=0;
            }else{
                sim[i]=1;
            }
        }
        return sim;
    }
    /*计算海明距离*/
    public  double getDistance(String text01, String text02) {
        int[] simHash01 = getSimHash(text01);
        int[] simHash02 = getSimHash(text02);
        int distance = 0;
        for (int i = 0; i < 128; i++) {
            /*不相同，海明距离+1*/
            if(simHash01[i]!=simHash02[i]){
                distance++;
            }
        }
        return 0.01 * (100 - distance * 100 / 128);/*返回换算成百分制*/
    }
}
