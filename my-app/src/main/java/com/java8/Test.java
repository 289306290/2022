package com.java8;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


///Volumes/My Passport/百度云/2020/Java架构精品资料/架构师之java技术书籍/Java 8实战.pdf
public class Test {

    public static void main(String[] args) {
        Stream<Teacher> stream = teacherStream();


        stream.filter(t->t.getAge()>20).forEach(System.out::println); //过滤输出大于20的老师

        stream = teacherStream();

        Map<String,List<Teacher>> teacherBySex =  stream.filter(t -> t.getAge() > 20).collect(Collectors.groupingBy(Teacher::getSex)); //按照性别分组

        System.out.println(teacherBySex);

    }

    public static Stream<Teacher> teacherStream() {
        return Stream.of(new Teacher[]{new Teacher("男","李四",18),
                new Teacher("男","张三",22),
                new Teacher("女","赵好",17),
                new Teacher("女","王青",34),
                new Teacher("男","武大",40)});
    }


    /**
     * 函数式编程, 参数行为化，将一段代码作为参数
     */
}
