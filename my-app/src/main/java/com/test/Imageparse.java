package com.test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Imageparse {

    public static void main (String[] args) throws IOException {
        //首先我们需要输入获取图片的路径信息,将其每个点位设置为二进制信息
        String path = "/Users/a123/Downloads/a.jpeg";
        int[][] img = readPic(path);
        //将其转为一个一维数组
        int[] bits = new int[img.length * img[0].length];
        int index = 0;
        for(int i = 0; i < img.length;i++){//这段代码是查看解析的二维数组是否正确
            for(int j = 0;j<img[i].length;j++){
                bits[index++] = img[i][j];
            }
        }
        //读取数组信息
        parseUTF(bits);
    }

    public static int[][] readPic(String path) throws IOException {
        //这里我们就需要涉及到去拿去所有的数据  rgb  信息，有一个图片  IO  流
        BufferedImage image = ImageIO.read(new FileInputStream(new File(path)));
        //获取这个图片的宽和高
        int width = image.getWidth();
        int height = image.getHeight();
        //创建一个当前图的最大宽高的二维数组
        int[][] img = new int[height][width];
        //到这里我们就该去把每个点位赋值
        for(int i = 0;i<height;i++){
            for(int j = 0;j<width;j++){
                int rgb       = image.getRGB(j,i);//此时这是一个二进制颜色代码
                //条件判断颜色转为      RGB      ，例如（255，0，0）
                int r = (rgb & 0x00ff0000) >>16 ;
                int g = (rgb & 0x0000ff00) >>8 ;
                int b = (rgb & 0x000000ff) ;
                int  a = 0xff /2;
                if(r > a && g > a && b > a){
                    img[i][j] = 0;
                }else{
                    img[i][j] = 1;
                }
            }
        }
        return img;
    }

    public static void  parseUTF(int[] bits){
        int index = 0;
        int count = 0;
        for(int j = 0;j<bits.length;){
            if(bits[j]==1){
                count++;
                index++;
            }else{
                index++;
                String str = "";
                if(count == 0){
                    for(int k=1;k<=7;k++){
                        str += bits[index++];
                    }
                }else {
                    for(int x = 0;x<8-(count+1);x++){
                        str+=bits[index++];
                    }
                    for(int y = 0;y<count-1;y++){
                        index+=2;
                        for(int z = 0;z<6;z++){
                            str+=bits[index++];
                        }
                    }
                }
                count = 0;
                System.out.print((char)Integer.parseInt(str,2));
                str="";
            }
            j=index;
        }
    }
}
