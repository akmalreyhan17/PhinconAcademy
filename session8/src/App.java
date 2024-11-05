import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        // Runnable r = () -> System.out.println("Hello, World!");

        // new Thread(r).start();

        Predicate<Integer> isPositive = x -> x > 0;

        Function<String, String> stringLength = a -> {return a.toLowerCase();};

        Consumer<String> print = x -> System.out.println(x);

        Supplier<Double> randomValue = () -> Math.random();

        Calculator add = (a, b) -> a + b;
        Calculator subtract = (a, b) -> a - b;

        System.out.println("Addition: " + add.calculate(10, 5));
        System.out.println("Subtraction: " + subtract.calculate(10, 5));

        Function<List<Integer>, Integer> size =

                list -> {
                    list.add(1);
                    list.add(2);
                    return list.size();
                };

        // Runnable r1 = new Runnable() {
        //     @Override
        //     public void run() {
        //         System.out.println("Hello, world!");
        //     }
        // };

        // Runnable r2 = () -> System.out.println("Hello, world!");

        // System.out.println(r1);
        // System.out.println(r2);

        Foo foo = parameter -> buildString(parameter);

        System.out.println(foo);

        Fii rr = (a, b) -> a.toLowerCase() + b.toLowerCase();

        // int x = 10; // Effectively final
        // Runnable r1 = () -> System.out.println(x);
        // r1.run();

        final int y = 20;
        Runnable r2 = () -> System.out.println(y);
        r2.run();

        // int x = 10;
        // Runnable r = () -> {
        //     x++;
        //     System.out.println(x);
        // };
        // r.run();

        // int[] arr = {10}; // Array reference is effectively final
        // Runnable r = () -> {
        //     arr[0]++; // Modifying the array element, not the reference
        //     System.out.println(arr[0]);
        // };
        // r.run();

        AtomicInteger counter = new AtomicInteger(10); // AtomicInteger is mutable
        Runnable r = () -> {
            counter.incrementAndGet(); // Modifying the counter value
            System.out.println(counter.get());
        };
        r.run();
    
        // Using lambda expression
        Function<String, Integer> lambdaFunction = str -> Integer.parseInt(str);
        
        // Using method reference
        Function<String, Integer> methodRefFunction = Integer::parseInt;

        System.out.println(lambdaFunction.apply("123")); // Output: 123
        System.out.println(methodRefFunction.apply("456")); // Output: 456

        // MyClass obj = new MyClass();

        // // Using lambda expression
        // Supplier<String> lambdaSupplier = () -> obj.instanceMethod();
        
        // // Using method reference
        // Supplier<String> methodRefSupplier = obj::instanceMethod;

        // System.out.println(lambdaSupplier.get()); // Output: Instance Method Called
        // System.out.println(methodRefSupplier.get()); // Output: Instance Method Called

        // List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // // Using lambda expression
        // names.forEach(name -> System.out.println(name));

        // // Using method reference
        // names.forEach(System.out::println);

        // Using lambda expression
        Supplier<MyClass> lambdaSupplier = () -> new MyClass();
        
        // Using method reference
        Supplier<MyClass> methodRefSupplier = MyClass::new;

        MyClass myClass1 = lambdaSupplier.get(); 
        MyClass myClass2 = methodRefSupplier.get();
        
        System.out.println(myClass1);
        System.out.println(myClass2);

        // Using lambda to define the operation
        MathOperation addition = (a, b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> a / b;

        // Passing lambda as a parameter
        System.out.println("Addition: " + operate(5, 3, addition));
        System.out.println("Subtraction: " + operate(5, 3, subtraction));
        System.out.println("Multiplication: " + operate(5, 3, multiplication));
        System.out.println("Division: " + operate(5, 3, division));

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

        // Using a lambda expression to define the filtering condition
        Predicate<String> startsWithC = name -> name.startsWith("C");

        // Filtering names that start with "C"
        List<String> result = filterNames(names, startsWithC);
        System.out.println(result);
    }

    private static String buildString(String parameter) {
        String result = "Something " + parameter;
        //many lines of code
        return result;
    }

    public static int operate(int a, int b, MathOperation operation) {
        return operation.operate(a, b);
    }

    public static List<String> filterNames(List<String> names, Predicate<String> condition) {
        return names.stream()
                    .filter(condition)
                    .collect(Collectors.toList());
    }


}
