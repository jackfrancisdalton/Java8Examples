package Interface;

public class FunctionalInterfaceExamples implements ExampleInterface {

    public FunctionalInterfaceExamples() {}

    @Override
    public void test() {
        print("Hello");
    }
}

@FunctionalInterface
interface ExampleInterface {

    void test();

    //default method
    default void print(String string) {
        printObjAndClassName(string);
    }

    //Static method can only be used inside of interface
    static void printObjAndClassName(Object object) {
        System.out.print(object.getClass().toGenericString() + ": " + object);
    }
}