package lambdasinaction.java8;

public interface FlyAnimal {
    default void walk() {
        System.out.println("这里是FlyAnimal的walk方法");
    }
}
