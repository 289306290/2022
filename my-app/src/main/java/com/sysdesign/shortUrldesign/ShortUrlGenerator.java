package com.sysdesign.shortUrldesign;

import java.math.BigInteger;
import java.security.MessageDigest;

public class ShortUrlGenerator {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALPHABET.length();

    public static String generate(String longUrl) {
        try {
            byte[] bytesOfMessage = longUrl.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] theDigest = md.digest(bytesOfMessage);
            System.out.println("Md5之后是---》 "+new String(theDigest,"UTF-8"));
            BigInteger bigInt = new BigInteger(1, theDigest);
            String hash = bigInt.toString(16);
            System.out.println("转换为16进制是:" + hash);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                String subStr = hash.substring(i * 4, i * 4 + 4);
                System.out.println("第   "+i+"    个四位:      "+subStr);
                int index = Integer.parseInt(subStr, 16) % BASE;
                sb.append(ALPHABET.charAt(index));
                System.out.println("生产的结果:"+sb);
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generating short url", e);
        }
    }

    public static void main(String[] args) {
//        System.out.println(generate("https://www.baidu.com/s?wd=idea%E6%A0%BC%E5%BC%8F%E5%8C%96%E4%BB%A3%E7%A0%81%E5%BF%AB%E6%8D%B7%E9%94%AE%E8%AE%BE%E7%BD%AE&rsv_spt=1&rsv_iqid=0xe131636a00020858&issp=1&f=3&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_dl=ts_0&rsv_sug3=16&rsv_sug1=8&rsv_sug7=100&rsv_t=02beGjM9BYrF5Yu5v3%2FS9YzZB5ysw1w3vBv4XZN523uk8kCv8ImjIHSh19MrJNRAaq4F&rsv_sug2=0&rsv_btype=i&prefixsug=idea%2520%25E6%25A0%25BC%25E5%25BC%258F%25E5%258C%2596&rsp=0&inputT=5530&rsv_sug4=5530"));
//        System.out.println(generate("https://www.baidu.com/s?wd=idea%E6%A0%BC%E5%BC%8F%E5%8C%96%E4%BB%A3%E7%A0%81%E5%BF%AB%E6%8D%B7%E9%94%AE%E8%AE%BE%E7%BD%AE&rsv_spt=1&rsv_iqid=0xe131636a00020858&issp=1&f=3&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_dl=ts_0&rsv_sug3=16&rsv_sug1=8&rsv_sug7=100&rsv_t=02beGjM9BYrF5Yu5v3%2FS9YzZB5ysw1w3vBv4XZN523uk8kCv8ImjIHSh19MrJNRAaq4F&rsv_sug2=0&rsv_btype=i&prefixsug=idea%2520%25E6%25A0%25BC%25E5%25BC%258F%25E5%258C%2596&rsp=0&inputT=5530&rsv_sug4=5530"));
        System.out.println(generate("https://www.baidu.com/s"));

        BigInteger b = new BigInteger("17");
        System.out.println(b.toString(16));
    }
}
