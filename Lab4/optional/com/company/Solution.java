package com.company;

import java.util.*;

public class Solution {

    public Map<Student, School> solve(Problem problem) {
        Map<Student, School> answer = new HashMap<>();
        Map<Student, List<School>> stdPrefMap = problem.getStdPrefMap();
        Map<School, List<Student>> schPrefMap = problem.getSchPrefMap();
        List<Student> students = problem.getStudents();

        //sort students according to their scores
        Collections.sort(students,
                Comparator.comparing(Student::getScore));

        for (Student entry : students) {
            //for every student check in order if the schools they finds acceptable have any space left
            //and if said schools want them (contains them in it's list)
            for (int i = 0; i < stdPrefMap.get(entry).size(); i++) {
                if (stdPrefMap.get(entry).get(i).getCapacity() > 0 && schPrefMap.get(stdPrefMap.get(entry).get(i)).contains(entry)) {
                    answer.put(entry, stdPrefMap.get(entry).get(i));
                    stdPrefMap.get(entry).get(i).setCapacity(stdPrefMap.get(entry).get(i).getCapacity() - 1);
                    break;
                }
            }
        }
        return answer;
    }
}
