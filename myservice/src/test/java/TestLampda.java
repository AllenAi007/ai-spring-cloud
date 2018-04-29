import org.junit.Test;

import java.util.stream.IntStream;

public class TestLampda {

    @Test
    public void testMyFunction() {

        MyClass myClass = new MyClass();

        myClass.sayHello(()-> System.out.println("Hello World!"));
        IntStream.range(0, 4).reduce((a, b) -> a + b);

    }

    class MyClass {

        public void sayHello(MyFunction myFunction) {
            myFunction.func();
        }

    }

    @FunctionalInterface
    interface MyReturnFunction {

        String func();

    }

    @FunctionalInterface
    interface MyFunction {
        void func();
    }


}
