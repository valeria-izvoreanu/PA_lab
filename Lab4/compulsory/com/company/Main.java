package com.company;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Student[] students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .toArray(Student[]::new);

        List<Student> studentList = new LinkedList<>();
        studentList.addAll(Arrays.asList(students));
        Collections.sort(studentList,
                Comparator.comparing(Student::getName));

        School[] schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i))
                .toArray(School[]::new);

        Set<School> schoolList = new TreeSet<>();
        schoolList.addAll(Arrays.asList(schools));

        Map<Student, List<School>> stdPrefMap = new HashMap<>();
        stdPrefMap.put(students[0], Arrays.asList(schools[0], schools[1], schools[2]));
        stdPrefMap.put(students[1], Arrays.asList(schools[0], schools[1], schools[2]));
        stdPrefMap.put(students[2], Arrays.asList(schools[0], schools[1]));
        stdPrefMap.put(students[3], Arrays.asList(schools[0], schools[2]));

        Map<School, List<Student>> schPrefMap = new TreeMap<>();
        schPrefMap.put(schools[0], Arrays.asList(students[3], students[0], students[1], students[2]));
        schPrefMap.put(schools[1], Arrays.asList(students[0], students[2], students[1]));
        schPrefMap.put(schools[2], Arrays.asList(students[0], students[1], students[3]));

        System.out.println(Arrays.asList(stdPrefMap));
        System.out.println(Arrays.asList(schPrefMap));
    }
}
