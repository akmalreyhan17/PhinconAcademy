public interface MyInterface {
    // Abstract method (must be implemented by the implementing class)
    void abstractMethod();

    // Default method (provides a default implementation)
    default void defaultMethod() {
        System.out.println("This is the default method in the interface.");
    }

    // Static method (can be called without an instance of the interface)
    static void staticMethod() {
        System.out.println("This is the static method in the interface.");
    }
}


