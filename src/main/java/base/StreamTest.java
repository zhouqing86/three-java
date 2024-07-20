package base;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {
    static final List<Student> list = Arrays.asList(
            new Student(1, "Rohit", "Mall", 30, "Male", "Mechanical Engineering", 2015, "Mumbai", 122),
            new Student(2, "Pulkit", "Singh", 56, "Male", "Computer Engineering", 2018, "Delhi", 67),
            new Student(3, "Ankit", "Patil", 25, "Female", "Mechanical Engineering", 2019, "Kerala", 164),
            new Student(4, "Satish Ray", "Malaghan", 30, "Male", "Mechanical Engineering", 2014, "Kerala", 26),
            new Student(5, "Roshan", "Mukd", 23, "Male", "Biotech Engineering", 2022, "Mumbai", 12),
            new Student(6, "Chetan", "Star", 24, "Male", "Mechanical Engineering", 2023, "Karnataka", 90),
            new Student(7, "Arun", "Vittal", 26, "Male", "Electronics Engineering", 2014, "Karnataka", 324),
            new Student(8, "Nam", "Dev", 31, "Male", "Computer Engineering", 2014, "Karnataka", 433),
            new Student(9, "Sonu", "Shankar", 27, "Female", "Computer Engineering", 2018, "Karnataka", 7),
            new Student(10, "Shubham", "Pandey", 26, "Male", "Instrumentation Engineering", 2017, "Mumbai", 98));

    public static void main(String[] args) {
        List<Student> lstStuName = list.stream().filter(dt -> dt.getFirstName().startsWith("A"))
                .toList();
        System.out.println("List of students whose name starts with letter A : "+lstStuName);


        Map<String, List<Student>> mapData = list.stream().collect(Collectors.groupingBy(Student::getDepartmantName));
        System.out.println("Students grouped by the department names : "+mapData);


        long countStudent = list.size();
        System.out.println("Total count of students : "+countStudent);

        OptionalInt maxAge = list.stream().mapToInt(Student::getAge).max();
        System.out.println("Max age of student : "+maxAge.getAsInt());

        Optional<Student> studentWithMaxAge = list.stream().max(Comparator.comparing(Student::getAge));
        System.out.println("Student with max age: " + studentWithMaxAge);

        List<String> lstDepartments = list.stream().map(Student::getDepartmantName).distinct().toList();
        System.out.println("All distinct department names : "+lstDepartments);


        Map<String, Long> countStudentInEachdept = list.stream().collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.counting()));
        System.out.println("Student count in each department : "+countStudentInEachdept);

        List<Student> lstStudent = list.stream().filter(student -> student.getAge() < 30).toList();
        System.out.println("List of students whose age is less than 30 : "+lstStudent);


        List<Student> lstStu = list.stream().filter(student -> student.getRank() > 50 && student.getRank() < 100).toList();
        System.out.println("List of students whose rank is between 50 and 100 : "+lstStu);


        Map<String, Double> mapAvgAge = list.stream().collect(Collectors.groupingBy(Student::getGender, Collectors.averagingInt(Student::getAge)));
        System.out.println("Average age of male and female students : "+mapAvgAge);

        Map.Entry<String, Long> entry = list.stream()
                .collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.counting())).entrySet().stream()
                .max(Map.Entry.comparingByValue()).get();
        System.out.println("Department having maximum number of students : "+entry);

        List<Student> lstDelhistudent = list.stream().filter(student -> student.getCity().equals("Delhi"))
                .sorted(Comparator.comparing(Student::getFirstName).thenComparing(Student::getLastName)).toList();
        System.out.println("List of students who stays in Delhi and sort them by their names : "+lstDelhistudent);

        Map<String, Double> collect = list.stream().collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.averagingInt(Student::getRank)));
        System.out.println("Average rank in all departments  : "+collect);


        Map<String, Optional<Student>> studentData = list.stream().
                collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.maxBy(Comparator.comparingInt(Student::getRank))));
        System.out.println("Highest rank in each department  : "+studentData);

        List<Student> stuRankSorted = list.stream().sorted(Comparator.comparing(Student::getRank)).toList();
        System.out.println("List of students sorted by their rank  : "+stuRankSorted);

        Student student = list.stream().sorted(Comparator.comparing(Student::getRank).reversed()).skip(1).findFirst().get();
        System.out.println("Second highest rank student  : "+student);

    }
}
