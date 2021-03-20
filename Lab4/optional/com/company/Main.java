package com.company;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        //create problem with the max number of schools and
        //students equal to 5
        Main app = new Main();
        Problem problem = app.randomProblem(5);

        System.out.println(Arrays.asList(problem.getStdPrefMap()));
        System.out.println(Arrays.asList(problem.getSchPrefMap()));

        //display the students that find school 0 and school 1 acceptable
        //if there are that many schools
        if (problem.getSchools().size() >= 2) {
            List<School> target = Arrays.asList(problem.getSchools().get(0), problem.getSchools().get(1));

            problem.getStudents().stream()
                    .filter(std -> problem.getStdPrefMap().get(std).containsAll(target))
                    .forEach(System.out::println);
        }

        System.out.println();
        System.out.println(problem.getStudents().get(0));
        //display that schools that have as top option student 0
        problem.getSchools().stream()
                .filter(sch -> problem.getSchPrefMap().get(sch).get(0).equals(problem.getStudents().get(0)))
                .forEach(System.out::println);

        System.out.println();
        Solution solution = new Solution();
        System.out.println(Arrays.asList(solution.solve(problem)));
    }

    private Problem randomProblem(int max) {
        //generate problem instance with faker library
        Faker fake = new Faker();

        //generate random number of schools and students
        Random rand = new Random();
        int nrStudents = rand.nextInt(max);
        nrStudents++;
        int nrSchools = rand.nextInt(max);
        nrSchools++;
        //generate fake schools
        School[] schools = IntStream.rangeClosed(0, nrSchools - 1)
                .mapToObj(i -> new School(fake.educator().secondarySchool(), rand.nextInt(max - 1) + 1))
                .toArray(School[]::new);

        //generate fake students
        Student[] students = IntStream.rangeClosed(0, nrStudents - 1)
                .mapToObj(i -> new Student(fake.name().fullName(), rand.nextInt(100)))
                .toArray(Student[]::new);

        //randomly assign a list pf preferred students to each school
        Map<School, List<Student>> schPrefMap = new TreeMap<>();
        for (int i = 0; i < nrSchools; i++) {
            int nrPrefStud = rand.nextInt(nrStudents) + 1;
            List<Student> prefList = new ArrayList<>();
            for (int j = 0; j < nrPrefStud; j++) {
                //choose a random index to get the wanted student
                int indexStudent = rand.nextInt(nrPrefStud);
                //if the selected student was already added to the list loop till
                //another suitable one is found
                if (j > 0) {
                    while (prefList.contains(students[indexStudent])) {
                        indexStudent = rand.nextInt(nrPrefStud);
                    }
                }
                prefList.add(students[indexStudent]);
            }
            schPrefMap.put(schools[i], prefList);
        }

        //the same is done with students
        Map<Student, List<School>> stdPrefMap = new HashMap<>();
        for (int i = 0; i < nrStudents; i++) {
            int nrPrefSch = rand.nextInt(nrSchools) + 1;
            List<School> prefList = new ArrayList<>();
            for (int j = 0; j < nrPrefSch; j++) {
                int indexSchool = rand.nextInt(nrPrefSch);
                if (j > 0) {
                    while (prefList.contains(schools[indexSchool])) {
                        indexSchool = rand.nextInt(nrPrefSch);
                    }
                }
                prefList.add(schools[indexSchool]);
            }
            stdPrefMap.put(students[i], prefList);
        }

        List<School> schoolList = new ArrayList<>();
        schoolList.addAll(Arrays.asList(schools));

        List<Student> studentList = new LinkedList<>();
        studentList.addAll(Arrays.asList(students));

        Problem problem = new Problem();
        problem.setSchools(schoolList);
        problem.setStudents(studentList);
        problem.setSchPrefMap(schPrefMap);
        problem.setStdPrefMap(stdPrefMap);

        return problem;
    }
}
