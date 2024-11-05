import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        List<Integer> number = new ArrayList<>();
        number.add(1);
        number.add(2);
        // square(number);

        List<Integer> numberA = new ArrayList<>();
        List<Number> numberB = new ArrayList<>();
        List<Object> numberC = new ArrayList<>();
        List<Double> numberD = new ArrayList<>();

        addToList(numberA);
        addToList(numberB);
        addToList(numberC);
        //addToList(numberD); // error compiler


        List<Double> fnumber = new ArrayList<>();
        fnumber.add(1.0);
        fnumber.add(2.0);
        square(fnumber);

        List<String> fruit = new ArrayList<>();
        fruit.add("Apple");
        fruit.add("Berry");
        // square(fruit); // error compiler

        // list.add("Apple"); // error compiler

        // for (Integer item : list) {
        // Integer result = item * item;
        // System.out.println(result);
        // }

        // square(list);

        // Box<Integer> intBox = new Box<>();
        // intBox.setValue(123);

        // System.out.println("int: " + intBox.getValue());

        Integer a = 17;
        int b = a;

        System.out.println(b);

        Box<Integer> intBox = new Box<>();
        intBox.setValue(123);

        Box<Double> douBox = new Box<>();
        douBox.setValue(22.5);

        // Box<AtomicInteger> atmBox = new Box<>(); // error compiler
        // atmBox.setValue(new AtomicInteger(1));

        // Box<String> strBox = new Box<>(); // error compiler
        // strBox.setValue("Hello");


        //System.out.println("str: " + strBox.getValue());

        Pair<Integer, String> idNamePair = new Pair<>(1, "Alice");
        printPair(idNamePair);

        // System.out.println("Key: " + idNamePair.getKey() + ", Value: " +
        // idNamePair.getValue());

        Pair<String, Double> productPricePair = new Pair<>("Apple", 0.99);
        System.out.println("Key: " + productPricePair.getKey() + ", Value: " + productPricePair.getValue());

    }

    public static <T extends Number> void square(List<T> numbers) {
        for (T item : numbers) {
            Double result = item.doubleValue() * item.doubleValue();
            System.out.println(result);
        }
    }

    
    // public static void addObject(List<? super Integer> list) {
    // list.add(42);
    // list.add();
    // }

    public static void addToList(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    public static <K, V> void printPair(Pair<K, V> pair) {
        System.out.println("Key: " + pair.getKey() + ", Value: " + pair.getValue());
    }

    public List<?> smallList(List<?> list) {
        List<?> myList = list.subList(0, 1);  
        return myList;
    }

    public void processNumbers(List<? extends Number> list) {
        for (Number num : list) {
            System.out.println(num);
        }
    }

    public <T> List<T> genericMethod(List<T> list) {
        return list.stream().collect(Collectors.toList());
    }

    //public void process(List<String> list) { }
    
    public void process(List<Integer> list) { }

}

