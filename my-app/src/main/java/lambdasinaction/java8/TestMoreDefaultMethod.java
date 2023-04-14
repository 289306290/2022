package lambdasinaction.java8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 一个类实现了多个接口,而这实现的多个接口里面都包含默认的同一个方法时候,必须实现实现对应的方法,不然会报错
 */
public class TestMoreDefaultMethod implements Aninal, FlyAnimal {

    static List list = new ArrayList();
    @Override
    public void walk() {
        System.out.println("aaaa");
    }


    public static void test(String[] args) {
        int max =1200000;
        List<Integer> cities = new ArrayList<Integer>();
        for(int i=0;i<max;i++){
            cities.add(i);
        }
        //indexOf方法取得索引值
        long start = System.nanoTime();
        int index1 = cities.indexOf((max-5));
        long mid = System.nanoTime();
        System.out.println(mid - start);
        //binarySearch查找到索引值
        int index2 = Collections.binarySearch(cities, (max-5));
        long end = System.nanoTime();
        System.out.println(end - mid);
        System.out.println("索引值(indexOf)："+index1);
        System.out.println("索引值（binarySearch)："+index2);
    }


    public static void test1() {
        Predicate<String> a = s -> s.equals("abc");
        Predicate<String> b = s -> list.add(s);
        Consumer s = t -> list.add(t);
        list.add("a");
        Consumer s1 = t -> {};
    }

    public static void test2() {
        String[] arrayOfWords = {"hello","world"};
        List<String> uniqueCharacters = Arrays.stream(arrayOfWords).map(w->w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        uniqueCharacters.stream().forEach(System.out::println);

        String [] a = "hello".split("");
        System.out.println(a);
    }

    public static void test3() {
        Integer[] arr = {4,5,3,9};
        List<Integer> list = Arrays.asList(arr);
        list.stream().reduce(Integer::sum).ifPresent(System.out::println);
        Optional<Integer> maxValue= list.stream().reduce(Integer::max);
        maxValue.ifPresent(System.out::println);
        int t = maxValue.orElse(-1);
        System.out.println(t);
        IntStream.rangeClosed(1,100).forEach(System.out::println);

    }

    /**
     * 打印斐波那契元组, 斐波那契数列是0 1 1 2 3 5 8 13 21 34
     * 斐波那契元组是 (0,1) (1,1),(1,2),(2,3),(3,5),(5,8),(8,13) 可以看到 （arr[0]   arr[0] + arr[1]）这么一个规律
     */
    public static void testStreamIterate() {
//        Stream.iterate(0,n -> n+2).limit(5).forEach(System.out::println);

        Stream.iterate(new int[]{0,1}, t-> new int[]{t[1],t[0]+t[1]}).limit(10).forEach(t -> System.out.println("(" + t[0]+","+ t[1] +")"));
        System.out.println("------");
        Stream.iterate(new int[]{0,1}, t-> new int[]{t[1],t[0]+t[1]}).limit(10).map(t->t[0]).forEach(System.out::println);

    }

    public static void testStreamgenerate() {
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    public static void testMap2Int() {
        List<Person> list = new ArrayList<>();
        Person p1 = new Person(null, "张三");
        Person p2 = new Person(1, "李四");
        Person p3 = new Person(2, "王五");
        list.add(p1);
        list.add(p2);
        list.add(p3);
        int sumAge = list.stream().map(Person::getAge).reduce(Integer::sum).get();
        System.out.println(sumAge);
    }

    public static void test5(){
        IntStream.range(2,3).forEach(System.out::println);
//        boolean t = IntStream.range(2,2).noneMatch(i->2 % i ==0);
//        System.out.println(t);
    }

    public static void testZeRenLianMoShi(){
        UnaryOperator<String> headerProcessing = t->"From beijing:" + t;
        UnaryOperator<String> second = t->t.replaceAll("beijing","shanghai");
        Function<String, String> pipeline = headerProcessing.andThen(second);
        String result = pipeline.apply("go home");
        System.out.println(result);

    }

    public static void testCompletableFuture() {

    }
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        testStreamgenerate();
//        testMap2Int();
//        test5();
        testZeRenLianMoShi();
        testCompletableFuture();
    }
}
