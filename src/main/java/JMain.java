public class JMain {
    public static void main(String[] args) {
        
        FakeBbc eden = new FakeBbc();
    }
}

abstract class A<T> {
    abstract T a();
}

class B extends A<String> {
    @Override
    String a() {
        return "a";
    }
}