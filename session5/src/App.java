import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        // int[] numbers; // Declaring an array of integers

        // Static initialization
        // int[] numbers = new int[5]; // An array with 5 elements initialized to 0

        // Dynamic initialization with values
        // int[] numbers = { 1, 2, 3, 4, 5 }; // An array initialized with specific
        // values

        // int[] numbers = { 10, 20, 30, 40, 50 };

        // int firstElement = numbers[0]; // Accessing the first element (10)
        // int thirdElement = numbers[2]; // Accessing the third element (30)

        // numbers[1] = 25; // Modifying the second element to 25

        // int[] numbers = { 10, 20, 30, 40, 50 };
        // int length = numbers.length; // length is 5

        // for (int i = 0; i < length; i++) {
        // System.out.println(numbers[i]);
        // }

        // for (int num : numbers) {
        // System.out.println(num);
        // }

        // Multi-dimensional array
        // int[][] matrix1 = new int[3][3]; // A 3x3 matrix (2D array)

        // int[][] matrix2 = {
        // {1, 2, 3},
        // {4, 5, 6},
        // {7, 8, 9}
        // };

        // int element = matrix2[1][2];
        // Accessing the element in the second row and third column (6)

        // int[] numbers = { 5, 3, 8, 1, 2 };
        // Arrays.sort(numbers); // Sorting the array

        // int index = Arrays.binarySearch(numbers, 3);
        // Binary search for 3 in the sorted array

        // int[] numbers = new int[5];
        // Arrays.fill(numbers, 7); // Filling the array with 7s

        // int[] oldArray = { 1, 2, 3 };
        // int[] newArray = Arrays.copyOf(oldArray, 5);

        // List<Integer> listA = new ArrayList<>();
        // List<Integer> listB = new LinkedList<>();
        // List<Integer> listC = new Vector<>();
        // List<Integer> listD = new Stack<>();

        // List<String> list = new ArrayList<>(List.of("Cherry", "Orange", "Peach"));

        // list.add("Apple"); // Appends to the end of the list.
        // list.add(1, "Banana"); // Inserts at the specified position.
        // list.remove(1); // Removes the element at the specified position.
        // list.remove("Apple"); // Removes the first occurrence of the specified
        // element

        // String fruitName = list.get(0); // Returns the element at the specified
        // position.
        // Integer listSize = list.size(); // Returns the number of elements.
        // Boolean hasFruit = list.contains("Orange"); // Returns true if the list has
        // it.

        // System.out.println(listj);

        // ArrayList<String> arrayList = new ArrayList<>();
        // arrayList.add("Apple");
        // arrayList.add("Banana");
        // arrayList.add("Cherry");
        // System.out.println(arrayList.get(1)); // Output: Banana

        // LinkedList<String> linkedList = new LinkedList<>();
        // linkedList.add("Apple");
        // linkedList.add("Banana");
        // linkedList.addFirst("Cherry"); // Adds to the start of the list
        // System.out.println(linkedList.get(0)); // Output: Cherry

        // Vector<String> vector = new Vector<>();
        // vector.add("Apple");
        // vector.add("Banana");
        // System.out.println(vector.get(0)); // Output: Apple

        // Stack<String> stack = new Stack<>();
        // stack.push("Apple");
        // stack.push("Banana");
        // System.out.println(stack.pop()); // Output: Banana

        // // System.out.println(element);
        // // System.out.println(matrix1);

        // Set<Integer> setA = new HashSet<>();
        // Set<Integer> setB = new LinkedHashSet<>();
        // Set<Integer> setC = new TreeSet<>();

        Set<String> set = new HashSet<>(Set.of("Apple", "Banana", "Cherry"));

        set.add("Pear"); // Adds element if it is not already present.
        set.remove("Banana"); // Removes element if it is present.

        // Boolean hasFruit = set.contains("Banana"); // Returns true if the set has it.
        // Integer setSize = set.size(); // Returns the number of elements.
        // Boolean emptySet = set.isEmpty(); // Returns true if the set contains no
        // elements.
        // Iterator<String> setIterator = set.iterator(); // Returns an iterator over
        // the elements in the set.

        // HashSet<String> hashSet = new HashSet<>();
        // hashSet.add("Apple");
        // hashSet.add("Banana");
        // hashSet.add("Orange");
        // System.out.println(hashSet); // Output may be unordered

        // LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        // linkedHashSet.add("Apple");
        // linkedHashSet.add("Banana");
        // linkedHashSet.add("Orange");
        // System.out.println(linkedHashSet); // Output will be in insertion order

        // TreeSet<String> treeSet = new TreeSet<>();
        // treeSet.add("Banana");
        // treeSet.add("Apple");
        // treeSet.add("Orange");
        // System.out.println(treeSet); // Output will be sorted: [Apple, Banana,
        // Orange]

        // Map<String, Integer> mapA = new HashMap<>();
        // Map<String, Integer> mapB = new LinkedHashMap<>();
        // Map<String, Integer> mapC = new TreeMap<>();
        // Map<String, Integer> mapD = new Hashtable<>();

        Map<String, Integer> map = new HashMap<>(Map.of("Apple", 1, "Banana", 2));

        map.put("Cherry", 3); // Add new mapping or update value if key exists.
        map.remove("Banana"); // Remove mapping if it is present.

        Integer fruit = map.get("Apple"); // Returns the value to which key is mapped.
        Boolean hasKey = map.containsKey("Cherry"); // Returns true if contains key.
        Boolean hasVal = map.containsValue(3); // Returns true if contains value.
        Set<String> fruitSet = map.keySet(); // Returns a Set of all the keys.
        Collection<Integer> numVal = map.values(); // Returns a Collection of all the values.

        // Hashtable<String, Integer> hashtable = new Hashtable<>();
        // hashtable.put("Apple", 1);
        // hashtable.put("Banana", 2);
        // hashtable.put("Cherry", 3);
        // System.out.println(hashtable.get("Apple")); // Outputs: 1

        // System.out.println(fruitSet);

        // for (Integer num: numVal) {
        // System.out.println(num);
        // }

        // Iterator<String> iterator = list.iterator();
        // while (iterator.hasNext()) {
        // System.out.println(iterator.next());
        // }

        // Iterator<Entry<String, Integer>> iterator = map.entrySet().iterator();
        // while (iterator.hasNext()) {
        // Entry<String, Integer> element = iterator.next();
        // System.out.println(element.getKey());
        // System.out.println(element.getValue());
        // }

        // List<String> list = List.of("Apple", "Berry", "Avocado", "Apricot");
        // List<String> fruitA = list.stream()
        // .limit(3) // Apple, Berry, Avocado
        // .filter(name -> name.startsWith("A")) // Apple, Avocado
        // .collect(Collectors.toList()); // [Apple, Avocado]

        // System.out.println(fruitA);

        List<String> list = List.of("Apple", "Berry", "Avocado", "Apricot");
        list.stream()
                .filter(name -> name.startsWith("A"))
                .map(name -> name.toUpperCase())
                .forEach(fruits -> System.out.println(fruits)); // APPLE AVOCADO APRICOT
    }
}
