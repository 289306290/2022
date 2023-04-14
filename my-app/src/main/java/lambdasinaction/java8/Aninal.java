package lambdasinaction.java8;

public interface Aninal {
    default public void walk(){
        System.out.println("这里是Animal类的walk方法");
    }
}
