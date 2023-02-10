package sg.edu.nus.iss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }
    
    // you can write the functional interface outside the main method
    // T is a generic data type
    @FunctionalInterface
    interface Operator<T> {
        T process(T a, T b);
    }

    public static void main(String[] args) {        
        
        if (args[0].equalsIgnoreCase("kill")) {
            System.exit(0);
        }

        emloyeeFilterSortExample();

        integersSortExample();

        functionalInterfaceExample();

        // arraySortExample();
        // mapSortExample();

        threadExecutorExample();

        // multithreadExample1();

        multithreadExample2();

        linkedListExample();

        stackExample();
    }

    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        
        employees.add(new Employee(1, "Hsien Loong", "Li", 70));
        employees.add(new Employee(2, "Ye Kung", "Ng", 54));
        employees.add(new Employee(3, "Chee Hean", "Neo", 72));
        employees.add(new Employee(4, "Pritam", "Sing", 52));
        employees.add(new Employee(5, "Eng Hen", "Sng", 56));
        employees.add(new Employee(6, "Lawrence", "Ong", 47));
        employees.add(new Employee(7, "Swee Keat", "Ng", 62));

        return employees;
    }

    public static void emloyeeFilterSortExample() {
        
        List<Employee> employees = getEmployees();
        
        // Stream and filter
        List<Employee> filteredEmployees = employees.stream()
            .filter(e -> e.getLastName().equalsIgnoreCase("ng"))
            .collect(Collectors.toList());
        System.out.println(filteredEmployees);
        System.out.println();

        // After filtering, return the first employee
        // Use .get() to return the correct type, in this case, Employee
        Employee emp = employees.stream()
            .filter(e -> e.getLastName().equalsIgnoreCase("ng"))
            .findFirst().get();
        System.out.println(emp);
        System.out.println();


        // Finding the index of and using the index to get the object
        int index = employees.indexOf(emp);
        Employee returnedEmp = employees.get(index);
        System.out.println(index + " >>>> " + returnedEmp);
        System.out.println();


        // To loop using forEach, print
        employees.forEach(emp1 -> System.out.println(emp1));
        System.out.println();


        // Sort by first name in alphabetical order
        employees.sort(Comparator.comparing(e -> e.getFirstName()));
        System.out.println(employees);
        System.out.println();

        // Reverse sort
        Comparator<Employee> comparator = Comparator.comparing(e -> e.getFirstName());
        employees.sort(comparator.reversed());
        System.out.println(employees);
        System.out.println();
    }

    public static void integersSortExample() {
         // Sort descending
        List<Integer> integers = new ArrayList<>();
        
        integers = Arrays.asList(1,42,63,12,51,61,23,21,78,10);
        integers.sort(Comparator.reverseOrder());
        System.out.println(integers);

        // Sort ascending
        integers.sort(Comparator.naturalOrder());
        System.out.println(integers);
        System.out.println();
    }
    
    public static void functionalInterfaceExample() {
        // Functional Interface
        Operator<Integer> addOperations = (a, b) -> {
            return a + b;
        };

        Operator<Integer> minusOperations = (a, b) -> {
            return a - b;
        };

        Operator<String> concatOperations = (a, b) -> {
            return a.concat(b);
        };

        System.out.println("Add Operation: " + addOperations.process(5, 2));
        System.out.println("Minus Operation: " + minusOperations.process(10, 2));
        System.out.println("Concat Operation: " + concatOperations.process("Yaba", "dabadoo"));
    }

    public static void arraySortExample() {
        System.out.println("Sorting Arrays");
        String arr[] = {"practice", "workshop", "lecture", "revision"};

        Arrays.sort(arr);
        System.out.println("Ascending sorted array: " + Arrays.toString(arr));

        Arrays.sort(arr, Collections.reverseOrder());
        System.out.println("Descending sorted array: " + Arrays.toString(arr));
        System.out.println();
    }

    public static void mapSortExample() {
        System.out.println("Sorting Maps");
        Map<String, Integer> mapList = new HashMap<>();
        mapList.put("Sushi", 5);
        mapList.put("Chocolate", 15);
        mapList.put("Coffee", 8);
        mapList.put("Tea", 12);
        mapList.put("Sandwich", 18);
        mapList.put("Hashbrown", 10);

        mapList.forEach((k, v) -> System.out.println(k + " ----> " + v));
        System.out.println();

        // sorting a list
        List<Entry<String, Integer>> sortingList = new ArrayList<>(mapList.entrySet());
        sortingList.sort(Entry.comparingByValue());
        sortingList.forEach(System.out::println);
    }

    // threading example
    // using Executor to use a fixed number of multiple threads
    public static void threadExecutorExample() {
        MyRunnableImplementation ri1 = new MyRunnableImplementation("Task 1");
        MyRunnableImplementation ri2 = new MyRunnableImplementation("Task 2");
        MyRunnableImplementation ri3 = new MyRunnableImplementation("Task 3");
        MyRunnableImplementation ri4 = new MyRunnableImplementation("Task 4");
        MyRunnableImplementation ri5 = new MyRunnableImplementation("Task 5");

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(ri1);
        es.execute(ri2);
        es.execute(ri3);
        es.execute(ri4);
        es.execute(ri5);
        // can use submit if we want a return type
        es.shutdown();
        System.out.println();
    }

    //  not limiting the number of threads used - each thread is allocated one task
    public static void multithreadExample1() {
        MyRunnableImplementation ri1 = new MyRunnableImplementation("Task 1");
        MyRunnableImplementation ri2 = new MyRunnableImplementation("Task 2");
        MyRunnableImplementation ri3 = new MyRunnableImplementation("Task 3");
        MyRunnableImplementation ri4 = new MyRunnableImplementation("Task 4");
        MyRunnableImplementation ri5 = new MyRunnableImplementation("Task 5");

        Thread t1 = new Thread(ri1);
        t1.start();
        Thread t2 = new Thread(ri2);
        t2.start();
        Thread t3 = new Thread(ri3);
        t3.start();
        Thread t4 = new Thread(ri4);
        t4.start();
        Thread t5 = new Thread(ri5);
        t5.start();
        System.out.println();
    }

    public static void multithreadExample2() {
        new Thread (() -> {
            for(int i = 0; i < 5; i++) {
                System.out.println(i);
            }
        }).start();
    }

    public static void linkedListExample() {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("A");
        ll.add("B");
        ll.add("C");
        ll.add("D");
        ll.add(2, "E");

        System.out.println(ll);

        ll.remove("C");
        System.out.println(ll);
        System.out.println(ll.peekFirst());
        ll.removeFirst();
        System.out.println(ll);

    }

    public static void stackExample(){
        Stack<Integer> st = new Stack<>();
        
        for (int i = 0; i < 10; i++) {
            st.push(i);
        }

        for (int i = 0; i < 5; i++) {
            int item = st.pop();
            System.out.println("Pop -> " + item);
        }

        int last = st.peek();
        System.out.println("Peek the last element in the stack: " + last);
        
        int first = st.firstElement();
        System.out.println("Peek the last element in the stack: " + first);

        Iterator<Integer> iterator = st.iterator();
        while (iterator.hasNext()) {
            Integer stackItem = iterator.next();
            System.out.println("Iterate through stack -> " + stackItem);
            
        }
    }
}
