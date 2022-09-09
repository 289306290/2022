package com.mycompany.app;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.getProperties().list(System.out);
        System.out.println(System.getProperty("user.name"));
        Random random = new Random();
        System.out.println(random.nextInt(1)); //永远是0
        System.out.println(Integer.MAX_VALUE);//Int最大21亿
        System.out.println(Long.MAX_VALUE);

        int a=1, b=2,c=3;
        System.out.println(a + b + " aa " + c);

    }
}
