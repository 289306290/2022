package com.java8;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;


///Volumes/My Passport/百度云/2020/Java架构精品资料/架构师之java技术书籍/Java 8实战.pdf
public class Test {


    public static void test1() {
        Stream<Teacher> stream = teacherStream();


        stream.filter(t->t.getAge()>20).forEach(System.out::println); //过滤输出大于20的老师

        stream = teacherStream();

        Map<String,List<Teacher>> teacherBySex =  stream.filter(t -> t.getAge() > 20).collect(Collectors.groupingBy(Teacher::getSex)); //按照性别分组

        System.out.println(teacherBySex);
    }
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }

    public static Stream<Teacher> teacherStream() {
        return Stream.of(new Teacher[]{new Teacher("男","李四",18),
                new Teacher("男","张三",22),
                new Teacher("女","赵好",17),
                new Teacher("女","王青",34),
                new Teacher("男","武大",40)});
    }

    public static void test2() {
        Map<String, String> map = new HashMap<>();
        Consumer<String> consumer1 = (s)->{
            System.out.println("这里是consumer1获取到的参数"+s);map.put(s,"b");
        };
        Consumer<String> consumer2 = (s)->{
            System.out.println("这里是consumer2获取到的另一个参数" + s);
            if (map.get(s).equals("b")) {
                System.out.println("修改map中的值");
                map.put("a","a2");
            }
        };
        Consumer<String> consumer3 = (s)->{
            System.out.println("这里是consumer3获取到的另一个参数"+s+",获取map中的值"+map.get(s));
        };
        consumer1.andThen(consumer2).andThen(consumer3).accept("a");

    }

     public static void test3() {
        Stream.iterate(1,n->n+2).limit(10).forEach(System.out::println);
        Stream.generate(()->new Random().nextInt(100)).limit(10).forEach(System.out::println);
     }

    public static void test4() {
        int[] nums = {4, 3, 1, 2, 6, 8, 9, 7, 5,9};
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();
        System.out.println("最小值:"+min + ",最大值:"+max);
    }

    public static void test5() {
        int[] nums = {4, 3, 1, 2, 6, 8, 9, 7, 5,9};
        Arrays.stream(nums).peek(n -> n = n + 1).filter(n->n>8).forEach(System.out::println);
    }


    /**
     * 函数式编程, 参数行为化，将一段代码作为参数
     */
}
