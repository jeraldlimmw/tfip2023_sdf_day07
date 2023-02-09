package sg.edu.nus.iss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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

        // Sort descending
        List<Integer> integers = new ArrayList<>();
        integers = Arrays.asList(1,42,63,12,51,61,23,21,78,10);
        integers.sort(Comparator.reverseOrder());
        System.out.println(integers);

        // Sort ascending
        integers.sort(Comparator.naturalOrder());
        System.out.println(integers);
        System.out.println();

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
}
