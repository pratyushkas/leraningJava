package com.example.demo.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPractice {
    public static void main(String[] args) {
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee(1, "Yanksha", 28, 123, "F", "HR", "Blore", 2020));
        empList.add(new Employee(2, "Francesca", 29, 120, "F", "HR", "Hyderabad", 2015));
        empList.add(new Employee(3, "Ramesh", 30, 115, "M", "HR", "Chennai", 2014));
        empList.add(new Employee(4, "Melanie", 32, 125, "F", "HR", "Chennai", 2013));
        empList.add(new Employee(5, "Padma", 22, 150, "F", "IT", "Noida", 2013));
        empList.add(new Employee(6, "Milad", 27, 140, "M", "IT", "Gurugram", 2017));
        empList.add(new Employee(7, "Uzma", 26, 130, "F", "IT", "Pune", 2016));
        empList.add(new Employee(8, "Ali", 23, 145, "M", "IT", "Trivandam", 2015));
        empList.add(new Employee(9, "Ram", 25, 160, "M", "IT", "Blore", 2010));
        //Group the employees by city
        System.out.println(empList.stream().collect(Collectors.groupingBy(Employee::getCity)));
        //Find the count of male and female employees present in the organization.
        System.out.println(empList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.counting())));
        //Find the count of male and female present in each department
        System.out.println(empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors
                .groupingBy(Employee::getGender,Collectors.counting()))));
        //Print the names of all distinct departments in the organization.
        System.out.println(empList.stream().map(Employee::getDeptName).distinct().toList());
        //Print employee details whose age is greater than 28 in the organisation.
        System.out.println(empList.stream().filter(emp->emp.getAge()>28).toList());
        //Find maximum age/oldest of employee in the organisation.
        System.out.println(empList.stream().max((emp1,emp2)->emp1.getAge()- emp2.getAge()).get());
        System.out.println(empList.stream().mapToInt(Employee::getAge).max().getAsInt());

        //Print Average age of Male and Female Employees in the organisation.
        System.out.println(empList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getAge))));
        //Print Average age of Male and Female Employees in each department.
        System.out.println(empList.stream().collect(Collectors
                .groupingBy(Employee::getDeptName,Collectors.groupingBy(Employee::getGender,Collectors.averagingInt(Employee::getAge)))));
        empList.stream().collect(Collectors
                .groupingBy(Employee::getDeptName,Collectors
                        .groupingBy(Employee::getGender,Collectors.averagingInt(Employee::getAge))))
                .forEach((dept,genderMap)->genderMap.forEach((gender,avgAge)->{
                    System.out.println(dept+"-"+gender+":"+avgAge);
                }));
        //Print the number of employees in each department.
        System.out.println(empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors.counting())));
        //Find the longest-serving employees in the organization
        System.out.println(empList.stream().min((emp1,emp2)->emp1.getYearOfJoining()-emp2.getYearOfJoining()).get());
        System.out.println(empList.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst().get());
        //Find the longest-serving employee in each department
        empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.minBy(Comparator.comparingInt(Employee::getYearOfJoining))))
                .forEach((dept,emp)->emp.ifPresent(employee -> System.out.println(dept+"-"+employee)));
        //Find youngest female employee in the organisation.
        System.out.println(empList.stream().filter(emp->emp.getGender().equals("F"))
                .min(Comparator.comparingInt(Employee::getAge)).get());
        //Find the department name that has the highest number of employees
        Map.Entry<String, Long> stringLongEntry = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()))
                .entrySet().stream().max(Comparator.comparingLong(Map.Entry::getValue)).get();
        System.out.println("Department With Highest Number of Employee: "+stringLongEntry.getKey());
        System.out.println(empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).get());

        //Sorting a Stream by age and name fields.
        System.out.println(empList.stream().sorted(Comparator.comparingInt(Employee::getAge).thenComparing(Employee::getName)).toList());

        //Print average and total salary of the organization.
        DoubleSummaryStatistics salary = empList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(salary.getAverage());
        System.out.println(salary.getSum());
        //Print Average salary by gender in each department
        System.out.println(empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors.groupingBy(Employee::getGender,
                Collectors.averagingDouble(Employee::getSalary)))));

        //Find Highest salary in the organisation.
        System.out.println("Highest salary: "+empList.stream().sorted(Comparator.comparingLong(Employee::getSalary).reversed()).findFirst().get());
        //Find Second Highest salary in the organisation.
        System.out.println("Second Highest Salary: "+empList.stream().sorted(Comparator.comparingLong(Employee::getSalary).reversed()).skip(1).findFirst().get());
        //Nth Highest salary.
        //empList.stream().sorted(Comparator.comparingLong(Employee::getSalary).reversed()).skip(n-1).findFirst().get();

        //Print the top 3 highest-salaried employees in the organisation
        empList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).limit(3).toList().forEach(System.out::println);
          //Find the first non-repeating character in a string
        String str = "My World My choice";
        Map<Character, Long> charMap = str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()));
        System.out.println(charMap.entrySet().stream().filter(entry->entry.getValue()==1).map(Map.Entry::getKey).findFirst().get());

        List<Employee> empList1 = new ArrayList<>();
        empList1.add(new Employee(1, "Yanksha", 28, 123, "F", "HR", "Blore", 2020));
        empList1.add(new Employee(2, "Francesca", 29, 120, "F", "HR", "Hyderabad", 2015));
        empList1.add(new Employee(3, "Ramesh", 30, 115, "M", "HR", "Chennai", 2014));
        empList1.add(new Employee(4, "Melanie", 32, 125, "F", "HR", "Chennai", 2013));
        List<Employee> empList2 = new ArrayList<>();
        empList2.add(new Employee(1, "Yanksha", 28, 123, "F", "HR", "Blore", 2020));
        empList2.add(new Employee(2, "Francesca", 29, 120, "F", "HR", "Hyderabad", 2015));
        empList2.add(new Employee(3, "Ramesh", 30, 115, "M", "HR", "Chennai", 2014));

        System.out.println(Stream.concat(empList1.stream(),empList2.stream()).collect(Collectors
                .toMap(Employee::getId,emp->emp,(emp1,emp2)->emp1)).values().stream().toList());

        //Find first non repeating string in array
        String[] input = {"apple", "banana", "apple", "cherry", "banana", "date","cherry","date"};
        String firstNonRepeatingStr = Arrays.stream(input).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("No non-repeating string found");
        System.out.println(firstNonRepeatingStr);

        //print the count of each character in a String?
        String strCount =  "Now is the winter";
        System.out.println(strCount.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting())));
        //Given two arrays of Person objects, merge them, sort them by age in ascending order, and then by name alphabetically for people with the same age.
        Person[] pList1 = {new Person("Alice", 25), new Person("Bob", 30),
                new Person("Charlie", 25)};
        Person[] pList2 = {new Person("David", 30), new Person("Eve", 25),
                new Person("Alice", 25)};

        Stream.concat(Arrays.stream(pList1), Arrays.stream(pList2)).sorted(Comparator.comparingInt(Person::getAge)
               .thenComparing(Comparator.comparing(Person::getName))).toList().forEach(System.out::println);

        //find the length of the longest name in a list of strings.
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eva");
        System.out.println(names.stream().mapToInt(String::length).max().getAsInt());
        //find the longest name in a list of strings.
        System.out.println(names.stream().max(Comparator.comparingInt(String::length)).get());
        //Check if a list of integers contains any prime numbers.
        List<Integer> numbers = Arrays.asList(4, 6, 8, 11, 12, 13, 14, 15);
        System.out.println(numbers.stream().anyMatch(num->isPrime(num)));
        //Count the total number of distinct words (case-insensitive) across multiple sentences.
        List<String> sentences = Arrays.asList(
                "Java Stream API provides a fluent interface",
                "It supports functional-style operations on stream",
                "In this exercise, you need to count words"
        );
        System.out.println(sentences.stream().map(s->s.toLowerCase().split(" ")).flatMap(Arrays::stream).distinct().count());
        //Find and concatenate the first two words that have even lengths.
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
        System.out.println(words.stream().filter(word->word.length()%2==0).limit(2).collect(Collectors.joining()));

        //Given a list of transactions, find the sum of transaction amounts for each day and sort by date.
        List<Transaction> transactions = Arrays.asList(
                new Transaction("2022-01-01", 100),
                new Transaction("2022-01-01", 200),
                new Transaction("2022-01-02", 300)
        );
        TreeMap<String, Long> longTreeMap = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getDate, TreeMap::new, Collectors.summingLong(Transaction::getAmount)));
        System.out.println(longTreeMap);
        //Given two arrays of integers, merge them, sort them, and then
        //filter out any numbers greater than a specified threshold.
        int[] array1 = {1, 5, 3, 9, 7};
        int[] array2 = {2, 4, 6, 8, 10};
        int threshold = 7;
        Optional<Integer> reduce = Arrays.stream(array1).boxed().reduce((a, b) -> a + b);
        System.out.println("Sum is:   "+reduce.orElse(0));
        System.out.println(Stream.concat(Arrays.stream(array1).boxed(),Arrays.stream(array2).boxed())
                .sorted().filter(num->num>threshold).toList());
        //Transform a list of employee records into a map of department to average salary.
        empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors.averagingDouble(Employee::getSalary)))
                .forEach((dept,avgSalary)->System.out.println(dept+":"+avgSalary));
        //Partition a list of numbers into two groups: prime and non-prime numbers.
        System.out.println(numbers.stream().collect(Collectors.partitioningBy(StreamPractice::isPrime)));
        //Generate Fibonacci sequence up to n terms using streams.
        Stream.iterate(new int[]{0,1},arr->new int[]{arr[1],arr[0]+arr[1]})
                .limit(10)
                .map(arr->arr[0])
                .forEach(num->System.out.print(num+" "));

        //Group strings by their first character and count occurrences.
        List<String> wordsList = Arrays.asList("apple", "banana", "bear", "cat", "apple");
        Map<Character, Long> collect = wordsList.stream().collect(Collectors.groupingBy(wordStr -> wordStr.charAt(0), Collectors.counting()));
        System.out.println(collect);

        //Find the intersection of two lists using Java streams:
        List<Integer> list3 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list4 = Arrays.asList(3, 4, 5, 6, 7);
        System.out.println(list3.stream().filter(list4::contains).toList());

        //How to Convert a List of Objects into a Sorted Map While Handling Duplicate Keys in Java?
        List<Employee> employees = Arrays.asList(
                new Employee(101, "Alice"),
                new Employee(102, "Bob"),
                new Employee(101, "Charlie"),
                new Employee(103, "David"),
                new Employee(102, "Eve")
        );
        employees.stream().collect(Collectors.groupingBy(Employee::getId, TreeMap::new, Collectors.toList()))
                .entrySet().forEach(System.out::println);


    }
    public static boolean isPrime(int num) {
        if(num<=1) return false;
        return IntStream.rangeClosed(2,(int)Math.sqrt(num)).noneMatch(i->num%i==0);
    }
}
@Data
@AllArgsConstructor
class Transaction {
    String date;
    long amount;
    // constructors and getters
}
















@Data
@AllArgsConstructor
class Person {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

@Data
@AllArgsConstructor
class  Employee {
    private int id;
    private String name;
    private int age;
    private long salary;
    private String gender;
    private String deptName;
    private String city;
    private int yearOfJoining;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", gender='" + gender + '\'' +
                ", deptName='" + deptName + '\'' +
                ", city='" + city + '\'' +
                ", yearOfJoining='" + yearOfJoining + '\'' +
                '}';
    }


}
