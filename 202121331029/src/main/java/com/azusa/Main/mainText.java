package com.azusa.Main;

import com.azusa.SimHash.SimHash;
import com.azusa.util.IOUtil;

public class mainText {
    public static void main(String[] args) {
        IOUtil ioUtil01 = new IOUtil();//论文原文的路径
        IOUtil ioUtil02 = new IOUtil();//需要查重的论文路径
        String text01 = ioUtil01.Read("D:\\！实验\\软件工程\\论文查重\\text01.txt", "论文原文");
        String text02 = ioUtil02.Read("D:\\！实验\\软件工程\\论文查重\\text02.txt", "查重文件");
        System.out.println("开始进行查重.....");
        try {
            Thread.sleep(10);
            SimHash simHash = new SimHash();
            double distance = simHash.getDistance(text01, text02);
            System.out.println("经检查两篇文章相似度为："+(int)(distance*100)+"%");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
