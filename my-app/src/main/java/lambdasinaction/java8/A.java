package lambdasinaction.java8;

public enum A implements Aninal{
    ;

    @Override
    public void walk() {
        Aninal.super.walk();
    }
}
